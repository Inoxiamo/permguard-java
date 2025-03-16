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
