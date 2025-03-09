package com.permguard.pep.examples.cmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.builder.*;
import com.permguard.pep.client.AZClient;
import com.permguard.pep.config.AZConfig;
import com.permguard.pep.model.request.*;
import com.permguard.pep.model.response.AZResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

        System.out.println("\n🔹 Running checkAtomicRequest()");
        checkAtomicRequest(client);

        System.out.println("\n🔹 Running checkMultipleEvaluationsRequest()");
        checkMultipleEvaluationsRequest(client);

        client.shutdown();
    }

    /**
     * Loads a JSON authorization request and validates it.
     * Equivalent to check_json_request() in Python.
     *
     * @param client The AZClient instance to send the request.
     */
    public static void checkJsonRequest(AZClient client) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Load JSON as InputStream from resources folder
            InputStream inputStream = Check.class.getClassLoader().getResourceAsStream(JSON_FILE_PATH);
            AZRequest request = objectMapper.readValue(inputStream, AZRequest.class);
            long requestStartTime = System.currentTimeMillis();
            AZResponse response = client.check(request);
            long requestEndTime = System.currentTimeMillis();

            System.out.println("Request execution time: " + (requestEndTime - requestStartTime) + " ms");
            printAuthorizationResult(response);
        } catch (IOException e) {
            System.err.println("❌ Error loading JSON request: " + e.getMessage());
        }
    }





    public static void checkAtomicRequest(AZClient client) {
        try {
            long zoneId = 611159836099L;
            String policyStoreId = "f96586c317c74aaaae4ff2ba2fef0459";
            String requestId = "abc1";

            Principal principal = new PrincipalBuilder("amy.smith@acmecorp.com")
                    .withType("user")
                    .withSource("keycloak")
                    .build();

            Entities entities = new Entities("cedar", List.of(
                    Map.of(
                            "uid", Map.of("type", "MagicFarmacia::Platform::BranchInfo", "id", "subscription"),
                            "attrs", Map.of("active", true),
                            "parents", List.of()
                    )
            ));

            // ✅ Build the atomic AZRequest using the exact JSON parameters
            AZRequest request = new AZAtomicRequestBuilder(
                    zoneId,
                    policyStoreId,
                    "amy.smith@acmecorp.com",  // Subject type from JSON
                    "MagicFarmacia::Platform::Subscription",  // Resource type from JSON
                    "MagicFarmacia::Platform::Action::create"  // Action name from JSON
            )
                    .withRequestId(requestId)
                    .withPrincipal(principal)
                    .withEntitiesItems("cedar", entities)
                    .withSubjectSource("keycloak")
                    .withSubjectProperty("isSuperUser", true)
                    .withResourceId("e3a786fd07e24bfa95ba4341d3695ae8")
                    .withResourceProperty("isEnabled", true)
                    .withActionProperty("isEnabled", true)
                    .withContextProperty("time", "2025-01-23T16:17:46+00:00")
                    .withContextProperty("isSubscriptionActive", true)
                    .build();

            // Perform atomic authorization check
            long requestStartTime = System.currentTimeMillis();
            AZResponse response = client.check(request);
            long requestEndTime = System.currentTimeMillis();
            System.out.println("Request execution time: " + (requestEndTime - requestStartTime) + " ms");
            printAuthorizationResult(response);
        } catch (Exception e) {
            System.err.println("❌ Error executing atomic request: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void checkMultipleEvaluationsRequest(AZClient client) {
        try {
            // ✅ Extract values from JSON (matching your provided data)
            long zoneId = 611159836099L;
            String policyStoreId = "f96586c317c74aaaae4ff2ba2fef0459";
            String requestId = "batch-eval-001";
            String subjectId = "amy.smith@acmecorp.com";
            String subjectType = "user";
            String resourceId = "e3a786fd07e24bfa95ba4341d3695ae8";
            String resourceType = "MagicFarmacia::Platform::Subscription";

            // ✅ Create Principal
            Principal principal = new PrincipalBuilder(subjectId)
                    .withType(subjectType)
                    .withSource("keycloak")
                    .build();

            // ✅ Create Subject
            Subject subject = new SubjectBuilder(subjectId)
                    .withType(subjectType)
                    .withSource("keycloak")
                    .withProperty("isSuperUser", true)
                    .build();

            // ✅ Create Resource
            Resource resource = new ResourceBuilder(resourceType)
                    .withId(resourceId)
                    .withProperty("isEnabled", true)
                    .build();

            // ✅ Create Actions
            Action actionView = new ActionBuilder("MagicFarmacia::Platform::Action::view")
                    .withProperty("isEnabled", true)
                    .build();

            Action actionCreate = new ActionBuilder("MagicFarmacia::Platform::Action::create")
                    .withProperty("isEnabled", true)
                    .build();

            // ✅ Create Context
            Map<String, Object> context = Map.of(
                    "time", "2025-01-23T16:17:46+00:00",
                    "isSubscriptionActive", true
            );

            // ✅ Create Evaluations
            Evaluation evaluationView = new EvaluationBuilder(subject, resource, actionView)
                    .withRequestId("1234")
                    .withContext(context)
                    .build();

            Evaluation evaluationCreate = new EvaluationBuilder(subject, resource, actionCreate)
                    .withRequestId("7890")
                    .withContext(context)
                    .build();

            // ✅ Create Entities
            Entities entities = new Entities("cedar", List.of(
                    Map.of(
                            "uid", Map.of("type", "MagicFarmacia::Platform::BranchInfo", "id", "subscription"),
                            "attrs", Map.of("active", true),
                            "parents", List.of()
                    )
            ));

            // ✅ Build the AZRequest with multiple evaluations
            AZRequest request = new AZRequestBuilder(zoneId, policyStoreId)
                    .withRequestId(requestId)
                    .withPrincipal(principal)
                    .withEntitiesItems(entities.getSchema(), entities)
                    .withEvaluation(evaluationView)
                    .withEvaluation(evaluationCreate)
                    .build();

            // Perform authorization check with multiple evaluations
            long requestStartTime = System.currentTimeMillis();
            AZResponse response = client.check(request);
            long requestEndTime = System.currentTimeMillis();
            System.out.println("Request execution time: " + (requestEndTime - requestStartTime) + " ms");
            printAuthorizationResult(response);
        } catch (Exception e) {
            System.err.println("❌ Error executing multiple evaluations request: " + e.getMessage());
            e.printStackTrace();
        }
    }





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
