package com.permguard.pep.server;

import com.permguard.pep.internal.proto.AuthorizationCheck;
import com.permguard.pep.internal.proto.V1PDPServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * A simple gRPC server that simulates an authorization decision service.
 */
public class AZServer {

    private Server server;

    /**
     * Starts the gRPC server on the specified port.
     *
     * @param port The port to start the server on.
     * @throws IOException If the server fails to start.
     */
    public void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new V1PDPServiceImpl())
                .build()
                .start();

        System.out.println("✅ gRPC Server started on port " + port);
    }

    /**
     * Stops the gRPC server.
     */
    public void stop() {
        if (server != null) {
            server.shutdown();
            System.out.println("⛔ gRPC Server stopped.");
        }
    }

    /**
     * Main method to start the server.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        AZServer server = new AZServer();
        server.start(9094);
        Thread.currentThread().join();
    }

    /**
     * Implementation of the gRPC authorization service.
     */
    private static class V1PDPServiceImpl extends V1PDPServiceGrpc.V1PDPServiceImplBase {

        @Override
        public void authorizationCheck(AuthorizationCheck.AuthorizationCheckRequest request,
                                       io.grpc.stub.StreamObserver<AuthorizationCheck.AuthorizationCheckResponse> responseObserver) {
            boolean decision = request.getRequestID().hashCode() % 2 == 0; // Mock decision logic

            AuthorizationCheck.AuthorizationCheckResponse response = AuthorizationCheck.AuthorizationCheckResponse.newBuilder()
                    .setDecision(decision)
                    .setRequestID(request.getRequestID())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
