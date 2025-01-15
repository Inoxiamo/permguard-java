package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.proto.AuthorizationCheck.*;
import com.permguard.pep.proto.V1PDPServiceGrpc;
import com.permguard.pep.representation.request.AuthRequestPayload;
import com.permguard.pep.representation.response.AuthResponsePayload;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static com.permguard.pep.mapping.MappingClass.mapAuthResponsePayload;
import static com.permguard.pep.mapping.MappingClass.mapAuthorizationCheckRequest;

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
     * Verifies authorization based on the provided request payload.
     *
     * @param authRequestPayload the request payload containing authorization details.
     * @return {@code AuthResponsePayload} the response payload containing the result of the authorization check.
     *
     * This method performs the following steps:
     * <ol>
     *   <li>Builds the request using the {@link #mapAuthorizationCheckRequest} method.</li>
     *   <li>Invokes the gRPC authorization check via {@code blockingStub.authorizationCheck}.</li>
     *   <li>Maps the response into an {@code AuthResponsePayload} object using the {@link #mapAuthResponsePayload} method.</li>
     * </ol>
     */
    public AuthResponsePayload checkAuthorization(
            AuthRequestPayload authRequestPayload
    ) {
        // Build the request
        AuthorizationCheckRequest request = mapAuthorizationCheckRequest(authRequestPayload);
        // Call the stub
        AuthorizationCheckResponse response = blockingStub.authorizationCheck(request);
        // Map the response
        AuthResponsePayload authResponsePayload = mapAuthResponsePayload(response);
        return authResponsePayload;
    }




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

