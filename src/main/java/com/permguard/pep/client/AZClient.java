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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import com.permguard.pep.config.AZConfig;
import com.permguard.pep.exception.AuthorizationException;
import com.permguard.pep.internal.proto.AuthorizationCheck;
import com.permguard.pep.internal.proto.V1PDPServiceGrpc;
import com.permguard.pep.model.request.*;
import com.permguard.pep.model.response.AZResponse;
import com.permguard.pep.model.response.ContextResponse;
import com.permguard.pep.model.response.EvaluationResponse;
import com.permguard.pep.model.response.ReasonResponse;
import com.permguard.pep.utils.GrpcStructMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.IOException;
import java.util.Map;

/**
 * Client for interacting with the Policy Decision Point (PDP) authorization service.
 */
public class AZClient {
    private final AZConfig config;
    private final ManagedChannel channel;
    private final V1PDPServiceGrpc.V1PDPServiceBlockingStub blockingStub;

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
     * @param requestPayload The request payload containing the authorization check details.
     * @return The response from the PDP.
     */
    public AZResponse check(AZRequest requestPayload) {
        try {
            // Convert to gRPC format
            AuthorizationCheck.AuthorizationCheckRequest grpcRequest = mapAuthorizationCheckRequest(requestPayload);
            AuthorizationCheck.AuthorizationCheckResponse grpcResponse = blockingStub.authorizationCheck(grpcRequest);

            // Convert gRPC response back to AZResponse
            return mapAuthResponsePayload(grpcResponse);

        } catch (StatusRuntimeException e) {
            throw new AuthorizationException("Authorization check failed due to gRPC error.", e);
        } catch (Exception e) {
            throw new AuthorizationException("An unexpected error occurred.", e);
        }
    }


    /**
     * Converts an AZRequest into a gRPC-compatible AuthorizationCheckRequest.
     *
     * @param request The AZRequest.
     * @return A gRPC-compatible AuthorizationCheckRequest.
     */
    private AuthorizationCheck.AuthorizationCheckRequest mapAuthorizationCheckRequest(AZRequest request) {
        AuthorizationCheck.AuthorizationCheckRequest.Builder requestBuilder = AuthorizationCheck.AuthorizationCheckRequest.newBuilder()
                .setRequestID(request.getRequestId() != null ? request.getRequestId() : "")
                .setAuthorizationModel(mapAuthorizationModel(request.getAuthorizationModel()));

        if (request.getSubject() != null) {
            requestBuilder.setSubject(mapSubject(request.getSubject()));
        }
        if (request.getResource() != null) {
            requestBuilder.setResource(mapResource(request.getResource()));
        }
        if (request.getAction() != null) {
            requestBuilder.setAction(mapAction(request.getAction()));
        }
        if (request.getContext() != null) {
            requestBuilder.setContext(GrpcStructMapper.toGrpcStruct(request.getContext())); // Use Struct directly
        }
        if (request.getEvaluations() != null) {
            for (Evaluation eval : request.getEvaluations()) {
                requestBuilder.addEvaluations(mapEvaluation(eval));
            }
        }

        return requestBuilder.build();
    }

    /**
     * Converts an AuthorizationCheckResponse into an AZResponse.
     *
     * @param response The gRPC response.
     * @return An AZResponse instance.
     */
    private AZResponse mapAuthResponsePayload(AuthorizationCheck.AuthorizationCheckResponse response) {
        return new AZResponse(
                response.getDecision(),
                response.hasRequestID() ? response.getRequestID() : "",
                response.hasContext() ? mapContextResponse(response.getContext()) : null,
                response.getEvaluationsList().stream().map(this::mapEvaluationResponse).toList()
        );
    }

    /** MAPPING HELPERS **/

    private AuthorizationCheck.AuthorizationModelRequest mapAuthorizationModel(AZModel model) {
        return AuthorizationCheck.AuthorizationModelRequest.newBuilder()
                .setZoneID(model.getZoneId())
                .setPolicyStore(mapPolicyStore(model.getPolicyStore()))
                .setPrincipal(mapPrincipal(model.getPrincipal()))
                .setEntities(mapEntities(model.getEntities()))
                .build();
    }

