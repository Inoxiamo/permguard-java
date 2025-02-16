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

package com.permguard.pep.mapping;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.permguard.pep.proto.AuthorizationCheck;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;
import com.permguard.pep.representation.response.ContextDetail;
import com.permguard.pep.representation.response.EvaluationResponseDetail;
import com.permguard.pep.representation.response.ReasonDetail;

import java.util.*;

/**
 * Utility class for mapping between DTOs and Protocol Buffer objects.
 */
public class MappingClass {


    /**
     * Maps a {@link AuthorizationCheck.AuthorizationCheckResponse} to an {@link AuthResponsePayload}.
     *
     * @param response The Protocol Buffer response object.
     * @return The mapped DTO object.
     */
    public static AuthResponsePayload mapAuthResponsePayload(AuthorizationCheck.AuthorizationCheckResponse response) {
        AuthResponsePayload authResponsePayload = new AuthResponsePayload();

        boolean decision = response.getDecision();

        ContextDetail contextDetail = getContextDetail(response.getContext());

        List<AuthorizationCheck.EvaluationResponse> evaluationResponses = response.getEvaluationsList();
        List<EvaluationResponseDetail> evaluationResponsesDetail = new ArrayList<>();
        for(AuthorizationCheck.EvaluationResponse evaluationResponse : evaluationResponses){
            boolean decisionEval = evaluationResponse.getDecision();
            ContextDetail contextDetailEval = getContextDetail(evaluationResponse.getContext());
            EvaluationResponseDetail evaluationResponseDetail = new EvaluationResponseDetail(decisionEval, contextDetailEval);
            evaluationResponsesDetail.add(evaluationResponseDetail);
        }

        authResponsePayload.setDecision(decision);
        authResponsePayload.setContext(contextDetail);
        authResponsePayload.setEvaluations(evaluationResponsesDetail);
        authResponsePayload.setRequestId(response.getRequestID());
        return authResponsePayload;
    }

     /**
     * Maps a {@link AuthorizationCheck.ContextResponse} to a {@link ContextDetail}.
     *
     * @param contextResponse The Protocol Buffer context response object.
     * @return The mapped DTO object.
     */
    private static ContextDetail getContextDetail(AuthorizationCheck.ContextResponse contextResponse) {
        AuthorizationCheck.ReasonResponse reasonResponseAdm = contextResponse.getReasonAdmin();
        AuthorizationCheck.ReasonResponse reasonResponseUsr = contextResponse.getReasonUser();
        ReasonDetail reasonDetailAdm = new ReasonDetail(reasonResponseAdm.getCode(), reasonResponseAdm.getMessage());
        ReasonDetail reasonDetailUsr = new ReasonDetail(reasonResponseUsr.getCode(), reasonResponseUsr.getMessage());
        return new ContextDetail(contextResponse.getID(), reasonDetailAdm, reasonDetailUsr);
    }

    /**
     * Maps an {@link Request} to an {@link AuthorizationCheck.AuthorizationCheckRequest}.
     *
     * @param requestPayload The DTO request object.
     * @return The mapped Protocol Buffer request object.
     */
    public static AuthorizationCheck.AuthorizationCheckRequest mapAuthorizationCheckRequest(Request requestPayload) {


        return AuthorizationCheck.AuthorizationCheckRequest.newBuilder()
                .setAuthorizationmodel(
                        AuthorizationCheck.AuthorizationmodelRequest.newBuilder()
                                .setZoneID(Long.parseLong(requestPayload.getauthModelDetail().getPolicyStore().getZoneId()))
                                .setPolicyStore(
                                        getAuthorizationCheckPolicyStore(requestPayload.getauthModelDetail().getPolicyStore())
                                )
                                .setPrincipal(
                                        getAuthorizationCheckPrincipal(requestPayload.getauthModelDetail().getPrincipal())
                                )
                                .setEntities(
                                        getAuthorizationCheckEntities(requestPayload.getauthModelDetail().getEntityDetail())
                                )
                                .build()
                )
                .setSubject(
                        getAuthorizationCheckSubject(requestPayload.getSubject())
                )
                .setResource(
                        getAuthorizationCheckResource(requestPayload.getResource())
                )
                .setAction(
                        getAuthorizationCheckAction(requestPayload.getAction())
                ).addAllEvaluations(
                    Optional.ofNullable(requestPayload.getEvaluations())
                            .orElseGet(List::of)
                            .stream()
                            .map(MappingClass::mapToEvaluationRequest)
                            .toList()
                )
                .setContext(mapToStruct(requestPayload.getContext()))
                .setRequestID(String.valueOf(UUID.randomUUID()))
                .build();
    }

