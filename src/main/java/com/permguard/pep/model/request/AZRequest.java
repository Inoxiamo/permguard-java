package com.permguard.pep.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.util.List;

/**
 * Represents an authorization check request.
 */
public class AZRequest {
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("authorization_model")
    private AZModel authorizationModel;
    private Subject subject;
    private Resource resource;
    private Action action;

    @JsonProperty("context")
    private Struct context;
    private List<Evaluation> evaluations;

    /**
     * Default constructor.
     */
    public AZRequest() {
    }

    /**
     * Constructor with parameters.
     *
     * @param requestId          The unique request ID.
     * @param authorizationModel The authorization model used for evaluation.
     * @param subject            The subject of the authorization request.
     * @param resource           The resource being accessed.
     * @param action             The action being performed.
     * @param context            Additional context for the request.
     * @param evaluations        A list of evaluations for multiple checks.
     */
    @JsonCreator
    public AZRequest(
            @JsonProperty("request_id") String requestId,
            @JsonProperty("authorization_model") AZModel authorizationModel,
            @JsonProperty("subject") Subject subject,
            @JsonProperty("resource") Resource resource,
            @JsonProperty("action") Action action,
            @JsonProperty("context") Struct context,
            @JsonProperty("evaluations") List<Evaluation> evaluations) {
        this.requestId = requestId;
        this.authorizationModel = authorizationModel;
        this.subject = subject;
        this.resource = resource;
        this.action = action;
        this.context = context;
        this.evaluations = evaluations;
    }


    /**
     * Gets the request ID.
     *
     * @return The request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the request ID.
     *
     * @param requestId The request ID.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the authorization model.
     *
     * @return The authorization model.
     */
    public AZModel getAuthorizationModel() {
        return authorizationModel;
    }

    /**
     * Sets the authorization model.
     *
     * @param authorizationModel The authorization model.
     */
    public void setAuthorizationModel(AZModel authorizationModel) {
        this.authorizationModel = authorizationModel;
    }

    /**
     * Gets the subject.
     *
     * @return The subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject The subject.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the resource.
     *
     * @return The resource.
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets the resource.
     *
     * @param resource The resource.
     */
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * Gets the action.
     *
     * @return The action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action The action.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Gets the request context.
     *
     * @return The context.
     */
    public Struct getContext() {
        return context;
    }

    /**
     * Sets the request context.
     *
     * @param context The context.
     */
    public void setContext(Struct context) {
        this.context = context;
    }

    /**
     * Gets the list of evaluations.
     *
     * @return The evaluations.
     */
    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    /**
     * Sets the list of evaluations.
     *
     * @param evaluations The evaluations.
     */
    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }


     /**
     * Parses a JSON string into a Protobuf Struct.
     *
     * @param json JSON representation of the context.
     * @return Parsed Struct or empty Struct if parsing fails.
     */
    private Struct parseContext(String json) {
        Struct.Builder structBuilder = Struct.newBuilder();
        try {
            JsonFormat.parser().merge(json, structBuilder);
        } catch (IOException e) {
            System.err.println("❌ Failed to parse context JSON into Struct: " + e.getMessage());
        }
        return structBuilder.build();
    }
}
