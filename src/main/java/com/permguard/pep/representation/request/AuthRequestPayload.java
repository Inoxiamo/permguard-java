package com.permguard.pep.representation.request;

import java.util.List;
import java.util.Map;

public class AuthRequestPayload {

    private AuthContextDetail authContextDetail;
    private SubjectDetail subject;
    private ResourceDetail resource;
    private ActionDetail action;
    private List<EvaluationRequestDetail> evaluations;
    private Map<String, Object> context;

    public AuthRequestPayload() {
    }

    public AuthRequestPayload(AuthContextDetail authContextDetail, SubjectDetail subject, ResourceDetail resource, ActionDetail action, List<EvaluationRequestDetail> evaluations, Map<String, Object> context) {
        this.authContextDetail = authContextDetail;
        this.subject = subject;
        this.resource = resource;
        this.action = action;
        this.evaluations = evaluations;
        this.context = context;
    }

    // Getters and Setters

    public SubjectDetail getSubject() {
        return subject;
    }

    public void setSubject(SubjectDetail subject) {
        this.subject = subject;
    }

    public ResourceDetail getResource() {
        return resource;
    }

    public void setResource(ResourceDetail resource) {
        this.resource = resource;
    }

    public ActionDetail getAction() {
        return action;
    }

    public void setAction(ActionDetail action) {
        this.action = action;
    }

    public List<EvaluationRequestDetail> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationRequestDetail> evaluations) {
        this.evaluations = evaluations;
    }


    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public AuthContextDetail getAuthContextDetail() {
        return authContextDetail;
    }

    public void setAuthContextDetail(AuthContextDetail authContextDetail) {
        this.authContextDetail = authContextDetail;
    }
}


