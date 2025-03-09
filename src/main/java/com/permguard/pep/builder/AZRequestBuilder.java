package com.permguard.pep.builder;

import com.permguard.pep.model.request.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builder for creating an AZRequest object.
 */
public class AZRequestBuilder {
    private String requestId;
    private AZModel authorizationModel;
    private Subject subject;
    private Resource resource;
    private Action action;
    private Map<String, Object> context;
    private List<Evaluation> evaluations = new ArrayList<>();

    /**
     * Constructor with required authorization model.
     *
     * @param zoneId      The authorization zone ID.
     * @param policyStoreId The ID of the policy store.
     */
    public AZRequestBuilder(long zoneId, String policyStoreId) {
        this.authorizationModel = new AZModel(zoneId, new PolicyStore("ledger", policyStoreId), null, null);
    }

    /**
     * Sets the request ID.
     *
     * @param requestId The unique request ID.
     * @return The current builder instance.
     */
    public AZRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Sets the principal.
     *
     * @param principal The principal making the request.
     * @return The current builder instance.
     */
    public AZRequestBuilder withPrincipal(Principal principal) {
        this.authorizationModel.setPrincipal(principal);
        return this;
    }

    /**
     * Sets the subject.
     *
     * @param subject The subject of the request.
     * @return The current builder instance.
     */
    public AZRequestBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Sets the resource.
     *
     * @param resource The resource being accessed.
     * @return The current builder instance.
     */
    public AZRequestBuilder withResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    /**
     * Sets the action.
     *
     * @param action The action being performed.
     * @return The current builder instance.
     */
    public AZRequestBuilder withAction(Action action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the request context.
     *
     * @param context Additional context for the request.
     * @return The current builder instance.
     */
    public AZRequestBuilder withContext(Map<String, Object> context) {
        this.context = context;
        return this;
    }

    /**
     * Adds an evaluation to the request.
     *
     * @param evaluation An evaluation to be included in the request.
     * @return The current builder instance.
     */
    public AZRequestBuilder withEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        return this;
    }

    /**
     * Sets the entities in the AZRequest.
     *
     * @param schema  The schema name for the entities.
     * @param entities The list of entities to be included.
     * @return The current builder instance.
     */
    public AZRequestBuilder withEntitiesItems(String schema, Entities entities) {
        this.authorizationModel.setEntities(new Entities(schema, entities.getItems()));
        return this;
    }


    /**
     * Builds the AZRequest object.
     *
     * @return A new AZRequest instance.
     */
    public AZRequest build() {
        return new AZRequest(requestId, authorizationModel, subject, resource, action, context, evaluations);
    }
}
