/*
 * Licensed to the University Corporation for Advanced Internet Development, 
 * Inc. (UCAID) under one or more contributor license agreements.  See the 
 * NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The UCAID licenses this file to You under the Apache 
 * License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensaml.saml1.core.impl;

import org.opensaml.common.impl.AbstractSAMLObjectMarshaller;
import org.opensaml.saml1.core.SubjectLocality;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.MarshallingException;
import org.w3c.dom.Element;

/**
 * A thread safe Marshaller for {@link org.opensaml.saml1.core.SubjectLocality} objects.
 */
public class SubjectLocalityMarshaller extends AbstractSAMLObjectMarshaller {

    /** {@inheritDoc} */
    public void marshallAttributes(XMLObject samlElement, Element domElement) throws MarshallingException {
        SubjectLocality subjectLocality = (SubjectLocality) samlElement;

        if (subjectLocality.getIPAddress() != null) {
            domElement.setAttributeNS(null, SubjectLocality.IPADDRESS_ATTRIB_NAME, subjectLocality.getIPAddress());
        }

        if (subjectLocality.getDNSAddress() != null) {
            domElement.setAttributeNS(null, SubjectLocality.DNSADDRESS_ATTRIB_NAME, subjectLocality.getDNSAddress());
        }
    }
}