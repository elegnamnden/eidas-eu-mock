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

package org.opensaml.saml2.core.impl;

import javax.xml.namespace.QName;

import org.opensaml.common.BaseSAMLObjectProviderTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.AssertionIDRef;

/**
 * Test case for creating, marshalling, and unmarshalling {@link org.opensaml.saml2.core.impl.AssertionIDRefImpl}.
 */
public class AssertionIDRefTest extends BaseSAMLObjectProviderTestCase {

    /**
     * Expected AssertionID value
     */
    protected String expectedAssertionID;

    /**
     * Constructor
     */
    public AssertionIDRefTest() {
        singleElementFile = "/data/org/opensaml/saml2/core/impl/AssertionIDRef.xml";
    }

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();

        expectedAssertionID = "assertion ID";
    }

    /**
     * {@inheritDoc}
     */
    public void testSingleElementUnmarshall() {
        AssertionIDRef assertionIDRef = (AssertionIDRef) unmarshallElement(singleElementFile);

        String assertionID = assertionIDRef.getAssertionID();
        assertEquals("AssertionID was " + assertionID + ", expected " + expectedAssertionID, expectedAssertionID,
                assertionID);
    }

    /**
     * {@inheritDoc}
     */
    public void testSingleElementOptionalAttributesUnmarshall() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    public void testSingleElementMarshall() {
        QName qname = new QName(SAMLConstants.SAML20_NS, AssertionIDRef.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20_PREFIX);
        AssertionIDRef assertionIDRef = (AssertionIDRef) buildXMLObject(qname);

        assertionIDRef.setAssertionID(expectedAssertionID);
        assertEquals(expectedDOM, assertionIDRef);
    }

    /**
     * {@inheritDoc}
     */
    public void testSingleElementOptionalAttributesMarshall() {
        // do nothing
    }
}