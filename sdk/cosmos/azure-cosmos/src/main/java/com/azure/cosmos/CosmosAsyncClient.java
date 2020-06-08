// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos;

import com.azure.core.annotation.ServiceClient;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.implementation.AsyncDocumentClient;
import com.azure.cosmos.implementation.Configs;
import com.azure.cosmos.implementation.ConnectionPolicy;
import com.azure.cosmos.implementation.CosmosAuthorizationTokenResolver;
import com.azure.cosmos.implementation.Database;
import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.directconnectivity.rntbd.RntbdMetrics;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosDatabaseProperties;
import com.azure.cosmos.models.CosmosDatabaseRequestOptions;
import com.azure.cosmos.models.CosmosPermissionProperties;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.ModelBridgeInternal;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.models.ThroughputProperties;
import com.azure.cosmos.util.CosmosPagedFlux;
import com.azure.cosmos.util.UtilBridgeInternal;
import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.util.List;

import static com.azure.cosmos.implementation.Utils.setContinuationTokenAndMaxItemCount;

/**
 * Provides a client-side logical representation of the Azure Cosmos DB service.
 * This asynchronous client is used to configure and execute requests against the service.
 */
@ServiceClient(
    builder = CosmosClientBuilder.class,
    isAsync = true)
public final class CosmosAsyncClient implements Closeable {

    // Async Cosmos client wrapper
    private final Configs configs;
    private final AsyncDocumentClient asyncDocumentClient;
    private final String serviceEndpoint;
    private final String keyOrResourceToken;
    private final ConnectionPolicy connectionPolicy;
    private final ConsistencyLevel desiredConsistencyLevel;
    private final List<CosmosPermissionProperties> permissions;
    private final CosmosAuthorizationTokenResolver cosmosAuthorizationTokenResolver;
    private final AzureKeyCredential credential;
    private final boolean sessionCapturingOverride;
    private final boolean enableTransportClientSharing;
    private final boolean contentResponseOnWriteEnabled;

    CosmosAsyncClient(CosmosClientBuilder builder) {
        this.configs = builder.configs();
        this.serviceEndpoint = builder.getEndpoint();
        this.keyOrResourceToken = builder.getKey();
        this.connectionPolicy = builder.getConnectionPolicy();
        this.desiredConsistencyLevel = builder.getConsistencyLevel();
        this.permissions = builder.getPermissions();
        this.cosmosAuthorizationTokenResolver = builder.getAuthorizationTokenResolver();
        this.credential = builder.getCredential();
        this.sessionCapturingOverride = builder.isSessionCapturingOverrideEnabled();
        this.enableTransportClientSharing = builder.isConnectionSharingAcrossClientsEnabled();
        this.contentResponseOnWriteEnabled = builder.isContentResponseOnWriteEnabled();
        this.asyncDocumentClient = new AsyncDocumentClient.Builder()
                                       .withServiceEndpoint(this.serviceEndpoint)
                                       .withMasterKeyOrResourceToken(this.keyOrResourceToken)
                                       .withConnectionPolicy(this.connectionPolicy)
                                       .withConsistencyLevel(this.desiredConsistencyLevel)
                                       .withSessionCapturingOverride(this.sessionCapturingOverride)
                                       .withConfigs(this.configs)
                                       .withTokenResolver(this.cosmosAuthorizationTokenResolver)
                                       .withCredential(this.credential)
                                       .withTransportClientSharing(this.enableTransportClientSharing)
                                       .withContentResponseOnWriteEnabled(this.contentResponseOnWriteEnabled)
                                       .build();
    }

    AsyncDocumentClient getContextClient() {
        return this.asyncDocumentClient;
    }

    /**
     * Monitor Cosmos client performance and resource utilization using the specified meter registry.
     *
     * @param registry meter registry to use for performance monitoring.
     */
    static void setMonitorTelemetry(MeterRegistry registry) {
        RntbdMetrics.add(registry);
    }

    /**
     * Get the service endpoint.
     *
     * @return the service endpoint.
     */
    String getServiceEndpoint() {
        return serviceEndpoint;
    }

    /**
     * Gets the key or resource token.
     *
     * @return get the key or resource token.
     */
    String getKeyOrResourceToken() {
        return keyOrResourceToken;
    }

    /**
     * Get the connection policy.
     *
     * @return {@link ConnectionPolicy}.
     */
    ConnectionPolicy getConnectionPolicy() {
        return connectionPolicy;
    }

    /**
     * Gets the consistency level.
     *
     * @return the {@link ConsistencyLevel}.
     */
    ConsistencyLevel getDesiredConsistencyLevel() {
        return desiredConsistencyLevel;
    }

    /**
     * Gets the permission list.
     *
     * @return the permission list.
     */
    List<CosmosPermissionProperties> getPermissions() {
        return permissions;
    }

    AsyncDocumentClient getDocClientWrapper() {
        return asyncDocumentClient;
    }

    /**
     * Gets the configs.
     *
     * @return the configs.
     */
    Configs getConfigs() {
        return configs;
    }

