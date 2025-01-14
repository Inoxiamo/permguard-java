package com.permguard.pep.client;

import com.permguard.pep.config.PermguardConfig;
import io.grpc.ManagedChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PermguardAuthorizationClientTest {

    @Mock
    private ManagedChannel mockChannel;

    @Mock
    private V1PDPServiceGrpc.V1PDPServiceBlockingStub mockStub;

    private PermguardAuthorizationClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        PermguardConfig config = new PermguardConfig("localhost", 8080, true);
        client = spy(new PermguardAuthorizationClient(config));

        // Mock the stub and channel initialization
        doReturn(mockStub).when(client).createStub(any());
        doReturn(mockChannel).when(client).createChannel(any());
    }

    @Test
    void testCheckAuthorization() {
        // Mock response
        AuthorizationCheckResponse expectedResponse = AuthorizationCheckResponse.newBuilder()
                .setAuthorized(true)
                .build();
        when(mockStub.authorizationCheck(any(AuthorizationCheckRequest.class))).thenReturn(expectedResponse);

        // Invoke the method
        AuthorizationCheckResponse response = client.checkAuthorization(
                123L,
                "policyType",
                "policyId",
                "principalType",
                "principalId",
                "subjectType",
                "subjectId",
                "resourceType",
                "resourceId",
                "actionName"
        );

        // Verify the request sent
        ArgumentCaptor<AuthorizationCheckRequest> captor = ArgumentCaptor.forClass(AuthorizationCheckRequest.class);
        verify(mockStub).authorizationCheck(captor.capture());
        AuthorizationCheckRequest requestSent = captor.getValue();

        // Validate the request values
        assertEquals(123L, requestSent.getAuthorizationContext().getApplicationID());
        assertEquals("policyType", requestSent.getAuthorizationContext().getPolicyStore().getType());
        assertEquals("policyId", requestSent.getAuthorizationContext().getPolicyStore().getID());
        assertEquals("principalType", requestSent.getAuthorizationContext().getPrincipal().getType());
        assertEquals("principalId", requestSent.getAuthorizationContext().getPrincipal().getID());
        assertEquals("subjectType", requestSent.getSubject().getType());
        assertEquals("subjectId", requestSent.getSubject().getID());
        assertEquals("resourceType", requestSent.getResource().getType());
        assertEquals("resourceId", requestSent.getResource().getID());
        assertEquals("actionName", requestSent.getAction().getName());

        // Validate the response
        assertNotNull(response);
        assertTrue(response.getAuthorized());
    }

    @Test
    void testShutdown() {
        // Invoke shutdown
        client.shutdown();

        // Verify channel shutdown
        verify(mockChannel, times(1)).shutdown();
    }
}
