package com.permguard.pep.representation.response;

public class EvaluationResponseDetail {
    private boolean decision;
    private ContextDetail context;

    public EvaluationResponseDetail(boolean decision, ContextDetail context) {
        this.decision = decision;
        this.context = context;
    }

    public EvaluationResponseDetail() {
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public ContextDetail getContext() {
        return context;
    }

    public void setContext(ContextDetail context) {
        this.context = context;
    }
}