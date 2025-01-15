package com.permguard.pep.representation.response;

import java.util.List;

public class AuthResponsePayload {

    private boolean decision;
    private ContextDetail context;
    private List<EvaluationResponseDetail> evaluations;

    public AuthResponsePayload(boolean decision, ContextDetail context, List<EvaluationResponseDetail> evaluations) {
        this.decision = decision;
        this.context = context;
        this.evaluations = evaluations;
    }

    public AuthResponsePayload() {
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

    public List<EvaluationResponseDetail> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationResponseDetail> evaluations) {
        this.evaluations = evaluations;
    }

}