    /**
     * Gets the token resolver.
     *
     * @return the token resolver.
     */
    CosmosAuthorizationTokenResolver getCosmosAuthorizationTokenResolver() {
        return cosmosAuthorizationTokenResolver;
    }

    /**
     * Gets the azure key credential.
     *
     * @return azure key credential.
     */
    AzureKeyCredential credential() {
        return credential;
    }

    /**
     * Gets the boolean which indicates whether to only return the headers and status code in Cosmos DB response
     * in case of Create, Update and Delete operations on CosmosItem.
     *
     * If set to false (which is by default), this removes the resource from response. It reduces networking
     * and CPU load by not sending the resource back over the network and serializing it
     * on the client.
     *
     * By-default, this is false.
     *
     * @return a boolean indicating whether resource will be included in the response or not.
     */
    boolean isContentResponseOnWriteEnabled() {
        return contentResponseOnWriteEnabled;
    }

    /**
     * CREATE a Database if it does not already exist on the service.
     * <p>
     * The {@link Mono} upon successful completion will contain a single cosmos database response with the
     * created or existing database.
     *
     * @param databaseProperties CosmosDatabaseProperties.
     * @return a {@link Mono} containing the cosmos database response with the created or existing database or
     * an error.
     */
    Mono<CosmosDatabaseResponse> createDatabaseIfNotExists(CosmosDatabaseProperties databaseProperties) {
        return createDatabaseIfNotExistsInternal(getDatabase(databaseProperties.getId()));
    }

