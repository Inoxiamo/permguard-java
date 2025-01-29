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

package com.permguard.pep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.client.PermguardAuthorizationClient;
import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Configuration Client
        PermguardConfig config = new PermguardConfig("localhost", 9094,true);
        PermguardAuthorizationClient client = new PermguardAuthorizationClient(config);

        try {
            // Example date for authorization check

            AuthRequestPayload authRequestPayload = getAuthRequestPayload();
            // Invoke the authorization check
            AuthResponsePayload response = client.checkAuthorization(authRequestPayload);
            // Print the result
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Result: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

        } catch (Exception e) {
            System.err.println("Errore durante il controllo di autorizzazione: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the client
            client.shutdown();
        }
    }


    private static AuthRequestPayload getAuthRequestPayload() {
    EntityDetail entityDetail = new EntityDetail.Builder()
        .schema("cedar")
        .items(List.of(
            new ItemDetails.Builder()
                .uid(new Uid.Builder()
                    .type("Permguard::IAM::User")
                    .id("amy.smith@acmecorp.com").build())
                .attrs(Map.of("isSuperUser", true))
                .parents(List.of()).build(),
            new ItemDetails.Builder()
                .uid(new Uid.Builder()
                    .type("MagicFarmacia::Platform::Subscription")
                    .id("e3a786fd07e24bfa95ba4341d3695ae8").build())
                .attrs(Map.of("active", true))
                .parents(List.of()).build()
        )).build();

    AuthContextDetail authContextDetail = new AuthContextDetail.Builder()
        .zoneId(220019489970L)
        .entityDetail(entityDetail)
        .policyStore(new PolicyStoreDetail.Builder()
            .type("ledger")
            .id("b147a0e326b040aab2693fc0253074b6").build())
        .principal(new PrincipalDetail.Builder()
            .type("user")
            .id("amy.smith@acmecorp.com")
            .source("keycloak")
            .identityToken("eyJhbGciOiJI...")
            .accessToken("eyJhbGciOiJI...").build())
        .build();

    return new AuthRequestPayload.Builder()
        .authContextDetail(authContextDetail)
        .subject(new SubjectDetail.Builder()
            .type("user")
            .id("amy.smith@acmecorp.com")
            .source("keycloak").build())
        .resource(new ResourceDetail.Builder()
            .type("MagicFarmacia::Platform::Subscription")
            .id("e3a786fd07e24bfa95ba4341d3695ae8").build())
        .action(new ActionDetail.Builder()
            .name("MagicFarmacia::Platform::Action::create").build())
        .context(Map.of("isSuperUser", true)).build();
}


}
