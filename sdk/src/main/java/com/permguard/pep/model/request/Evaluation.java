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

package com.permguard.pep.model.request;

import java.util.Map;

/**
 * Represents an evaluation request for an authorization decision.
 */
public class Evaluation {
    private String requestId;
    private Subject subject;
    private Resource resource;
    private Action action;
    private Map<String, Object> context;

    /**
     * Default conMap<String, Object>or.
     */
    public Evaluation() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param requestId The unique request ID for the evaluation.
     * @param subject   The subject associated with the evaluation.
     * @param resource  The resource associated with the evaluation.
     * @param action    The action associated with the evaluation.
     * @param context   The additional context for the evaluation.
     */
    public Evaluation(String requestId, Subject subject, Resource resource, Action action, Map<String, Object> context) {
        this.requestId = requestId;
        this.subject = subject;
        this.resource = resource;
        this.action = action;
        this.context = context;
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
     * Gets the evaluation context.
     *
     * @return The evaluation context.
     */
    public Map<String, Object> getContext() {
        return context;
    }

    /**
     * Sets the evaluation context.
     *
     * @param context The evaluation context.
     */
    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}
