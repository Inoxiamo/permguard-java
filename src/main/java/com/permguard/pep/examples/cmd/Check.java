package com.permguard.pep.examples.cmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.client.AZClient;
import com.permguard.pep.config.AZConfig;
import com.permguard.pep.model.request.AZRequest;
import com.permguard.pep.model.response.AZResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for testing authorization requests, equivalent to check.py in Python.
 */
public class Check {

    private static final String JSON_FILE_PATH = "requests/ok_onlyone1.json";

    public static void main(String[] args) {
        AZConfig config = new AZConfig("localhost", 9094, true);
        AZClient client = new AZClient(config);

        System.out.println("🔹 Running checkJsonRequest()");
        checkJsonRequest(client);

//        System.out.println("\n🔹 Running checkAtomicEvaluation()");
//        checkAtomicEvaluation(client);
//
//        System.out.println("\n🔹 Running checkMultipleEvaluations()");
//        checkMultipleEvaluations(client);

        client.shutdown();
    }

    /**
     * Loads a JSON authorization request and validates it.
     * Equivalent to check_json_request() in Python.
     *
     * @param client The AZClient instance to send the request.
     */
    public static void checkJsonRequest(AZClient client) {
        long startTime = System.currentTimeMillis();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Load JSON as InputStream from resources folder
            InputStream inputStream = Check.class.getClassLoader().getResourceAsStream(JSON_FILE_PATH);
            AZRequest request = objectMapper.readValue(inputStream, AZRequest.class);
            long requestBuildTime = System.currentTimeMillis();
            System.out.println("Request build time: " + (requestBuildTime - startTime) + " ms");

            long requestStartTime = System.currentTimeMillis();
            AZResponse response = client.check(request);
            long requestEndTime = System.currentTimeMillis();

            System.out.println("Request execution time: " + (requestEndTime - requestStartTime) + " ms");
            printAuthorizationResult(response);
        } catch (IOException e) {
            System.err.println("❌ Error loading JSON request: " + e.getMessage());
        }
    }





    /**
     * Creates and sends an atomic authorization request.
     * Equivalent to check_atomic_evaluation() in Python.
     *
     * @param client The AZClient instance to send the request.
     */
//    public static void checkAtomicEvaluation(AZClient client) {
//        // Create request components
//        Principal principal = new PrincipalBuilder("amy.smith@acmecorp.com")
//                .withSource("keycloak")
//                .build();
//
//        Subject subject = new SubjectBuilder("platform-creator")
//                .withType("role-actor")
//                .withProperty("isSuperUser", "true")
//                .build();
//
//        Resource resource = new ResourceBuilder("MagicFarmacia::Platform::Subscription")
//                .withId("e3a786fd07e24bfa95ba4341d3695ae8")
//                .withProperty("isEnabled", "true")
//                .build();
//
//        Action action = new ActionBuilder("MagicFarmacia::Platform::Action::create")
//                .withProperty("isEnabled", "true")
//                .build();
//
//        PolicyStore policyStore = new PolicyStore("ledger", "809257ed202e40cab7e958218eecad20");
//        Entities entities = new Entities("cedar", List.of());
//
//        AZResponse response = client.check(policyStore, action, principal, resource, entities, subject, Collections.emptyMap());
//        printAuthorizationResult(response);
//    }

    /**
     * Creates and sends multiple evaluation requests.
     * Equivalent to check_multiple_evaluations() in Python.
     *
     * @param client The AZClient instance to send the request.
     */
//    public static void checkMultipleEvaluations(AZClient client) {
//        // Create Subject
//        Subject subject = new SubjectBuilder("platform-creator")
//                .withType("role-actor")
//                .withSource("keycloak")
//                .withProperty("isSuperUser", "true")
//                .build();
//
//        // Create Resource
//        Resource resource = new ResourceBuilder("MagicFarmacia::Platform::Subscription")
//                .withId("e3a786fd07e24bfa95ba4341d3695ae8")
//                .withProperty("isEnabled", "true")
//                .build();
//
//        // Create Actions
//        Action actionView = new ActionBuilder("MagicFarmacia::Platform::Action::view")
//                .withProperty("isEnabled", "true")
//                .build();
//
//        Action actionCreate = new ActionBuilder("MagicFarmacia::Platform::Action::create")
//                .withProperty("isEnabled", "true")
//                .build();
//
//        // Create Context
//        Struct context = Struct.newBuilder()
//                .putFields("time", com.google.protobuf.Value.newBuilder().setStringValue("2025-01-23T16:17:46+00:00").build())
//                .putFields("isSubscriptionActive", com.google.protobuf.Value.newBuilder().setBoolValue(true).build())
//                .build();
//
//        // Create Evaluations
//        Evaluation evaluationView = new EvaluationBuilder(subject, resource, actionView)
//                .withRequestId("1234")
//                .withContext(context)
//                .build();
//
//        Evaluation evaluationCreate = new EvaluationBuilder(subject, resource, actionCreate)
//                .withRequestId("7890")
//                .withContext(context)
//                .build();
//
//        // Perform request
//        PolicyStore policyStore = new PolicyStore("ledger", "809257ed202e40cab7e958218eecad20");
//        Entities entities = new Entities("cedar", List.of());
//
//        AZRequest request = new AZRequestBuilder(895741663247L, "809257ed202e40cab7e958218eecad20")
//                .withPrincipal(new PrincipalBuilder("amy.smith@acmecorp.com").build())
//                .withEntitiesItems("cedar", entities)
//                .withEvaluation(evaluationView)
//                .withEvaluation(evaluationCreate)
//                .build();
//
//        AZResponse response = client.check(request);
//
//        printAuthorizationResult(response);
//    }

    /**
     * Prints the result of an authorization request.
     *
     * @param response The AZResponse received from the PDP.
     */
    public static void printAuthorizationResult(AZResponse response) {
        if (response == null) {
            System.out.println("❌ Authorization request failed.");
            return;
        }

        if (response.isDecision()) {
            System.out.println("✅ Authorization Permitted");
        } else {
            System.out.println("❌ Authorization Denied");
            if (response.getContext() != null) {
                if (response.getContext().getReasonAdmin() != null) {
                    System.out.println("-> Reason Admin: " + response.getContext().getReasonAdmin().getMessage());
                }
                if (response.getContext().getReasonUser() != null) {
                    System.out.println("-> Reason User: " + response.getContext().getReasonUser().getMessage());
                }
            }
            if (response.getEvaluations() != null) {
                for (var eval : response.getEvaluations()) {
                    if (eval.getContext() != null && eval.getContext().getReasonUser() != null) {
                        System.out.println("-> Evaluation RequestID " + eval.getRequestId()
                                + ": Reason User: " + eval.getContext().getReasonUser().getMessage());
                    }
                }
            }
        }
    }
}
