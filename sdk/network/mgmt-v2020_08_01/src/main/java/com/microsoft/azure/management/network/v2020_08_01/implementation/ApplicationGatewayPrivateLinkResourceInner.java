/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.network.v2020_08_01.implementation;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.SubResource;

/**
 * PrivateLink Resource of an application gateway.
 */
@JsonFlatten
public class ApplicationGatewayPrivateLinkResourceInner extends SubResource {
    /**
     * Group identifier of private link resource.
     */
    @JsonProperty(value = "properties.groupId", access = JsonProperty.Access.WRITE_ONLY)
    private String groupId;

    /**
     * Required member names of private link resource.
     */
    @JsonProperty(value = "properties.requiredMembers", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> requiredMembers;

    /**
     * Required DNS zone names of the the private link resource.
     */
    @JsonProperty(value = "properties.requiredZoneNames")
    private List<String> requiredZoneNames;

    /**
     * Name of the private link resource that is unique within an Application
     * Gateway.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * A unique read-only string that changes whenever the resource is updated.
     */
    @JsonProperty(value = "etag", access = JsonProperty.Access.WRITE_ONLY)
    private String etag;

    /**
     * Type of the resource.
     */
    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    private String type;

    /**
     * Get group identifier of private link resource.
     *
     * @return the groupId value
     */
    public String groupId() {
        return this.groupId;
    }

    /**
     * Get required member names of private link resource.
     *
     * @return the requiredMembers value
     */
    public List<String> requiredMembers() {
        return this.requiredMembers;
    }

    /**
     * Get required DNS zone names of the the private link resource.
     *
     * @return the requiredZoneNames value
     */
    public List<String> requiredZoneNames() {
        return this.requiredZoneNames;
    }

    /**
     * Set required DNS zone names of the the private link resource.
     *
     * @param requiredZoneNames the requiredZoneNames value to set
     * @return the ApplicationGatewayPrivateLinkResourceInner object itself.
     */
    public ApplicationGatewayPrivateLinkResourceInner withRequiredZoneNames(List<String> requiredZoneNames) {
        this.requiredZoneNames = requiredZoneNames;
        return this;
    }

    /**
     * Get name of the private link resource that is unique within an Application Gateway.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set name of the private link resource that is unique within an Application Gateway.
     *
     * @param name the name value to set
     * @return the ApplicationGatewayPrivateLinkResourceInner object itself.
     */
    public ApplicationGatewayPrivateLinkResourceInner withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get a unique read-only string that changes whenever the resource is updated.
     *
     * @return the etag value
     */
    public String etag() {
        return this.etag;
    }

    /**
     * Get type of the resource.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

}
