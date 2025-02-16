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

import java.util.Map;

/**
 * Represents the details of an evaluation request.
 * <p>
 * This class encapsulates the subject, resource, action, and additional context
 * required for an evaluation request. It uses the Builder pattern for flexible and
 * fluent construction.
 * <p>
 * Usage example:
 * <pre>{@code
 * EvaluationRequestDetail evaluationRequestDetail = new EvaluationRequestDetail.Builder()
 *     .subject(subjectDetail)
 *     .resource(resourceDetail)
 *     .action(actionDetail)
 *     .context(Map.of("key", "value"))
 *     .build();
 * }</pre>
 */
public class Evaluation {

    private final Subject subject;
    private final Resource resource;
    private final Action action;
    private final Map<String, Object> context;

    private Evaluation(Builder builder) {
        this.subject = builder.subject;
        this.resource = builder.resource;
        this.action = builder.action;
        this.context = builder.context;
    }

    /**
     * Builder class for {@link Evaluation}.
     */
    public static class Builder {
        private Subject subject;
        private Resource resource;
        private Action action;
        private Map<String, Object> context;

        public Builder(Subject subject, Resource resource, Action action, Map<String, Object> context) {
            this.subject = subject;
            this.resource = resource;
            this.action = action;
            this.context = context;
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
         * Sets the context for the evaluation request.
         *
         * @param context a key-value map representing the context
         * @return the builder instance
         */
        public Builder context(Map<String, Object> context) {
            this.context = context;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Evaluation}.
         *
         * @return a new instance of {@link Evaluation}
         */
        public Evaluation build() {
            return new Evaluation(this);
        }
    }

    // Getters with JavaDoc

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
     * Gets the context for the evaluation request.
     *
     * @return a key-value map representing the context
     */
    public Map<String, Object> getContext() {
        return context;
    }
}