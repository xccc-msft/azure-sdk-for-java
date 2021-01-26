/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cosmosdb.v2020_09_01;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for ServerVersion.
 */
public final class ServerVersion extends ExpandableStringEnum<ServerVersion> {
    /** Static value 3.2 for ServerVersion. */
    public static final ServerVersion THREE_FULL_STOP_TWO = fromString("3.2");

    /** Static value 3.6 for ServerVersion. */
    public static final ServerVersion THREE_FULL_STOP_SIX = fromString("3.6");

    /**
     * Creates or finds a ServerVersion from its string representation.
     * @param name a name to look for
     * @return the corresponding ServerVersion
     */
    @JsonCreator
    public static ServerVersion fromString(String name) {
        return fromString(name, ServerVersion.class);
    }

    /**
     * @return known ServerVersion values
     */
    public static Collection<ServerVersion> values() {
        return values(ServerVersion.class);
    }
}
