/*
 * Copyright (c) 2019 by European Commission
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/page/eupl-text-11-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

package eu.eidas.encryption;

import eu.eidas.auth.commons.EidasStringUtil;
import eu.eidas.auth.commons.xml.DocumentBuilderFactoryUtil;
import eu.eidas.auth.commons.xml.opensaml.OpenSamlHelper;
import eu.eidas.encryption.exception.DecryptionException;
import eu.eidas.encryption.exception.MarshallException;
import eu.eidas.encryption.exception.UnmarshallException;
import eu.eidas.encryption.utils.DecryptionUtils;
import org.opensaml.saml.saml2.core.EncryptedAssertion;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.x509.X509Credential;
import org.opensaml.xmlsec.DecryptionParameters;
import org.opensaml.xmlsec.encryption.support.Decrypter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Low-level implementation of the OpenSAML decryption process.
 */
public final class SAMLAuthnResponseDecrypter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SAMLAuthnResponseDecrypter.class);

    @Nullable
    private final String jcaProviderName;

    public SAMLAuthnResponseDecrypter(@Nullable String jcaProviderName) {
        this.jcaProviderName = jcaProviderName;
    }

    @Nonnull
    private Response performDecryption(@Nonnull Response samlResponseDecryptee, @Nonnull Credential... credentials)
            throws DecryptionException, MarshallException {

        try {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("SAML Response XMLObject to decrypt: " + EidasStringUtil.toString(
                        OpenSamlHelper.marshall(samlResponseDecryptee)));
            }
            List<DocumentFragment> decryptedAssertionFragments = new ArrayList<>();
            for (EncryptedAssertion encAssertion : samlResponseDecryptee.getEncryptedAssertions()) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("SAML Response decrypting with data encryption algorithm: '"
                            + encAssertion.getEncryptedData().getEncryptionMethod().getAlgorithm() + "'");
                }

                final List<Credential> credentialsList = Arrays.asList(credentials);
                final Decrypter dataDecrypter = getDataDecrypter(credentialsList);

                //https://jira.spring.io/browse/SES-148
                //http://digitaliser.dk/forum/2621692
                DocumentFragment decryptedAssertionFragment =
                        dataDecrypter.decryptDataToDOM(encAssertion.getEncryptedData());

                decryptedAssertionFragments.add(decryptedAssertionFragment);

                // We only want to work on the DOM tree because:
                //
                // When you call add() on an OpenSAML list: see org.opensaml.core.xml.util.XMLObjectChildrenList.add()
                // it calls org.opensaml.core.xml.util.XMLObjectChildrenList.setParent()
                // which invokes: element.releaseParentDOM(true);
                // therefore after this call, the DOM is null
            }

            // Then we will unmarshall the decrypted fragments into a cloned DOM tree
            /*
                In the @eu.eidas.encryption.SAMLAuthnResponseDecrypter.decryptSAMLResponse method when inserting
                the decrypted Assertions the DOM resets to null.
                Marsahlling it again resolves it but this loses the ID-ness of attributes.
                Which means that signatures cannot be found anymore in the DOM and signature verification
                fails (because it used Document.getElementById("assertionID") to find the signed assertion).

                See http://svn.shibboleth.net/view/java-xmltooling/branches/REL_1/src/main/java/org/opensaml/xml/encryption/Decrypter.java?view=markup
                    http://shibboleth.net/pipermail/dev/2012-April/000624.html

                And https://issues.apache.org/jira/browse/XERCESJ-1022

                More info in the links belows
                https://jira.spring.io/browse/SES-148
                http://digitaliser.dk/forum/2621692
            */
            Element previousDom = samlResponseDecryptee.getDOM();
            if (null == previousDom) {
                previousDom = OpenSamlHelper.marshallToDom(samlResponseDecryptee);
            }
            Document ownerDocument = previousDom.getOwnerDocument();

            // Deep copy the previous DOM into a new one using importNode()
            Document newDocument = DocumentBuilderFactoryUtil.newDocument();
            Node copiedRoot = newDocument.importNode(ownerDocument.getDocumentElement(), true);
            newDocument.appendChild(copiedRoot);

            Element newRootElement = newDocument.getDocumentElement();
            NodeList encryptedAssertionList =
                    newRootElement.getElementsByTagNameNS(EncryptedAssertion.DEFAULT_ELEMENT_NAME.getNamespaceURI(),
                            EncryptedAssertion.DEFAULT_ELEMENT_NAME.getLocalPart());

            // Replace the encrypted assertions by the decrypted assertions in the new DOM tree:
            for (int i = 0, n = encryptedAssertionList.getLength(); i < n; i++) {
                Node encryptedAssertion = encryptedAssertionList.item(i);
                DocumentFragment decryptedAssertionFragment = decryptedAssertionFragments.get(i);
                // we may use adoptNode() instead of importNode() because the unmarshaller rectifies the ID-ness:
                // See org.opensaml.saml1.core.impl.AssertionUnmarshaller.unmarshall()
                // See org.opensaml.saml.saml2.core.impl.AssertionUnmarshaller.processAttribute()
                // And org.opensaml.saml1.core.impl.ResponseAbstractTypeUnmarshaller.unmarshall()
                // And org.opensaml.saml.saml2.core.impl.StatusResponseTypeUnmarshaller.processAttribute()
                Node copiedFragment = newDocument.adoptNode(decryptedAssertionFragment);
                newRootElement.replaceChild(copiedFragment, encryptedAssertion);
            }

            // Finally unmarshall the updated DOM into a new XMLObject graph:
            // The unmarshaller rectifies the ID-ness:
            // See org.opensaml.saml1.core.impl.AssertionUnmarshaller.unmarshall()
            // See org.opensaml.saml.saml2.core.impl.AssertionUnmarshaller.processAttribute()
            // And org.opensaml.saml1.core.impl.ResponseAbstractTypeUnmarshaller.unmarshall()
            // And org.opensaml.saml.saml2.core.impl.StatusResponseTypeUnmarshaller.processAttribute()
            Response decryptedResponse = (Response) OpenSamlHelper.unmarshallFromDom(newDocument);

            if (LOGGER.isTraceEnabled()) {
                try {
                    LOGGER.trace("SAML Response XMLObject decrypted: " + EidasStringUtil.toString(
                            DocumentBuilderFactoryUtil.marshall(newDocument, true)));
                } catch (TransformerException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

            return decryptedResponse;

        } catch ( ParserConfigurationException | UnmarshallException | org.opensaml.xmlsec.encryption.support.DecryptionException e) {
            throw new DecryptionException(e);
        }
    }

    /**
     * Performs a decryption on every encrypted assertions of the given response with the given credential.
     * @param samlResponseEncrypted
     *          the response for which encrypted assertions should be decrypted.
     * @param credential
     *          the credential that should use to decrypt the response.
     * @return the response with assertions corresponding to the decryption of the encrypted assertions
     * @throws DecryptionException
     *      if an error occurs during the decryption.
     */
    public Response decryptSAMLResponse(Response samlResponseEncrypted, X509Credential credential)
            throws DecryptionException {
        try {
            return performDecryption(samlResponseEncrypted, credential);
        } catch (MarshallException e) {
            throw new DecryptionException(e);
        }
    }

    /**
     * Performs a decryption on every encrypted assertions of the given response with the appropriate
     * credential from the array of credentials received in parameters.
     * @param samlResponseEncrypted
     *          the response for which encrypted assertions should be decrypted.
     * @param credentials
     *          An array of credentials that can be used for the response's decryption.
     * @return the response with assertions corresponding to the decryption of the encrypted assertions.
     * @throws DecryptionException
     *      if an error occurs during the decryption.
     */
    public Response decryptSAMLResponse(Response samlResponseEncrypted, Credential... credentials)
            throws DecryptionException {
        try {
            return performDecryption(samlResponseEncrypted, credentials);
        } catch (MarshallException e) {
            throw new DecryptionException(e);
        }
    }

    public byte[] decryptSAMLResponseAndMarshall(Response samlResponse, X509Credential credential)
            throws DecryptionException {

        Response samlResponseDecryptee = this.decryptSAMLResponse(samlResponse, credential);

        byte[] samlResponseDecrypted;
        try {
            samlResponseDecrypted = OpenSamlHelper.marshall(samlResponseDecryptee);
        } catch (MarshallException e) {
            throw new DecryptionException(e);
        }

        return samlResponseDecrypted;
    }

    private Decrypter getDataDecrypter(final List<Credential> credentials) {
        DecryptionParameters decryptionParameters = DecryptionUtils.createDecryptionParameters(credentials);
        Decrypter dataDecrypter = new Decrypter(decryptionParameters);
        dataDecrypter.setRootInNewDocument(false);
        if (getJcaProviderName() != null) {
            dataDecrypter.setJCAProviderName(getJcaProviderName());
        }
        return dataDecrypter;
    }

    @Nullable
    public String getJcaProviderName() {
        return jcaProviderName;
    }
}
