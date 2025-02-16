/**
 *   Copyright 2024 Nitro Agility S.r.l.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  SPDX-License-Identifier: Apache-2.0
  */



package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.exception.AuthorizationException;
import com.permguard.pep.proto.AuthorizationCheck.AuthorizationCheckRequest;
import com.permguard.pep.proto.AuthorizationCheck.AuthorizationCheckResponse;
import com.permguard.pep.proto.V1PDPServiceGrpc;
import com.permguard.pep.representation.request.AuthRequestPayload;
import com.permguard.pep.representation.response.AuthResponsePayload;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.permguard.pep.mapping.MappingClass.mapAuthResponsePayload;
import static com.permguard.pep.mapping.MappingClass.mapAuthorizationCheckRequest;

/**
 * Client for interacting with the Policy Decision Point.
 */
public class PermguardAuthorizationClient {

    private static final Logger logger = LoggerFactory.getLogger(PermguardAuthorizationClient.class);


    private final PermguardConfig config;
    private ManagedChannel channel;
    V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;

    /**
     * Constructs a new client with the given configuration.
     * Initializes the channel and stub.
     *
     * @param config the configuration for the client
     */
    public PermguardAuthorizationClient(PermguardConfig config) {
        this.config = config;
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
     * @throws AuthorizationException if an error occurs during the authorization process.
     */
    public AuthResponsePayload checkAuthorization(AuthRequestPayload authRequestPayload) {
        try {
            logger.debug("Mapping authorization check request.");
            AuthorizationCheckRequest request = mapAuthorizationCheckRequest(authRequestPayload);
            logger.debug("Authorization check request built: {}", request);
            logger.debug("Sending request to authorization service.");
            AuthorizationCheckResponse response = blockingStub.authorizationCheck(request);
            logger.debug("Mapping response to AuthResponsePayload.");
            AuthResponsePayload authResponsePayload = mapAuthResponsePayload(response);
            return authResponsePayload;

        } catch (StatusRuntimeException e) {
            logger.error("gRPC error occurred during authorization check: {}", e.getMessage(), e);
            throw new AuthorizationException("Authorization check failed due to gRPC error.", e);

        } catch (Exception e) {
            logger.error("Unexpected error occurred during authorization check: {}", e.getMessage(), e);
            throw new AuthorizationException("An unexpected error occurred.", e);
        }
    }
}

