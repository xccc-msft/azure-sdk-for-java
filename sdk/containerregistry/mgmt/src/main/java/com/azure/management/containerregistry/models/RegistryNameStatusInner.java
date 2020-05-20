// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.containerregistry.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The RegistryNameStatus model. */
@Fluent
public final class RegistryNameStatusInner {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(RegistryNameStatusInner.class);

    /*
     * The value that indicates whether the name is available.
     */
    @JsonProperty(value = "nameAvailable")
    private Boolean nameAvailable;

    /*
     * If any, the reason that the name is not available.
     */
    @JsonProperty(value = "reason")
    private String reason;

    /*
     * If any, the error message that provides more detail for the reason that
     * the name is not available.
     */
    @JsonProperty(value = "message")
    private String message;

    /**
     * Get the nameAvailable property: The value that indicates whether the name is available.
     *
     * @return the nameAvailable value.
     */
    public Boolean nameAvailable() {
        return this.nameAvailable;
    }

    /**
     * Set the nameAvailable property: The value that indicates whether the name is available.
     *
     * @param nameAvailable the nameAvailable value to set.
     * @return the RegistryNameStatusInner object itself.
     */
    public RegistryNameStatusInner withNameAvailable(Boolean nameAvailable) {
        this.nameAvailable = nameAvailable;
        return this;
    }

    /**
     * Get the reason property: If any, the reason that the name is not available.
     *
     * @return the reason value.
     */
    public String reason() {
        return this.reason;
    }

    /**
     * Set the reason property: If any, the reason that the name is not available.
     *
     * @param reason the reason value to set.
     * @return the RegistryNameStatusInner object itself.
     */
    public RegistryNameStatusInner withReason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Get the message property: If any, the error message that provides more detail for the reason that the name is not
     * available.
     *
     * @return the message value.
     */
    public String message() {
        return this.message;
    }

    /**
     * Set the message property: If any, the error message that provides more detail for the reason that the name is not
     * available.
     *
     * @param message the message value to set.
     * @return the RegistryNameStatusInner object itself.
     */
    public RegistryNameStatusInner withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}