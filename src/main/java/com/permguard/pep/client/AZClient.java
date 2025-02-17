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
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.Map;

import static com.permguard.pep.mapping.MappingClass.mapAuthResponsePayload;
import static com.permguard.pep.mapping.MappingClass.mapAuthorizationCheckRequest;

/**
 * Client for interacting with the Policy Decision Point.
 */
public class AZClient {

    private final AZConfig config;
    private ManagedChannel channel;
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
     * @param policyStore the policy store
     * @param action the action being performed
     * @param principal the principal performing the action
     * @param resource the resource being accessed
     * @param entity entity details
     * @param subject the subject of the authorization check
     * @return the authorization response
     * @throws AuthorizationException if there is an error during the authorization check
     */
    public AuthResponsePayload check(PolicyStore policyStore, Action action, Principal principal
            , Resource resource, Entity entity, Subject subject, Map<String, Object> context) {
        try {
            //TODO: missing evaluation
            AuthModel authModelDetail = new AuthModel.Builder(entity, policyStore, principal).build();
            Request requestPayload = new Request.Builder(authModelDetail, subject, resource, action
                , null, context).build();
            AuthorizationCheckRequest request = mapAuthorizationCheckRequest(requestPayload);
            AuthorizationCheckResponse response = blockingStub.authorizationCheck(request);
            AuthResponsePayload authResponsePayload = mapAuthResponsePayload(response);
            return authResponsePayload;

        } catch (StatusRuntimeException e) {
            throw new AuthorizationException("Authorization check failed due to gRPC error.", e);

        } catch (Exception e) {
            throw new AuthorizationException("An unexpected error occurred.", e);
        }
    }
}

