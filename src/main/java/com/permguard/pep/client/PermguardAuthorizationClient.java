package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.proto.AuthorizationCheck.*;
import com.permguard.pep.proto.V1PDPServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Client for interacting with the Policy Decision Point.
 */
public class PermguardAuthorizationClient {

    private final PermguardConfig config;
    private ManagedChannel channel;
    private V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;

    /**
     * Constructs a new client with the given configuration.
     * Initializes the channel and stub.
     *
     * @param config the configuration for the client
     */
    public PermguardAuthorizationClient(PermguardConfig config) {
        this.config = config;
        initChannelAndStub();
    }

    /**
     * Initializes (or re-initializes) the channel and stub based on the configuration.
     */
    private void initChannelAndStub() {
        ManagedChannelBuilder<?> builder = ManagedChannelBuilder
                .forAddress(config.getHost(), config.getPort());

        if (config.isUsePlaintext()) {
            builder.usePlaintext();
        }
        this.channel = builder.build();
        this.blockingStub = V1PDPServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Closes the channel when it is no longer needed.
     */
    public void shutdown() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }

    /**
     * Checks whether the given subject is authorized to perform the given action on the given resource,
     * according to the policy defined in the given policy store.
     *
     * @param applicationId  the ID of the application
     * @param policyStoreType the type of the policy store
     * @param policyStoreId   the ID of the policy store
     * @param principalType   the type of the principal
     * @param principalId     the ID of the principal
     * @param subjectType     the type of the subject
     * @param subjectId       the ID of the subject
     * @param resourceType    the type of the resource
     * @param resourceId      the ID of the resource
     * @param actionName      the name of the action
     * @return the result of the authorization check
     */
    public AuthorizationCheckResponse checkAuthorization(
            long applicationId,
            String policyStoreType,
            String policyStoreId,
            String principalType,
            String principalId,
            String subjectType,
            String subjectId,
            String resourceType,
            String resourceId,
            String actionName
    ) {
        // Build the request
        AuthorizationCheckRequest request = AuthorizationCheckRequest.newBuilder()
                .setAuthorizationContext(
                        AuthorizationContextRequest.newBuilder()
                                .setApplicationID(applicationId)
                                .setPolicyStore(
                                        PolicyStore.newBuilder()
                                                .setType(policyStoreType)
                                                .setID(policyStoreId)
                                                .build()
                                )
                                .setPrincipal(
                                        Principal.newBuilder()
                                                .setType(principalType)
                                                .setID(principalId)
                                                .build()
                                )
                                .build()
                )
                .setSubject(
                        Subject.newBuilder()
                                .setType(subjectType)
                                .setID(subjectId)
                                .build()
                )
                .setResource(
                        Resource.newBuilder()
                                .setType(resourceType)
                                .setID(resourceId)
                                .build()
                )
                .setAction(
                        Action.newBuilder()
                                .setName(actionName)
                                .build()
                )
                .build();

        // Call the stub
        return blockingStub.authorizationCheck(request);
    }

    // =============================================================
    // Example of a method that accepts an AuthorizationCheckRequest 
    // complete object, if you want to pass all the fields (including "Context", etc.)
    // =============================================================
    /**
     * Checks whether the given subject is authorized to perform the given action on the given resource,
     * according to the policy defined in the given policy store.
     *
     * @param request the request containing all the necessary information
     * @return the result of the authorization check
     */
    public AuthorizationCheckResponse checkAuthorization(AuthorizationCheckRequest request) {
        return blockingStub.authorizationCheck(request);
    }
}

