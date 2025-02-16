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
 *     .authModelDetail(authModelDetail)
 *     .subject(subjectDetail)
 *     .resource(resourceDetail)
 *     .action(actionDetail)
 *     .evaluations(evaluationList)
 *     .context(contextMap)
 *     .build();
 * }</pre>
 */
public class Request {

    private final AuthModel authModel;
    private final Subject subject;
    private final Resource resource;
    private final Action action;
    private final List<Evaluation> evaluations;
    private final Map<String, Object> context;

    private Request(Builder builder) {
        this.authModel = builder.authModel;
        this.subject = builder.subject;
        this.resource = builder.resource;
        this.action = builder.action;
        this.evaluations = builder.evaluations;
        this.context = builder.context;
    }

    /**
     * Builder class for {@link Request}.
     */
    public static class Builder {
        private AuthModel authModel;
        private Subject subject;
        private Resource resource;
        private Action action;
        private List<Evaluation> evaluations;
        private Map<String, Object> context;


        public Builder(AuthModel authModel, Subject subject, Resource resource, Action action, List<Evaluation> evaluations, Map<String, Object> context) {
            this.authModel = authModel;
            this.subject = subject;
            this.resource = resource;
            this.action = action;
            this.evaluations = evaluations;
            this.context = context;
        }

        /**
         * Sets the authentication context detail.
         *
         * @param authModel the authentication context detail
         * @return the builder instance
         */
        public Builder authModelDetail(AuthModel authModel) {
            this.authModel = authModel;
            return this;
        }

        /**
         * Sets the subject detail.
         *
         * @param subject the subject detail
         * @return the builder instance
         */
        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Sets the resource detail.
         *
         * @param resource the resource detail
         * @return the builder instance
         */
        public Builder resource(Resource resource) {
            this.resource = resource;
            return this;
        }

        /**
         * Sets the action detail.
         *
         * @param action the action detail
         * @return the builder instance
         */
        public Builder action(Action action) {
            this.action = action;
            return this;
        }

        /**
         * Sets the list of evaluation request details.
         *
         * @param evaluations the list of evaluation request details
         * @return the builder instance
         */
        public Builder evaluations(List<Evaluation> evaluations) {
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
         * Builds and returns an instance of {@link Request}.
         *
         * @return a new instance of {@link Request}
         */
        public Request build() {
            return new Request(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the authentication context detail.
     *
     * @return the authentication context detail
     */
    public AuthModel getauthModelDetail() {
        return authModel;
    }

    /**
     * Gets the subject detail.
     *
     * @return the subject detail
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the resource detail.
     *
     * @return the resource detail
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Gets the action detail.
     *
     * @return the action detail
     */
    public Action getAction() {
        return action;
    }

    /**
     * Gets the list of evaluation request details.
     *
     * @return the list of evaluation request details
     */
    public List<Evaluation> getEvaluations() {
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


