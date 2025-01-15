package com.permguard.pep.representation.request;

import java.util.Map;

public class EvaluationRequestDetail {
    private SubjectDetail subject;
    private ResourceDetail resource;
    private ActionDetail action;
    private Map<String, Object> context;

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

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}