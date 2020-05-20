// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.management.compute.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The ResourceSkusResult model. */
@Fluent
public final class ResourceSkusResultInner {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ResourceSkusResultInner.class);

    /*
     * The list of skus available for the subscription.
     */
    @JsonProperty(value = "value", required = true)
    private List<ResourceSkuInner> value;

    /*
     * The URI to fetch the next page of Resource Skus. Call ListNext() with
     * this URI to fetch the next page of Resource Skus
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: The list of skus available for the subscription.
     *
     * @return the value value.
     */
    public List<ResourceSkuInner> value() {
        return this.value;
    }

    /**
     * Set the value property: The list of skus available for the subscription.
     *
     * @param value the value value to set.
     * @return the ResourceSkusResultInner object itself.
     */
    public ResourceSkusResultInner withValue(List<ResourceSkuInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The URI to fetch the next page of Resource Skus. Call ListNext() with this URI to
     * fetch the next page of Resource Skus.
     *
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The URI to fetch the next page of Resource Skus. Call ListNext() with this URI to
     * fetch the next page of Resource Skus.
     *
     * @param nextLink the nextLink value to set.
     * @return the ResourceSkusResultInner object itself.
     */
    public ResourceSkusResultInner withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (value() == null) {
            throw logger
                .logExceptionAsError(
                    new IllegalArgumentException("Missing required property value in model ResourceSkusResultInner"));
        } else {
            value().forEach(e -> e.validate());
        }
    }
}