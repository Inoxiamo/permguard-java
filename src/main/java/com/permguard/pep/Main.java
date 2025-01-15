package com.permguard.pep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.client.PermguardAuthorizationClient;
import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;

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
        AuthRequestPayload authRequestPayload = new AuthRequestPayload();
        authRequestPayload.setApplicationId(Long.parseLong("330265061957"));
        PolicyStoreDetail policyStore = new PolicyStoreDetail("ledger", "ef1957db283f45e5b53e54f1da8e9dda");
        PrincipalDetail principalDetail = new PrincipalDetail("user", "amy.smith@acmecorp.com", null, null, null);
        SubjectDetail subjectDetail = new SubjectDetail("user", "amy.smith@acmecorp.com", null, null);
        ResourceDetail resourceDetail = new ResourceDetail("Magicfarmacia::Platform::BranchInfo", "subscription", null);
        ActionDetail actionDetail = new ActionDetail("MagicFarmacia::Platform::Action::view", null);
        Map<String, Object> contextDetail = null;
        authRequestPayload.setPolicyStore(policyStore);
        authRequestPayload.setPrincipal(principalDetail);
        authRequestPayload.setSubject(subjectDetail);
        authRequestPayload.setResource(resourceDetail);
        authRequestPayload.setAction(actionDetail);
        authRequestPayload.setContext(contextDetail);
        return authRequestPayload;
    }
}
