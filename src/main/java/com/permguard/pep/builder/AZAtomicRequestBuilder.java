package com.permguard.pep.builder;

import com.google.protobuf.Struct;
import com.permguard.pep.model.request.*;


/**
 * Builder for creating an atomic AZRequest object.
 */
public class AZAtomicRequestBuilder {
    private AZRequestBuilder requestBuilder;
    private String requestId;

    /**
     * Constructor for AZAtomicRequestBuilder.
     *
     * @param zoneId      The authorization zone ID.
     * @param policyStoreId The ID of the policy store.
     */
    public AZAtomicRequestBuilder(long zoneId, String policyStoreId) {
        this.requestBuilder = new AZRequestBuilder(zoneId, policyStoreId);
    }

    /**
     * Sets the request ID.
     *
     * @param requestId The unique request ID.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        this.requestBuilder.withRequestId(requestId);
        return this;
    }

    /**
     * Sets the principal.
     *
     * @param principal The principal making the request.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withPrincipal(Principal principal) {
        this.requestBuilder.withPrincipal(principal);
        return this;
    }

    /**
     * Sets the subject.
     *
     * @param subject The subject of the request.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withSubject(Subject subject) {
        this.requestBuilder.withSubject(subject);
        return this;
    }

    /**
     * Sets the resource.
     *
     * @param resource The resource being accessed.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withResource(Resource resource) {
        this.requestBuilder.withResource(resource);
        return this;
    }

    /**
     * Sets the action.
     *
     * @param action The action being performed.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withAction(Action action) {
        this.requestBuilder.withAction(action);
        return this;
    }

    /**
     * Sets the context.
     *
     * @param context The context for the request.
     * @return The current builder instance.
     */
    public AZAtomicRequestBuilder withContext(Struct context) {
        this.requestBuilder.withContext(context);
        return this;
    }

    /**
     * Builds the AZRequest object.
     *
     * @return A new AZRequest instance.
     */
    public AZRequest build() {
        return requestBuilder.build();
    }
}
