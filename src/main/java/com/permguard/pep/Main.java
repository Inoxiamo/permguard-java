package com.permguard.pep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.client.PermguardAuthorizationClient;
import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.representation.request.*;
import com.permguard.pep.representation.response.AuthResponsePayload;

import java.util.ArrayList;
import java.util.HashMap;
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
        AuthRequestPayload authRequestPayload = new AuthRequestPayload();
        authRequestPayload.setApplicationId(Long.parseLong("662436641083"));
        EntityDetail entityDetail = new EntityDetail();
        entityDetail.setSchema("cedar");
        List<ItemDetails> items = new ArrayList<>();
        Uid uid1 = new Uid("Permguard::IAM::User", "amy.smith@acmecorp.com");
        Map<String, Object> attr1= new HashMap<>();
        attr1.put("isSuperUser", true);
        Uid uid2 = new Uid("MagicFarmacia::Platform::BranchInfo", "subscription");
        Map<String, Object> attr2= new HashMap<>();
        attr2.put("active", true);
        ItemDetails item1 = new ItemDetails(uid1, attr1, List.of());
        ItemDetails item2 = new ItemDetails(uid2, attr2, List.of());
        entityDetail.setItems(List.of(item1, item2));
        PolicyStoreDetail policyStore = new PolicyStoreDetail("ledger", "8f413ab4ad754a2a92915c5d064ea0bc");
        PrincipalDetail principalDetail = new PrincipalDetail("user", "amy.smith@acmecorp.com", "keycloak", "eyJhbGciOiJI...", "eyJhbGciOiJI...");
        SubjectDetail subjectDetail = new SubjectDetail("user", "amy.smith@acmecorp.com", "keycloak", null);
        ResourceDetail resourceDetail = new ResourceDetail("MagicFarmacia::Platform::BranchInfo", "subscription", null);
        ActionDetail actionDetail = new ActionDetail("MagicFarmacia::Platform::Action::create", null);
        Map<String, Object> contextDetail = new HashMap<>();
        contextDetail.put("isSuperUser", true);
        authRequestPayload.setEntityDetails(entityDetail);
        authRequestPayload.setPolicyStore(policyStore);
        authRequestPayload.setPrincipal(principalDetail);
        authRequestPayload.setSubject(subjectDetail);
        authRequestPayload.setResource(resourceDetail);
        authRequestPayload.setAction(actionDetail);
        authRequestPayload.setContext(contextDetail);
        return authRequestPayload;
    }
}
