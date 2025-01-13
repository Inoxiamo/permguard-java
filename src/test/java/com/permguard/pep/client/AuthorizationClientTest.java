package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import com.permguard.pep.proto.V1PDPServiceGrpc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthorizationClientTest {

    private PermguardAuthorizationClient client;
    private V1PDPServiceGrpc.V1PDPServiceBlockingStub mockStub;

    @BeforeEach
    public void setUp() {
        // Mock dello stub gRPC
        mockStub = mock(V1PDPServiceGrpc.V1PDPServiceBlockingStub.class);

        // Configurazione del client con mock
        PermguardConfig config = new PermguardConfig("localhost", 50051, true) {
            @Override
            public String getHost() {
                return "localhost";
            }

            @Override
            public int getPort() {
                return 50051;
            }
        };
        
        client = new PermguardAuthorizationClient(config) {
            @Override
            protected void initChannelAndStub() {
                this.blockingStub = mockStub; // Usa il mock invece del vero stub
            }
        };
    }

    @Test
    public void testAuthorizationCheck() {
        // Configura la risposta mock dello stub
        AuthorizationCheckResponse mockResponse = AuthorizationCheckResponse.newBuilder()
            .setAllow(true)
            .build();
        when(mockStub.authorizationCheck(any())).thenReturn(mockResponse);

        // Esegui il test sul metodo
        AuthorizationCheckResponse response = client.checkAuthorization(
            12345L,
            "policyType",
            "policyId",
            "userType",
            "userId",
            "subjectType",
            "subjectId",
            "resourceType",
            "resourceId",
            "actionName"
        );

        // Verifica il comportamento
        assertNotNull(response, "La risposta non deve essere null");
        assertTrue(response.getAllow(), "L'autorizzazione dovrebbe essere consentita");

        // Verifica che lo stub sia stato invocato correttamente
        verify(mockStub, times(1)).authorizationCheck(any());
    }
}
