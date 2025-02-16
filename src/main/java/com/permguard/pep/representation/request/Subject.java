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
 * Represents the details of a subject, including type, identifier, source, and additional properties.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * SubjectDetail subjectDetail = new SubjectDetail.Builder()
 *     .type("user")
 *     .id("amy.smith@acmecorp.com")
 *     .source("keycloak")
 *     .properties(Map.of("department", "IT", "role", "admin"))
 *     .build();
 * }</pre>
 */
public class Subject {

    private final String type;
    private final String id;
    private final String source;
    private final Map<String, Object> properties;

    private Subject(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.source = builder.source;
        this.properties = builder.properties;
    }

    /**
     * Builder class for {@link Subject}.
     */
    public static class Builder {
        private String type;
        private String id;
        private String source;
        private Map<String, Object> properties;

        public Builder(String type, String id, String source, Map<String, Object> properties) {
            this.type = type;
            this.id = id;
            this.source = source;
            this.properties = properties;
        }

        /**
         * Sets the type of the subject.
         *
         * @param type the type of the subject
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the identifier of the subject.
         *
         * @param id the identifier of the subject
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the source of the subject.
         *
         * @param source the source of the subject
         * @return the builder instance
         */
        public Builder source(String source) {
            this.source = source;
            return this;
        }

        /**
         * Sets the additional properties of the subject.
         *
         * @param properties a key-value map of additional properties
         * @return the builder instance
         */
        public Builder properties(Map<String, Object> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Subject}.
         *
         * @return a new instance of {@link Subject}
         */
        public Subject build() {
            return new Subject(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the subject.
     *
     * @return the type of the subject
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the identifier of the subject.
     *
     * @return the identifier of the subject
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the source of the subject.
     *
     * @return the source of the subject
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the additional properties of the subject.
     *
     * @return a key-value map of additional properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
}
