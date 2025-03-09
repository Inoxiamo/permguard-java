package com.permguard.pep.builder;


import com.permguard.pep.model.request.Action;
import com.permguard.pep.model.request.Evaluation;
import com.permguard.pep.model.request.Resource;
import com.permguard.pep.model.request.Subject;

import java.util.Map;

/**
 * Builder for creating an Evaluation object.
 */
public class EvaluationBuilder {
    private String requestId;
    private Subject subject;
    private Resource resource;
    private Action action;
    private Map<String, Object> context;

    /**
     * Constructor with required subject, resource, and action.
     *
     * @param subject  The subject of the evaluation.
     * @param resource The resource being accessed.
     * @param action   The action being performed.
     */
    public EvaluationBuilder(Subject subject, Resource resource, Action action) {
        this.subject = subject;
        this.resource = resource;
        this.action = action;
    }

    /**
     * Sets the request ID for the evaluation.
     *
     * @param requestId The unique request ID for the evaluation.
     * @return The current builder instance.
     */
    public EvaluationBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Sets the context for the evaluation.
     *
     * @param context Additional context for the evaluation.
     * @return The current builder instance.
     */
    public EvaluationBuilder withContext(Map<String, Object> context) {
        this.context = context;
        return this;
    }

    /**
     * Builds the Evaluation object.
     *
     * @return A new Evaluation instance.
     */
    public Evaluation build() {
        return new Evaluation(requestId, subject, resource, action, context);
    }
}
