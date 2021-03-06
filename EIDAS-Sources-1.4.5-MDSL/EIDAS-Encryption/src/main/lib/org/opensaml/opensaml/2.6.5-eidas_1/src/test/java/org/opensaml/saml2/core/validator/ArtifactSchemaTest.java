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

/**
 * 
 */
package org.opensaml.saml2.core.validator;

import javax.xml.namespace.QName;

import org.opensaml.common.BaseSAMLObjectValidatorTestCase;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Artifact;

/**
 *
 */
public class ArtifactSchemaTest extends BaseSAMLObjectValidatorTestCase {

    /**
     * Constructor
     *
     */
    public ArtifactSchemaTest() {
        super();
        targetQName = new QName(SAMLConstants.SAML20P_NS, Artifact.DEFAULT_ELEMENT_LOCAL_NAME, SAMLConstants.SAML20P_PREFIX);
        validator = new ArtifactSchemaValidator();
    }

    /** {@inheritDoc} */
    protected void populateRequiredData() {
        super.populateRequiredData();
        Artifact artifact = (Artifact) target;
        artifact.setArtifact("artifact-string");
    }
   
   /**
    * Tests null or empty artifact.
    */
    public void testArtifactFailure() {
       Artifact artifact = (Artifact) target;
       
       artifact.setArtifact(null);
       assertValidationFail("Artifact was null");
       
       artifact.setArtifact("");
       assertValidationFail("Artifact was empty string");
    
       artifact.setArtifact("        ");
       assertValidationFail("Artifact was all whitespace");
   }

}
