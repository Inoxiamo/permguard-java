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

package com.permguard.pep.representation.request;

import java.util.List;
import java.util.Map;

/**
 * Represents the payload for authentication requests.
 * <p>
 * This class is built using the Builder pattern, allowing for flexible and fluent construction
 * of complex objects. It encapsulates details such as the authentication context, subject,
 * resource, action, evaluations, and additional context parameters.
 * <p>
 * Usage example:
 * <pre>{@code
 * AuthRequestPayload payload = new AuthRequestPayload.Builder()
 *     .authContextDetail(authContextDetail)
 *     .subject(subjectDetail)
 *     .resource(resourceDetail)
 *     .action(actionDetail)
 *     .evaluations(evaluationList)
 *     .context(contextMap)
 *     .build();
 * }</pre>
 */
public class AuthRequestPayload {

    private final AuthContextDetail authContextDetail;
    private final SubjectDetail subject;
    private final ResourceDetail resource;
    private final ActionDetail action;
    private final List<EvaluationRequestDetail> evaluations;
    private final Map<String, Object> context;

    private AuthRequestPayload(Builder builder) {
        this.authContextDetail = builder.authContextDetail;
        this.subject = builder.subject;
        this.resource = builder.resource;
        this.action = builder.action;
        this.evaluations = builder.evaluations;
        this.context = builder.context;
    }

    /**
     * Builder class for {@link AuthRequestPayload}.
     */
    public static class Builder {
        private AuthContextDetail authContextDetail;
        private SubjectDetail subject;
        private ResourceDetail resource;
        private ActionDetail action;
        private List<EvaluationRequestDetail> evaluations;
        private Map<String, Object> context;

        /**
         * Sets the authentication context detail.
         *
         * @param authContextDetail the authentication context detail
         * @return the builder instance
         */
        public Builder authContextDetail(AuthContextDetail authContextDetail) {
            this.authContextDetail = authContextDetail;
            return this;
        }

        /**
         * Sets the subject detail.
         *
         * @param subject the subject detail
         * @return the builder instance
         */
        public Builder subject(SubjectDetail subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Sets the resource detail.
         *
         * @param resource the resource detail
         * @return the builder instance
         */
        public Builder resource(ResourceDetail resource) {
            this.resource = resource;
            return this;
        }

        /**
         * Sets the action detail.
         *
         * @param action the action detail
         * @return the builder instance
         */
        public Builder action(ActionDetail action) {
            this.action = action;
            return this;
        }

        /**
         * Sets the list of evaluation request details.
         *
         * @param evaluations the list of evaluation request details
         * @return the builder instance
         */
        public Builder evaluations(List<EvaluationRequestDetail> evaluations) {
            this.evaluations = evaluations;
            return this;
        }

        /**
         * Sets the additional context parameters.
         *
         * @param context the additional context parameters
         * @return the builder instance
         */
        public Builder context(Map<String, Object> context) {
            this.context = context;
            return this;
        }

        /**
         * Builds and returns an instance of {@link AuthRequestPayload}.
         *
         * @return a new instance of {@link AuthRequestPayload}
         */
        public AuthRequestPayload build() {
            return new AuthRequestPayload(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the authentication context detail.
     *
     * @return the authentication context detail
     */
    public AuthContextDetail getAuthContextDetail() {
        return authContextDetail;
    }

    /**
     * Gets the subject detail.
     *
     * @return the subject detail
     */
    public SubjectDetail getSubject() {
        return subject;
    }

    /**
     * Gets the resource detail.
     *
     * @return the resource detail
     */
    public ResourceDetail getResource() {
        return resource;
    }

    /**
     * Gets the action detail.
     *
     * @return the action detail
     */
    public ActionDetail getAction() {
        return action;
    }

    /**
     * Gets the list of evaluation request details.
     *
     * @return the list of evaluation request details
     */
    public List<EvaluationRequestDetail> getEvaluations() {
        return evaluations;
    }

    /**
     * Gets the additional context parameters.
     *
     * @return the additional context parameters
     */
    public Map<String, Object> getContext() {
        return context;
    }
}


