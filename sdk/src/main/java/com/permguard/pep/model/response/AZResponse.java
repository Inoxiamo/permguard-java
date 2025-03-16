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

package com.permguard.pep.model.response;

import java.util.List;

/**
 * Represents the response of an authorization check.
 * It includes the decision, request ID, context, and evaluation results.
 */
public class AZResponse {
    private boolean decision;
    private String requestId;
    private ContextResponse context;
    private List<EvaluationResponse> evaluations;

    /**
     * Default conMap<String, Object>or.
     */
    public AZResponse() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param decision    The authorization decision (true = allowed, false = denied).
     * @param requestId   The unique ID of the request.
     * @param context     The context included in the response.
     * @param evaluations The list of evaluation responses.
     */
    public AZResponse(boolean decision, String requestId, ContextResponse context, List<EvaluationResponse> evaluations) {
        this.decision = decision;
        this.requestId = requestId;
        this.context = context;
        this.evaluations = evaluations;
    }

    /**
     * Gets the authorization decision.
     *
     * @return True if the request is authorized, false otherwise.
     */
    public boolean isDecision() {
        return decision;
    }

    /**
     * Sets the authorization decision.
     *
     * @param decision The authorization decision.
     */
    public void setDecision(boolean decision) {
        this.decision = decision;
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
     * Gets the context response.
     *
     * @return The context response.
     */
    public ContextResponse getContext() {
        return context;
    }

    /**
     * Sets the context response.
     *
     * @param context The context response.
     */
    public void setContext(ContextResponse context) {
        this.context = context;
    }

    /**
     * Gets the list of evaluation responses.
     *
     * @return The list of evaluations.
     */
    public List<EvaluationResponse> getEvaluations() {
        return evaluations;
    }

    /**
     * Sets the list of evaluation responses.
     *
     * @param evaluations The list of evaluations.
     */
    public void setEvaluations(List<EvaluationResponse> evaluations) {
        this.evaluations = evaluations;
    }
}
