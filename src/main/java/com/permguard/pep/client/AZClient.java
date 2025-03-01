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

import com.permguard.pep.config.AZConfig;
import com.permguard.pep.exception.AuthorizationException;
import com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest;
import com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckResponse;
import com.permguard.pep.internal.proto.V1PDPServiceGrpc;
import com.permguard.pep.mapping.AuthorizationCheckRequestMapper;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * Client for interacting with the Policy Decision Point.
 */
public class AZClient {

    private final AZConfig config;
    private ManagedChannel channel;
    private AuthorizationCheckRequestMapper mapper;
    V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;

    /**
     * Constructs a new client with the given configuration.
     * Initializes the channel and stub.
     *
     * @param config the configuration for the client
     */
    public AZClient(AZConfig config) {
        this.config = config;
        ManagedChannelBuilder<?> builder = ManagedChannelBuilder
                .forAddress(config.getHost(), config.getPort());
        if (config.isUsePlaintext()) {
            builder.usePlaintext();
        }
        this.channel = builder.build();
        this.mapper = mapper;
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
     * Performs an authorization check against the PDP using a pre-built AZRequest.
     *
     * @param azRequest the pre-built authorization request
     * @return the authorization response
     * @throws AuthorizationException if there is an error during the authorization check
     */
    public AuthResponsePayload check(AZRequest azRequest) {
            AuthorizationCheckRequest request = mapper.map(azRequest);
            return getAuthResponsePayload(request);
    }

    /**
     * Performs an authorization check against the PDP using a pre-built AZAtomicRequest.
     *
     * @param azRequest the pre-built authorization request
     * @return the authorization response
     * @throws AuthorizationException if there is an error during the authorization check
     */
    public AuthResponsePayload check(AZAtomicRequest azRequest) {
            AuthorizationCheckRequest request = mapper.map(azRequest);
            return getAuthResponsePayload(request);
    }

    private AuthResponsePayload getAuthResponsePayload(AuthorizationCheckRequest request) {
        try {
            AuthorizationCheckResponse response = blockingStub.authorizationCheck(request);
            AuthResponsePayload authResponsePayload = mapper.mapAuthResponsePayload(response);
            return authResponsePayload;
        } catch (StatusRuntimeException e) {
            throw new AuthorizationException("Authorization check failed due to gRPC error.", e);
        } catch (Exception e) {
            throw new AuthorizationException("An unexpected error occurred.", e);
        }
    }

}