    private static AuthorizationCheck.EvaluationRequest mapToEvaluationRequest(Evaluation detail) {
    return AuthorizationCheck.EvaluationRequest.newBuilder()
            .setSubject(getAuthorizationCheckSubject(detail.getSubject()))
            .setResource(getAuthorizationCheckResource(detail.getResource()))
            .setAction(getAuthorizationCheckAction(detail.getAction()))
            .setContext(mapToStruct(detail.getContext()))
            .build();
    }



    private static AuthorizationCheck.PolicyStore getAuthorizationCheckPolicyStore(PolicyStore policyStore) {
        return AuthorizationCheck.PolicyStore.newBuilder()
                .setType(policyStore.getType())
                .setID(policyStore.getId())
                .build();
    }

    private static AuthorizationCheck.Entities getAuthorizationCheckEntities(Entity entity) {
        return AuthorizationCheck.Entities.newBuilder()
                .setSchema(entity.getSchema())
                .addAllItems(getAuthorizationCheckItems(entity.getItems()))
                .build();
    }

    private static List<Struct> getAuthorizationCheckItems(List<Item> itemDetails) {
       List<Struct> structList = new ArrayList<>();

        for (Item itemDetail : itemDetails) {
            Struct itemStruct;
            // Creazione della mappa per ogni ItemDetails
            Map<String, Object> itemMap = new HashMap<>();

            // Creazione della mappa per l'oggetto UID
            Map<String, Object> uidMap = new HashMap<>();
            uidMap.put("type", itemDetail.getType());
            uidMap.put("id", itemDetail.getId());

            // Aggiunta delle proprietà alla mappa dell'item
            itemMap.put("uid", uidMap);
            itemMap.put("attrs", itemDetail.getAttrs());
            itemMap.put("parents", itemDetail.getParents());

            itemStruct = mapToStruct(itemMap);
            structList.add(itemStruct);
        }
        return structList;
    }

    private static AuthorizationCheck.Principal getAuthorizationCheckPrincipal(Principal principal) {
        AuthorizationCheck.Principal.Builder principalBuilder = AuthorizationCheck.Principal.newBuilder()
                .setType(principal.getType())
                .setSource(principal.getSource())
                .setID(principal.getId());
        if(principal.getAccessToken() != null){
            principalBuilder.setAccessToken(principal.getAccessToken());
        }
        if(principal.getIdentityToken() != null){
            principalBuilder.setIdentityToken(principal.getIdentityToken());
        }
        return principalBuilder.build();
    }

    private static AuthorizationCheck.Subject getAuthorizationCheckSubject(Subject subject) {
        return AuthorizationCheck.Subject.newBuilder()
                .setType(subject.getType())
                .setID(subject.getId())
                .setSource(subject.getSource())
                .setProperties(mapToStruct(subject.getProperties()))
                .build();
    }

    private static AuthorizationCheck.Resource getAuthorizationCheckResource(Resource resource) {
        return AuthorizationCheck.Resource.newBuilder()
                .setType(resource.getType())
                .setID(resource.getId())
                .setProperties(mapToStruct(resource.getProperties()))
                .build();
    }

    private static AuthorizationCheck.Action getAuthorizationCheckAction(Action action) {
        return AuthorizationCheck.Action.newBuilder()
                .setName(action.getName())
                .setProperties(mapToStruct(action.getProperties()))
                .build();
    }

    private static Struct mapToStruct(Map<String, Object> map) {
        Struct.Builder structBuilder = Struct.newBuilder();
        Optional.ofNullable(map).ifPresent(m ->
            m.forEach((key, value) -> structBuilder.putFields(key, convertToValue(value)))
        );
        return structBuilder.build();
    }

    // Metodo per convertire Object in Protobuf Value
    private static Value convertToValue(Object value) {
        if (value instanceof String) {
            return Value.newBuilder().setStringValue((String) value).build();
        } else if (value instanceof Number) {
            return Value.newBuilder().setNumberValue(((Number) value).doubleValue()).build();
        } else if (value instanceof Boolean) {
            return Value.newBuilder().setBoolValue((Boolean) value).build();
        } else if (value == null) {
            return Value.newBuilder().setNullValueValue(0).build();
        } else if (value instanceof Map<?,?>) {
            Struct.Builder structBuilder = Struct.newBuilder();
            ((Map<?, ?>) value).forEach((k, v) -> structBuilder.putFields(k.toString(), convertToValue(v)));
            return Value.newBuilder().setStructValue(structBuilder.build()).build();
        } else if (value instanceof List) {
            com.google.protobuf.ListValue.Builder listBuilder = com.google.protobuf.ListValue.newBuilder();
            ((List<?>) value).forEach(item -> listBuilder.addValues(convertToValue(item)));
            return Value.newBuilder().setListValue(listBuilder.build()).build();
    }
        throw new IllegalArgumentException("Unsupported type: " + value.getClass());
    }
}
