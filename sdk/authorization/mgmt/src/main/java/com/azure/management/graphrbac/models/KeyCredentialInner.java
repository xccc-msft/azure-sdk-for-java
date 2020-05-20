// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.graphrbac.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/** The KeyCredential model. */
@Fluent
public final class KeyCredentialInner {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(KeyCredentialInner.class);

    /*
     * Start date.
     */
    @JsonProperty(value = "startDate")
    private OffsetDateTime startDate;

    /*
     * End date.
     */
    @JsonProperty(value = "endDate")
    private OffsetDateTime endDate;

    /*
     * Key value.
     */
    @JsonProperty(value = "value")
    private String value;

    /*
     * Key ID.
     */
    @JsonProperty(value = "keyId")
    private String keyId;

    /*
     * Usage. Acceptable values are 'Verify' and 'Sign'.
     */
    @JsonProperty(value = "usage")
    private String usage;

    /*
     * Type. Acceptable values are 'AsymmetricX509Cert' and 'Symmetric'.
     */
    @JsonProperty(value = "type")
    private String type;

    /*
     * Custom Key Identifier
     */
    @JsonProperty(value = "customKeyIdentifier")
    private String customKeyIdentifier;

    /*
     * Active Directory Key Credential information.
     */
    @JsonIgnore private Map<String, Object> additionalProperties;

    /**
     * Get the startDate property: Start date.
     *
     * @return the startDate value.
     */
    public OffsetDateTime startDate() {
        return this.startDate;
    }

    /**
     * Set the startDate property: Start date.
     *
     * @param startDate the startDate value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get the endDate property: End date.
     *
     * @return the endDate value.
     */
    public OffsetDateTime endDate() {
        return this.endDate;
    }

    /**
     * Set the endDate property: End date.
     *
     * @param endDate the endDate value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get the value property: Key value.
     *
     * @return the value value.
     */
    public String value() {
        return this.value;
    }

    /**
     * Set the value property: Key value.
     *
     * @param value the value value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the keyId property: Key ID.
     *
     * @return the keyId value.
     */
    public String keyId() {
        return this.keyId;
    }

    /**
     * Set the keyId property: Key ID.
     *
     * @param keyId the keyId value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withKeyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    /**
     * Get the usage property: Usage. Acceptable values are 'Verify' and 'Sign'.
     *
     * @return the usage value.
     */
    public String usage() {
        return this.usage;
    }

    /**
     * Set the usage property: Usage. Acceptable values are 'Verify' and 'Sign'.
     *
     * @param usage the usage value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withUsage(String usage) {
        this.usage = usage;
        return this;
    }

    /**
     * Get the type property: Type. Acceptable values are 'AsymmetricX509Cert' and 'Symmetric'.
     *
     * @return the type value.
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the type property: Type. Acceptable values are 'AsymmetricX509Cert' and 'Symmetric'.
     *
     * @param type the type value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the customKeyIdentifier property: Custom Key Identifier.
     *
     * @return the customKeyIdentifier value.
     */
    public String customKeyIdentifier() {
        return this.customKeyIdentifier;
    }

    /**
     * Set the customKeyIdentifier property: Custom Key Identifier.
     *
     * @param customKeyIdentifier the customKeyIdentifier value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withCustomKeyIdentifier(String customKeyIdentifier) {
        this.customKeyIdentifier = customKeyIdentifier;
        return this;
    }

    /**
     * Get the additionalProperties property: Active Directory Key Credential information.
     *
     * @return the additionalProperties value.
     */
    @JsonAnyGetter
    public Map<String, Object> additionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Active Directory Key Credential information.
     *
     * @param additionalProperties the additionalProperties value to set.
     * @return the KeyCredentialInner object itself.
     */
    public KeyCredentialInner withAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @JsonAnySetter
    void withAdditionalProperties(String key, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(key, value);
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}