/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement.v2019_12_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Properties of server X509Names.
 */
public class X509CertificateName {
    /**
     * Common Name of the Certificate.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * Thumbprint for the Issuer of the Certificate.
     */
    @JsonProperty(value = "issuerCertificateThumbprint")
    private String issuerCertificateThumbprint;

    /**
     * Get common Name of the Certificate.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set common Name of the Certificate.
     *
     * @param name the name value to set
     * @return the X509CertificateName object itself.
     */
    public X509CertificateName withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get thumbprint for the Issuer of the Certificate.
     *
     * @return the issuerCertificateThumbprint value
     */
    public String issuerCertificateThumbprint() {
        return this.issuerCertificateThumbprint;
    }

    /**
     * Set thumbprint for the Issuer of the Certificate.
     *
     * @param issuerCertificateThumbprint the issuerCertificateThumbprint value to set
     * @return the X509CertificateName object itself.
     */
    public X509CertificateName withIssuerCertificateThumbprint(String issuerCertificateThumbprint) {
        this.issuerCertificateThumbprint = issuerCertificateThumbprint;
        return this;
    }

}