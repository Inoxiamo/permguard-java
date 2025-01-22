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
        EntityDetail entityDetail = new EntityDetail(
                "cedar" ,
                List.of(
            new ItemDetails(new Uid("Permguard::IAM::User", "amy.smith@acmecorp.com"),
                            Map.of("isSuperUser", true), List.of()),
            new ItemDetails(new Uid("MagicFarmacia::Platform::Subscription", "e3a786fd07e24bfa95ba4341d3695ae8"),
                            Map.of("active", true), List.of())
        ));
        AuthContextDetail authContextDetail = new AuthContextDetail(
            145748228796L,
            entityDetail,
            new PolicyStoreDetail("ledger", "5740a9c648a04f7db08ac2f44a3779da"),
            new PrincipalDetail("user", "amy.smith@acmecorp.com", "keycloak", "eyJhbGciOiJI...", "eyJhbGciOiJI...")
        );
        return new AuthRequestPayload(
            authContextDetail,
            new SubjectDetail("user", "amy.smith@acmecorp.com", "keycloak", null),
            new ResourceDetail("MagicFarmacia::Platform::Subscription", "e3a786fd07e24bfa95ba4341d3695ae8", null),
            new ActionDetail("MagicFarmacia::Platform::Action::create", null),
            null,
            Map.of("isSuperUser", true)
        );
    }

}
