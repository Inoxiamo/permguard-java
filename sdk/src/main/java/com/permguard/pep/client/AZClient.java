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
import com.permguard.pep.internal.proto.AuthorizationCheck;
import com.permguard.pep.internal.proto.V1PDPServiceGrpc;
import com.permguard.pep.model.request.*;
import com.permguard.pep.model.response.AZResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

/**
 * Client for interacting with the Policy Decision Point (PDP) authorization service.
 */
public class AZClient {
    private final AZConfig config;
    private final ManagedChannel channel;
    private final V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;
    private final Mapper mapper;

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
        this.mapper = new Mapper();
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
     * Performs an authorization check against the PDP.
     *
     * @param requestPayload The request payload containing the authorization check details.
     * @return The response from the PDP.
     */
    public AZResponse check(AZRequest requestPayload) {
        try {
            // Convert to gRPC format
            AuthorizationCheck.AuthorizationCheckRequest grpcRequest = mapper.mapAuthorizationCheckRequest(requestPayload);
            AuthorizationCheck.AuthorizationCheckResponse grpcResponse = blockingStub.authorizationCheck(grpcRequest);

            // Convert gRPC response back to AZResponse
            return mapper.mapAuthResponsePayload(grpcResponse);

        } catch (StatusRuntimeException e) {
            throw new AuthorizationException("Authorization check failed due to gRPC error.", e);
        } catch (Exception e) {
            throw new AuthorizationException("An unexpected error occurred.", e);
        }
    }

}

