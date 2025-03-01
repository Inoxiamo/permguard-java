package com.permguard.pep.mapping;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.permguard.pep.internal.proto.AuthorizationCheck;
import com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationCheckRequest;
import com.permguard.pep.internal.proto.AuthorizationCheck.AuthorizationmodelRequest;
import com.permguard.pep.internal.proto.AuthorizationCheck.PolicyStore;
import com.permguard.pep.internal.proto.AuthorizationCheck.Principal;
import com.permguard.pep.internal.proto.AuthorizationCheck.Subject;
import com.permguard.pep.internal.proto.AuthorizationCheck.Resource;
import com.permguard.pep.internal.proto.AuthorizationCheck.Action;
import com.permguard.pep.internal.proto.AuthorizationCheck.EvaluationRequest;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;
import com.permguard.pep.representation.response.ContextDetail;
import com.permguard.pep.representation.response.EvaluationResponseDetail;
import com.permguard.pep.representation.response.ReasonDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Maps both atomic and non-atomic authorization requests (AZAtomicRequest and AZRequest)
 * to a Protocol Buffers AuthorizationCheckRequest.
 *
 * The required parameters are:
 * - zoneId: used for the ZoneID field in AuthorizationmodelRequest.
 * - policyCodeId: used for the ID field of PolicyStore.
 *
 * Default values:
 * - PolicyStore.Type is set to "ledger".
 * - If there are no Evaluations (in AZRequest), the top-level Subject, Resource, Action, and Context fields
 *   will be set to empty strings or empty messages.
 */
public class AuthorizationCheckRequestMapper {

    // --- Helper methods for Protobuf conversion ---

