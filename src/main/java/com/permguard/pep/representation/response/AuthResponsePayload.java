/**
 *   Copyright 2024 Nitro Agility S.r.l.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  SPDX-License-Identifier: Apache-2.0
  */

package com.permguard.pep.representation.response;

import java.util.List;

public class AuthResponsePayload {

    private String requestId;
    private boolean decision;
    private ContextDetail context;
    private List<EvaluationResponseDetail> evaluations;

    public AuthResponsePayload(String requestId, boolean decision, ContextDetail context, List<EvaluationResponseDetail> evaluations) {
        this.requestId = requestId;
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}






