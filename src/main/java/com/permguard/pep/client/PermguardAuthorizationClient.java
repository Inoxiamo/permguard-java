package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.proto.V1PDPServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PermguardAuthorizationClient {

    private final PermguardConfig config;
    private ManagedChannel channel;
    private V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;

    /**
     * Costruttore che riceve un oggetto di configurazione.
     * All’interno istanzia il channel e lo stub.
     */
    public PermguardAuthorizationClient(PermguardConfig config) {
        this.config = config;
        initChannelAndStub();
    }

    /**
     * Inizializza (o re-inizializza) il canale e lo stub sulla base della config.
     */
    private void initChannelAndStub() {
        ManagedChannelBuilder<?> builder = ManagedChannelBuilder
                .forAddress(config.getHost(), config.getPort());

        if (config.isUsePlaintext()) {
            builder.usePlaintext();
        } else {
            // Se serve TLS, configuralo qui
        }

        this.channel = builder.build();
        this.blockingStub = V1PDPServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Espone un metodo di utility per chiudere il canale
     * quando non serve più (oppure lo chiudi a fine applicazione).
     */
    public void shutdown() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }

    /**
     * checkAuthorization usando i parametri principali.
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
        // Costruisci la richiesta
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

        // Invoca lo stub
        return blockingStub.authorizationCheck(request);
    }

    // =============================================================
    // Esempio di metodo che accetta già un AuthorizationCheckRequest 
    // completo, se vuoi passare tu tutti i campi (inclusi "Context", etc.)
    // =============================================================
    public AuthorizationCheckResponse checkAuthorization(AuthorizationCheckRequest request) {
        return blockingStub.authorizationCheck(request);
    }
}
