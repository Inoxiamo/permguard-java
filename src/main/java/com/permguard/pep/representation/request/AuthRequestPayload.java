package com.permguard.pep.representation.request;

import java.util.List;
import java.util.Map;

public class AuthRequestPayload {

    private long applicationId;
    private PolicyStoreDetail policyStore;
    private PrincipalDetail principal;
    private SubjectDetail subject;
    private ResourceDetail resource;
    private ActionDetail action;
    private List<EvaluationRequestDetail> evaluations;
    private Map<String, Object> context;

    // Getters and Setters

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public PolicyStoreDetail getPolicyStore() {
        return policyStore;
    }

    public void setPolicyStore(PolicyStoreDetail policyStore) {
        this.policyStore = policyStore;
    }

    public PrincipalDetail getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalDetail principal) {
        this.principal = principal;
    }

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
}