    private AuthorizationCheck.PolicyStore mapPolicyStore(PolicyStore store) {
        return AuthorizationCheck.PolicyStore.newBuilder()
                .setKind(store.getKind())
                .setID(store.getId())
                .build();
    }

    private AuthorizationCheck.Principal mapPrincipal(Principal principal) {
        return AuthorizationCheck.Principal.newBuilder()
                .setType(principal.getType())
                .setID(principal.getId())
                .setSource(principal.getSource())
                .build();
    }

    private AuthorizationCheck.Entities mapEntities(Entities entities) {
        return AuthorizationCheck.Entities.newBuilder()
                .setSchema(entities.getSchema())
                .build();
    }

    private AuthorizationCheck.Subject mapSubject(Subject subject) {
        return AuthorizationCheck.Subject.newBuilder()
                .setType(subject.getType())
                .setID(subject.getId())
                .setSource(subject.getSource())
                .setProperties(GrpcStructMapper.toGrpcStruct(subject.getProperties()))
                .build();
    }

    private AuthorizationCheck.Resource mapResource(Resource resource) {
        return AuthorizationCheck.Resource.newBuilder()
                .setType(resource.getType())
                .setID(resource.getId())
                .setProperties(GrpcStructMapper.toGrpcStruct(resource.getProperties()))
                .build();
    }

    private AuthorizationCheck.Action mapAction(Action action) {
        return AuthorizationCheck.Action.newBuilder()
                .setName(action.getName())
                .setProperties(GrpcStructMapper.toGrpcStruct(action.getProperties()))
                .build();
    }

    private AuthorizationCheck.EvaluationRequest mapEvaluation(Evaluation evaluation) {
        AuthorizationCheck.EvaluationRequest.Builder builder = AuthorizationCheck.EvaluationRequest.newBuilder()
                .setRequestID(evaluation.getRequestId() != null ? evaluation.getRequestId() : "")
                .setSubject(mapSubject(evaluation.getSubject()))
                .setResource(mapResource(evaluation.getResource()))
                .setAction(mapAction(evaluation.getAction()));

        if (evaluation.getContext() != null) {
            builder.setContext(GrpcStructMapper.toGrpcStruct(evaluation.getContext()));
        }

        return builder.build();
    }

    private EvaluationResponse mapEvaluationResponse(AuthorizationCheck.EvaluationResponse response) {
        return new EvaluationResponse(
                response.getDecision(),
                response.hasRequestID() ? response.getRequestID() : "",
                response.hasContext() ? mapContextResponse(response.getContext()) : null
        );
    }



    private ContextResponse mapContextResponse(AuthorizationCheck.ContextResponse grpcContext) {
        return new ContextResponse(
                grpcContext.getID(),
                grpcContext.hasReasonAdmin() ? mapReasonResponse(grpcContext.getReasonAdmin()) : null,
                grpcContext.hasReasonUser() ? mapReasonResponse(grpcContext.getReasonUser()) : null
        );
    }


    private ReasonResponse mapReasonResponse(AuthorizationCheck.ReasonResponse grpcReason) {
        return new ReasonResponse(
                grpcReason.getCode(),
                grpcReason.getMessage()
        );
    }


    /**
     * Converts a Java Map to a Protobuf Struct.
     *
     * @param map The Java Map to convert.
     * @return A Protobuf Struct representation of the map.
     */
    public static Struct mapToStruct(Map<String, Object> map) {
        try {
            String json = new ObjectMapper().writeValueAsString(map);
            Struct.Builder structBuilder = Struct.newBuilder();
            JsonFormat.parser().merge(json, structBuilder);
            return structBuilder.build();
        } catch (IOException e) {
            System.err.println("❌ Failed to convert Map to Struct: " + e.getMessage());
            return Struct.getDefaultInstance();
        }
    }




}

