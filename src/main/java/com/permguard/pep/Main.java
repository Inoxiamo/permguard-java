package com.permguard.pep;

import com.permguard.pep.client.PermguardAuthorizationClient;
import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.proto.AuthorizationCheck.*;

public class Main {

    public static void main(String[] args) {
        // Configurazione del client
        PermguardConfig config = new PermguardConfig("localhost", 9094, true);

        // Inizializzazione del client
        PermguardAuthorizationClient client = new PermguardAuthorizationClient(config);

        try {
            // Esempio di dati per l'autorizzazione
            long applicationId = Long.parseLong("330265061957");
            String policyStoreType = "ledger";
            String policyStoreId = "ef1957db283f45e5b53e54f1da8e9dda";
            String principalType = "user";
            String principalId = "amy.smith@acmecorp.com";
            String subjectType = "user";
            String subjectId = "amy.smith@acmecorp.com";
            String resourceType = "Magicfarmacia::Platform::BranchInfo";
            String resourceId = "subscription";
            String actionName = "MagicFarmacia::Platform::Action::view";

            // Invoca il metodo di autorizzazione
            AuthorizationCheckResponse response = client.checkAuthorization(
                applicationId,
                policyStoreType,
                policyStoreId,
                principalType,
                principalId,
                subjectType,
                subjectId,
                resourceType,
                resourceId,
                actionName
            );

            // Stampa il risultato
            System.out.println("Risultato dell'autorizzazione: " + response);

        } catch (Exception e) {
            System.err.println("Errore durante il controllo di autorizzazione: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Chiudi il client
            client.shutdown();
        }
    }
}
