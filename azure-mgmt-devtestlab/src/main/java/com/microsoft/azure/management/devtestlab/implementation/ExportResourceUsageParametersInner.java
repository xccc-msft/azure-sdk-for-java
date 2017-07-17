/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.devtestlab.implementation;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The parameters of the export operation.
 */
public class ExportResourceUsageParametersInner {
    /**
     * The blob storage absolute sas uri with write permission to the container
     * which the usage data needs to be uploaded to.
     */
    @JsonProperty(value = "blobStorageAbsoluteSasUri")
    private String blobStorageAbsoluteSasUri;

    /**
     * The start time of the usage. If not provided, usage will be reported
     * since the beginning of data collection.
     */
    @JsonProperty(value = "usageStartDate")
    private DateTime usageStartDate;

    /**
     * Get the blobStorageAbsoluteSasUri value.
     *
     * @return the blobStorageAbsoluteSasUri value
     */
    public String blobStorageAbsoluteSasUri() {
        return this.blobStorageAbsoluteSasUri;
    }

    /**
     * Set the blobStorageAbsoluteSasUri value.
     *
     * @param blobStorageAbsoluteSasUri the blobStorageAbsoluteSasUri value to set
     * @return the ExportResourceUsageParametersInner object itself.
     */
    public ExportResourceUsageParametersInner withBlobStorageAbsoluteSasUri(String blobStorageAbsoluteSasUri) {
        this.blobStorageAbsoluteSasUri = blobStorageAbsoluteSasUri;
        return this;
    }

    /**
     * Get the usageStartDate value.
     *
     * @return the usageStartDate value
     */
    public DateTime usageStartDate() {
        return this.usageStartDate;
    }

    /**
     * Set the usageStartDate value.
     *
     * @param usageStartDate the usageStartDate value to set
     * @return the ExportResourceUsageParametersInner object itself.
     */
    public ExportResourceUsageParametersInner withUsageStartDate(DateTime usageStartDate) {
        this.usageStartDate = usageStartDate;
        return this;
    }

}