    /**
     * Create a Database if it does not already exist on the service.
     * <p>
     * The {@link Mono} upon successful completion will contain a single cosmos database response with the
     * created or existing database.
     *
     * @param id the id of the database.
     * @return a {@link Mono} containing the cosmos database response with the created or existing database or
     * an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabaseIfNotExists(String id) {
        return createDatabaseIfNotExistsInternal(getDatabase(id));
    }

    private Mono<CosmosDatabaseResponse> createDatabaseIfNotExistsInternal(CosmosAsyncDatabase database) {
        return database.read().onErrorResume(exception -> {
            final Throwable unwrappedException = Exceptions.unwrap(exception);
            if (unwrappedException instanceof CosmosException) {
                final CosmosException cosmosException = (CosmosException) unwrappedException;
                if (cosmosException.getStatusCode() == HttpConstants.StatusCodes.NOTFOUND) {
                    return createDatabase(new CosmosDatabaseProperties(database.getId()),
                        new CosmosDatabaseRequestOptions());
                }
            }
            return Mono.error(unwrappedException);
        });
    }

    /**
     * Create a Database if it does not already exist on the service.
     * <p>
     * The throughputProperties will only be used if the specified database
     * does not exist and therefor a new database will be created with throughputProperties.
     * <p>
     * The {@link Mono} upon successful completion will contain a single cosmos database response with the
     * created or existing database.
     *
     * @param id the id.
     * @param throughputProperties the throughputProperties.
     * @return the mono.
     */
    public Mono<CosmosDatabaseResponse> createDatabaseIfNotExists(String id, ThroughputProperties throughputProperties) {
        return this.getDatabase(id).read().onErrorResume(exception -> {
            final Throwable unwrappedException = Exceptions.unwrap(exception);
            if (unwrappedException instanceof CosmosException) {
                final CosmosException cosmosException = (CosmosException) unwrappedException;
                if (cosmosException.getStatusCode() == HttpConstants.StatusCodes.NOTFOUND) {
                    CosmosDatabaseRequestOptions options = new CosmosDatabaseRequestOptions();
                    ModelBridgeInternal.setThroughputProperties(options, throughputProperties);
                    return createDatabase(new CosmosDatabaseProperties(id),
                        options);
                }
            }
            return Mono.error(unwrappedException);
        });
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param databaseProperties {@link CosmosDatabaseProperties}.
     * @param options {@link CosmosDatabaseRequestOptions}.
     * @return an {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(CosmosDatabaseProperties databaseProperties,
                                                       CosmosDatabaseRequestOptions options) {
        if (options == null) {
            options = new CosmosDatabaseRequestOptions();
        }
        Database wrappedDatabase = new Database();
        wrappedDatabase.setId(databaseProperties.getId());
        return asyncDocumentClient.createDatabase(wrappedDatabase, ModelBridgeInternal.toRequestOptions(options))
                   .map(databaseResourceResponse -> ModelBridgeInternal.createCosmosDatabaseResponse(databaseResourceResponse))
                   .single();
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param databaseProperties {@link CosmosDatabaseProperties}.
     * @return an {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(CosmosDatabaseProperties databaseProperties) {
        return createDatabase(databaseProperties, new CosmosDatabaseRequestOptions());
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param id id of the database.
     * @return a {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(String id) {
        return createDatabase(new CosmosDatabaseProperties(id), new CosmosDatabaseRequestOptions());
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param databaseProperties {@link CosmosDatabaseProperties}.
     * @param throughputProperties the throughput properties for the database.
     * @param options {@link CosmosDatabaseRequestOptions}.
     * @return an {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(CosmosDatabaseProperties databaseProperties,
                                                       ThroughputProperties throughputProperties,
                                                       CosmosDatabaseRequestOptions options) {
        if (options == null) {
            options = new CosmosDatabaseRequestOptions();
        }
        ModelBridgeInternal.setThroughputProperties(options, throughputProperties);
        Database wrappedDatabase = new Database();
        wrappedDatabase.setId(databaseProperties.getId());
        return asyncDocumentClient.createDatabase(wrappedDatabase, ModelBridgeInternal.toRequestOptions(options))
                   .map(databaseResourceResponse -> ModelBridgeInternal.createCosmosDatabaseResponse(databaseResourceResponse))
                   .single();
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param databaseProperties {@link CosmosDatabaseProperties}.
     * @param throughputProperties the throughput properties for the database.
     * @return an {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(CosmosDatabaseProperties databaseProperties, ThroughputProperties throughputProperties) {
        CosmosDatabaseRequestOptions options = new CosmosDatabaseRequestOptions();
        ModelBridgeInternal.setThroughputProperties(options, throughputProperties);
        return createDatabase(databaseProperties, options);
    }

    /**
     * Creates a database.
     * <p>
     * After subscription the operation will be performed.
     * The {@link Mono} upon successful completion will contain a single resource response with the
     * created database.
     * In case of failure the {@link Mono} will error.
     *
     * @param id id of the database.
     * @param throughput the throughput for the database.
     * @return a {@link Mono} containing the single cosmos database response with the created database or an error.
     */
    Mono<CosmosDatabaseResponse> createDatabase(String id, int throughput) {
        CosmosDatabaseRequestOptions options = new CosmosDatabaseRequestOptions();
        ModelBridgeInternal.setThroughputProperties(options, ThroughputProperties.createManualThroughput(throughput));
        return createDatabase(new CosmosDatabaseProperties(id), options);
    }

    /**
     * Creates a database.
     *
     * @param id the id.
     * @param throughputProperties the throughputProperties.
     * @return the mono.
     */
    public Mono<CosmosDatabaseResponse> createDatabase(String id, ThroughputProperties throughputProperties) {
        CosmosDatabaseRequestOptions options = new CosmosDatabaseRequestOptions();
        ModelBridgeInternal.setThroughputProperties(options, throughputProperties);
        return createDatabase(new CosmosDatabaseProperties(id), options);
    }

    /**
     * Reads all databases.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response of the read databases.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param options {@link CosmosQueryRequestOptions}
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of read databases or an error.
     */
    CosmosPagedFlux<CosmosDatabaseProperties> readAllDatabases(CosmosQueryRequestOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return getDocClientWrapper().readDatabases(options)
                                        .map(response ->
                                            BridgeInternal.createFeedResponse(
                                                ModelBridgeInternal.getCosmosDatabasePropertiesFromV2Results(response.getResults()),
                                                response.getResponseHeaders()));
        });
    }

    /**
     * Reads all databases.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response of the read databases.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of read databases or an error.
     */
    public CosmosPagedFlux<CosmosDatabaseProperties> readAllDatabases() {
        return readAllDatabases(new CosmosQueryRequestOptions());
    }


    /**
     * Query for databases.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response of the read databases.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param query the query.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of read databases or an error.
     */
    public CosmosPagedFlux<CosmosDatabaseProperties> queryDatabases(String query, CosmosQueryRequestOptions options) {
        return queryDatabases(new SqlQuerySpec(query), options);
    }

    /**
     * Query for databases.
     * <p>
     * After subscription the operation will be performed.
     * The {@link CosmosPagedFlux} will contain one or several feed response of the read databases.
     * In case of failure the {@link CosmosPagedFlux} will error.
     *
     * @param querySpec the SQL query specification.
     * @param options the feed options.
     * @return a {@link CosmosPagedFlux} containing one or several feed response pages of read databases or an error.
     */
    public CosmosPagedFlux<CosmosDatabaseProperties> queryDatabases(SqlQuerySpec querySpec, CosmosQueryRequestOptions options) {
        return UtilBridgeInternal.createCosmosPagedFlux(pagedFluxOptions -> {
            setContinuationTokenAndMaxItemCount(pagedFluxOptions, options);
            return getDocClientWrapper().queryDatabases(querySpec, options)
                                        .map(response -> BridgeInternal.createFeedResponse(
                                            ModelBridgeInternal.getCosmosDatabasePropertiesFromV2Results(response.getResults()),
                                            response.getResponseHeaders()));
        });
    }

    /**
     * Gets a database object without making a service call.
     *
     * @param id name of the database.
     * @return {@link CosmosAsyncDatabase}.
     */
    public CosmosAsyncDatabase getDatabase(String id) {
        return new CosmosAsyncDatabase(id, this);
    }

    /**
     * Close this {@link CosmosAsyncClient} instance and cleans up the resources.
     */
    @Override
    public void close() {
        asyncDocumentClient.close();
    }
}