    private static Struct mapToStruct(Map<String, Object> map) {
        Struct.Builder structBuilder = Struct.newBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            structBuilder.putFields(entry.getKey(), convertToProtoValue(entry.getValue()));
        }
        return structBuilder.build();
    }

    @SuppressWarnings("unchecked")
    private static Value convertToProtoValue(Object obj) {
        if (obj instanceof String) {
            return Value.newBuilder().setStringValue((String) obj).build();
        } else if (obj instanceof Integer) {
            return Value.newBuilder().setNumberValue((Integer) obj).build();
        } else if (obj instanceof Boolean) {
            return Value.newBuilder().setBoolValue((Boolean) obj).build();
        } else if (obj instanceof Double) {
            return Value.newBuilder().setNumberValue((Double) obj).build();
        } else if (obj instanceof Map) {
            return Value.newBuilder().setStructValue(mapToStruct((Map<String, Object>) obj)).build();
        } else if (obj instanceof List) {
            Value.Builder listBuilder = Value.newBuilder();
            for (Object item : (List<?>) obj) {
                listBuilder.getListValueBuilder().addValues(convertToProtoValue(item));
            }
            return listBuilder.build();
        }
        return Value.newBuilder().setNullValueValue(0).build();
    }

    /**
     * Maps an Evaluation to an EvaluationRequest (Protobuf).
     *
     * @param eval the Evaluation object to map
     * @return the corresponding EvaluationRequest object
     */
    private static EvaluationRequest mapEvaluation(Evaluation eval) {
        // Mapping Subject: we use the Subject fields present in the Evaluation
        Subject.Builder subjBuilder = Subject.newBuilder()
                .setType(eval.getSubject().getKind())
                .setID(eval.getSubject().getEmail());
        if (eval.getSubject().getSource() != null) {
            subjBuilder.setSource(eval.getSubject().getSource());
        }
        if (!eval.getSubject().getProperties().isEmpty()) {
            subjBuilder.setProperties(mapToStruct(eval.getSubject().getProperties()));
        }
        // Mapping Resource
        Resource.Builder resBuilder = Resource.newBuilder()
                .setType(eval.getResource().getType())
                .setID(eval.getResource().getId());
        if (!eval.getResource().getProperties().isEmpty()) {
            resBuilder.setProperties(mapToStruct(eval.getResource().getProperties()));
        }
        // Mapping Action
        Action.Builder actBuilder = Action.newBuilder()
                .setName(eval.getAction().getName());
        if (!eval.getAction().getProperties().isEmpty()) {
            actBuilder.setProperties(mapToStruct(eval.getAction().getProperties()));
        }
        EvaluationRequest.Builder evalReqBuilder = EvaluationRequest.newBuilder()
                .setRequestID(eval.getRequestId())
                .setSubject(subjBuilder.build())
                .setResource(resBuilder.build())
                .setAction(actBuilder.build());
        if (!eval.getContext().isEmpty()) {
            evalReqBuilder.setContext(mapToStruct(eval.getContext()));
        }
        return evalReqBuilder.build();
    }

    // --- Mapping methods ---

    /**
     * Maps an AZAtomicRequest to an AuthorizationCheckRequest.
     *
     * @param req the atomic request to map
     * @return the corresponding AuthorizationCheckRequest object
     */
    public static AuthorizationCheckRequest map(AZAtomicRequest req) {
        // Creation of PolicyStore: type "ledger", ID = policyCodeId
        PolicyStore policyStore = PolicyStore.newBuilder()
                .setType("ledger")
                .setID(req.getPolicyCodeId())
                .build();

        // Mapping Principal
        Principal.Builder principalBuilder = Principal.newBuilder()
                .setType("user")
                .setID(req.getPrincipal().getEmail());
        if (req.getPrincipal().getSource() != null) {
            principalBuilder.setSource(req.getPrincipal().getSource());
        }

        // Mapping Subject (dalla richiesta atomica: subjectKind, subjectSource, subjectProperties)
        Subject.Builder subjBuilder = Subject.newBuilder()
                .setType(req.getSubjectKind())
                .setID(req.getPrincipal().getEmail()); // By default, the principal's email is used as ID
        if (req.getSubjectSource() != null) {
            subjBuilder.setSource(req.getSubjectSource());
        }
        if (!req.getSubjectProperties().isEmpty()) {
            subjBuilder.setProperties(mapToStruct(req.getSubjectProperties()));
        }

        // Mapping Resource
        Resource.Builder resBuilder = Resource.newBuilder()
                .setType(req.getResource().getType())
                .setID(req.getResource().getId());
        if (!req.getResource().getProperties().isEmpty()) {
            resBuilder.setProperties(mapToStruct(req.getResource().getProperties()));
        }

        // Mapping Action
        Action.Builder actBuilder = Action.newBuilder()
                .setName(req.getAction().getName());
        if (!req.getAction().getProperties().isEmpty()) {
            actBuilder.setProperties(mapToStruct(req.getAction().getProperties()));
        }

        // Mapping EvaluationRequest: we use the fields of the atomic request to create an evaluation
        EvaluationRequest.Builder evalBuilder = EvaluationRequest.newBuilder()
                .setRequestID(req.getRequestId())
                .setSubject(subjBuilder.build())
                .setResource(resBuilder.build())
                .setAction(actBuilder.build());
        if (!req.getContext().isEmpty()) {
            evalBuilder.setContext(mapToStruct(req.getContext()));
        }


        // Creation of AuthorizationmodelRequest with the minimum data (zoneId, PolicyStore, Principal)
        AuthorizationmodelRequest.Builder authorizationModel = AuthorizationmodelRequest.newBuilder()
                .setZoneID(req.getZoneId())
                .setPolicyStore(policyStore)
                .setPrincipal(principalBuilder.build());
        if (req.getEntities() != null && !req.getEntities().isEmpty()) {
            Map.Entry<String, List<Entity>> entry = req.getEntities().entrySet().iterator().next();
            String schema = entry.getKey();
            Entity entity = entry.getValue().get(0);
            AuthorizationCheck.Entities.Builder entitiesProtoBuilder = AuthorizationCheck.Entities.newBuilder();
            entitiesProtoBuilder.setSchema(schema);
            int i = 0;
            for (Item item : entity.getItems()) {
                entitiesProtoBuilder.addItems(i, mapEntity(item));
                i++;
            }

            authorizationModel.setEntities(entitiesProtoBuilder.build());
        }
        authorizationModel.build();


        AuthorizationCheckRequest.Builder protoRequest = AuthorizationCheckRequest.newBuilder()
                .setAuthorizationmodel(authorizationModel)
                .setRequestID(req.getRequestId())
                .setSubject(subjBuilder.build())
                .setResource(resBuilder.build())
                .setAction(actBuilder.build());
                //.addEvaluations(evalBuilder.build()
                //);
        if (!req.getContext().isEmpty()) {
            protoRequest.setContext(mapToStruct(req.getContext()));
        }
        return protoRequest.build();
    }

     /**
     * Maps an Entity object to a Protobuf Struct.
     * The resulting object will have the keys "schema" and "items", where "items" is a list of Structs
     * representing each Item.
     *
     * @param item the Item object to map
     * @return a Struct containing the mapping of the Entity
     */
    private static Struct mapEntity(Item item) {
        Map<String, Object> itemMap = new HashMap<>();
        Map<String, Object> uid = new HashMap<>();
        uid.put("type", item.getType());
        uid.put("id", item.getId());
        itemMap.put("uid", uid);
        itemMap.put("attrs", item.getAttrs());
        itemMap.put("parents", item.getParents()==null ? List.of() : item.getParents());
        return mapToStruct(itemMap);
    }

    /**
     * Maps an AZRequest (non-atomic) to an AuthorizationCheckRequest.
     *
     * If there are multiple evaluations, the top-level Subject, Resource, Action, and Context fields
     * are derived from the first evaluation.
     *
     * @param req the non-atomic request to map
     * @return the corresponding AuthorizationCheckRequest object
     */
    public static AuthorizationCheckRequest map(AZRequest req) {
        // Creation of PolicyStore with ledger
        PolicyStore policyStore = PolicyStore.newBuilder()
                .setType("ledger")
                .setID(req.getPolicyCodeId())
                .build();

        // Mapping Principal
        Principal.Builder principalBuilder = Principal.newBuilder()
                .setType("user")
                .setID(req.getPrincipal().getEmail());
        if (req.getPrincipal().getSource() != null) {
            principalBuilder.setSource(req.getPrincipal().getSource());
        }

        // If there are evaluations, we use the first one for the top-level fields
        Evaluation firstEval = req.getEvaluations().isEmpty() ? null : req.getEvaluations().get(0);
        Subject.Builder subjBuilder = Subject.newBuilder();
        Resource.Builder resBuilder = Resource.newBuilder();
        Action.Builder actBuilder = Action.newBuilder();
        Struct contextStruct = null;
        if (firstEval != null) {
            subjBuilder.setType(firstEval.getSubject().getKind())
                        .setID(firstEval.getSubject().getEmail());
            if (firstEval.getSubject().getSource() != null) {
                subjBuilder.setSource(firstEval.getSubject().getSource());
            }
            if (!firstEval.getSubject().getProperties().isEmpty()) {
                subjBuilder.setProperties(mapToStruct(firstEval.getSubject().getProperties()));
            }
            resBuilder.setType(firstEval.getResource().getType())
                      .setID(firstEval.getResource().getId());
            if (!firstEval.getResource().getProperties().isEmpty()) {
                resBuilder.setProperties(mapToStruct(firstEval.getResource().getProperties()));
            }
            actBuilder.setName(firstEval.getAction().getName());
            if (!firstEval.getAction().getProperties().isEmpty()) {
                actBuilder.setProperties(mapToStruct(firstEval.getAction().getProperties()));
            }
            if (!firstEval.getContext().isEmpty()) {
                contextStruct = mapToStruct(firstEval.getContext());
            }
        } else {
            // Default values if no evaluation exists
            subjBuilder.setType("").setID("");
            resBuilder.setType("").setID("");
            actBuilder.setName("");
        }

        // Creation of AuthorizationmodelRequest
        AuthorizationmodelRequest.Builder authorizationModel = AuthorizationmodelRequest.newBuilder()
                .setZoneID(req.getZoneId())
                .setPolicyStore(policyStore)
                .setPrincipal(principalBuilder.build());
         if (req.getEntities() != null && !req.getEntities().isEmpty()) {
            Map.Entry<String, List<Entity>> entry = req.getEntities().entrySet().iterator().next();
            String schema = entry.getKey();
            Entity entity = entry.getValue().get(0);
            AuthorizationCheck.Entities.Builder entitiesProtoBuilder = AuthorizationCheck.Entities.newBuilder();
            entitiesProtoBuilder.setSchema(schema);
            int i = 0;
            for (Item item : entity.getItems()) {
                entitiesProtoBuilder.addItems(i, mapEntity(item));
                i++;
            }
            authorizationModel.setEntities(entitiesProtoBuilder.build());
        }
        authorizationModel.build();

        AuthorizationCheckRequest.Builder protoRequest = AuthorizationCheckRequest.newBuilder()
                .setAuthorizationmodel(authorizationModel)
                .setRequestID(firstEval == null ? "" : firstEval.getRequestId())
                .setSubject(subjBuilder.build())
                .setResource(resBuilder.build())
                .setAction(actBuilder.build());
        if (contextStruct != null) {
            protoRequest.setContext(contextStruct);
        }
        // Add all mapped Evaluations
        for (Evaluation eval : req.getEvaluations()) {
            protoRequest.addEvaluations(mapEvaluation(eval));
        }
        return protoRequest.build();
    }

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

}
