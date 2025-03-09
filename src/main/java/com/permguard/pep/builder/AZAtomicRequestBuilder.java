package com.permguard.pep.builder;

import com.permguard.pep.model.request.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating an atomic AZRequest object.
 */
public class AZAtomicRequestBuilder {
    private final AZRequestBuilder requestBuilder;
    private final String subjectId;
    private final String subjectType; // ✅ Preserve subject type
    private final String resourceType; // ✅ Preserve resource type
    private final String actionName; // ✅ Preserve action name
    private String requestId;
    private String subjectSource;
    private String resourceId;
    private final Map<String, Object> subjectProperties = new HashMap<>();
    private final Map<String, Object> resourceProperties = new HashMap<>();
    private final Map<String, Object> actionProperties = new HashMap<>();
    private final Map<String, Object> context = new HashMap<>();

    /**
     * Constructor for AZAtomicRequestBuilder.
     *
     * @param zoneId        The authorization zone ID.
     * @param policyStoreId The ID of the policy store.
     * @param id            The ID of the subject.
     * @param resourceType  The type of the resource.
     * @param actionName    The name of the action.
     */
    public AZAtomicRequestBuilder(long zoneId, String policyStoreId, String id, String resourceType, String actionName) {
        this.requestBuilder = new AZRequestBuilder(zoneId, policyStoreId);
        this.subjectId = id; // ✅ Store the original subject ID
        this.subjectType = "role-actor"; // ✅ Default to role-actor (can be modified)
        this.resourceType = resourceType; // ✅ Store the resource type
        this.actionName = actionName; // ✅ Store the action name

        this.requestBuilder.withSubject(new SubjectBuilder(id).withType(this.subjectType).build());
        this.requestBuilder.withResource(new ResourceBuilder(resourceType).build());
        this.requestBuilder.withAction(new ActionBuilder(actionName).build());
    }

    /**
     * Sets the request ID.
     */
    public AZAtomicRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        this.requestBuilder.withRequestId(requestId);
        return this;
    }

    /**
     * Sets the principal.
     */
    public AZAtomicRequestBuilder withPrincipal(Principal principal) {
        this.requestBuilder.withPrincipal(principal);
        return this;
    }

    /**
     * Sets the subject type (without overwriting previous values).
     */
    public AZAtomicRequestBuilder withSubjectType(String type) {
        if (type != null && !type.isEmpty()) {
            this.requestBuilder.withSubject(new SubjectBuilder(subjectId).withType(type).build());
        }
        return this;
    }

    /**
     * Sets the subject source.
     */
    public AZAtomicRequestBuilder withSubjectSource(String source) {
        this.subjectSource = source;
        return this;
    }

    /**
     * Adds a property to the subject.
     */
    public AZAtomicRequestBuilder withSubjectProperty(String key, Object value) {
        this.subjectProperties.put(key, value);
        return this;
    }

    /**
     * Sets the resource ID.
     */
    public AZAtomicRequestBuilder withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * Adds a property to the resource.
     */
    public AZAtomicRequestBuilder withResourceProperty(String key, Object value) {
        this.resourceProperties.put(key, value);
        return this;
    }

    /**
     * Adds a property to the action.
     */
    public AZAtomicRequestBuilder withActionProperty(String key, Object value) {
        this.actionProperties.put(key, value);
        return this;
    }

    /**
     * Adds a property to the request context.
     */
    public AZAtomicRequestBuilder withContextProperty(String key, Object value) {
        this.context.put(key, value);
        return this;
    }

    /**
     * Sets the entities for the request.
     */
    public AZAtomicRequestBuilder withEntitiesItems(String schema, Entities entities) {
        this.requestBuilder.withEntitiesItems(schema, entities);
        return this;
    }

    /**
     * Builds the AZRequest object, ensuring all properties are correctly applied.
     */
    public AZRequest build() {
        // ✅ Ensure the subject ID is not lost and type is preserved
        Subject subject = new SubjectBuilder(this.subjectId)
                .withType(this.subjectType) // ✅ Preserve subject type
                .withSource(subjectSource)
                .build();
        subject.getProperties().putAll(subjectProperties); // ✅ Append properties

        // ✅ Preserve resource ID and type
        Resource resource = new ResourceBuilder(this.resourceType).withId(resourceId).build();
        resource.getProperties().putAll(resourceProperties); // ✅ Append properties

        // ✅ Preserve action name and append properties
        Action action = new ActionBuilder(this.actionName).build();
        action.getProperties().putAll(actionProperties); // ✅ Append properties

        // ✅ Apply context properties
        this.requestBuilder.withContext(context);

        return requestBuilder.withSubject(subject)
                .withResource(resource)
                .withAction(action)
                .build();
    }
}
