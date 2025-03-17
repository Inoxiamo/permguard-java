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

package com.permguard.example;

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
    public static final long ZONE_ID = 646309364259L;
    public static final String POLICY_STORE_ID = "917e468442634c5486319ca6f09475e8";
    public static final String EMAIL = "amy.smith@acmecorp.com";
    public static final String USER = "user";
    public static final String KEYCLOAK = "keycloak";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {


        AZConfig config = new AZConfig("localhost", 9094, true);
        AZClient client = new AZClient(config);

        System.out.println("\n🔹 Running checkJsonRequest()");
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
        try {
            // Load JSON as InputStream from resources folder and print its content
            InputStream inputStream = Check.class.getClassLoader().getResourceAsStream(JSON_FILE_PATH);
            AZRequest request = objectMapper.readValue(inputStream, AZRequest.class);
            inputStream.close();

            long requestStartTime = System.currentTimeMillis();
            AZResponse response = client.check(request);
            long requestEndTime = System.currentTimeMillis();

            System.out.println("Request execution time: " + (requestEndTime - requestStartTime) + " ms");
            printAuthorizationResult(response);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error loading JSON request: " + e.getMessage());
        }
    }





    public static void checkAtomicRequest(AZClient client) {
        try {
            long zoneId = ZONE_ID;
            String policyStoreId = POLICY_STORE_ID;
            String requestId = "abc1";

            Principal principal = new PrincipalBuilder(EMAIL)
                    .withType(USER)
                    .withSource(KEYCLOAK)
                    .build();

            Entities entities = new Entities("cedar", List.of(
                    Map.of(
                            "uid", Map.of("type", "MagicFarmacia::Platform::BranchInfo", "id", "subscription"),
                            "attrs", Map.of("active", true),
                            "parents", List.of()
                    )
            ));

            // Build the atomic AZRequest using the exact JSON parameters
            AZRequest request = new AZAtomicRequestBuilder(
                    zoneId,
                    policyStoreId,
                    "platform-creator",  // Subject id from JSON
                    "MagicFarmacia::Platform::Subscription",  // Resource type from JSON
                    "MagicFarmacia::Platform::Action::create"  // Action name from JSON
            )
                    .withRequestId(requestId)
                    .withPrincipal(principal)
                    .withEntitiesItems("cedar", entities)
                    .withSubjectSource(KEYCLOAK)
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
            // Extract values from JSON (matching your provided data)
            long zoneId = ZONE_ID;
            String policyStoreId = POLICY_STORE_ID;
            String requestId = "batch-eval-001";
            String subjectId = "platform-creator";
            String subjectType = "role-actor";
            String resourceId = "e3a786fd07e24bfa95ba4341d3695ae8";
            String resourceType = "MagicFarmacia::Platform::Subscription";

            // Create Principal
            Principal principal = new PrincipalBuilder(subjectId)
                    .withType(subjectType)
                    .withSource(KEYCLOAK)
                    .build();

            // Create Subject
            Subject subject = new SubjectBuilder(subjectId)
                    .withType(subjectType)
                    .withSource(KEYCLOAK)
                    .withProperty("isSuperUser", true)
                    .build();

            // Create Resource
            Resource resource = new ResourceBuilder(resourceType)
                    .withId(resourceId)
                    .withProperty("isEnabled", true)
                    .build();

            // Create Actions
            Action actionView = new ActionBuilder("MagicFarmacia::Platform::Action::view")
                    .withProperty("isEnabled", true)
                    .build();

            Action actionCreate = new ActionBuilder("MagicFarmacia::Platform::Action::create")
                    .withProperty("isEnabled", true)
                    .build();

            // Create Context
            Map<String, Object> context = Map.of(
                    "time", "2025-01-23T16:17:46+00:00",
                    "isSubscriptionActive", true
            );

            // Create Evaluations
            Evaluation evaluationView = new EvaluationBuilder(subject, resource, actionView)
                    .withRequestId("1234")
                    .withContext(context)
                    .build();

            Evaluation evaluationCreate = new EvaluationBuilder(subject, resource, actionCreate)
                    .withRequestId("7890")
                    .withContext(context)
                    .build();

            // Create Entities
            Entities entities = new Entities("cedar", List.of(
                    Map.of(
                            "uid", Map.of("type", "MagicFarmacia::Platform::BranchInfo", "id", "subscription"),
                            "attrs", Map.of("active", true),
                            "parents", List.of()
                    )
            ));

            // Build the AZRequest with multiple evaluations
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
                    } else if(eval.isDecision()){
                        System.out.println("-> Evaluation RequestID " + eval.getRequestId() + ": Single Authorization Permitted");
                    }
                }
            }
        }
    }
}
