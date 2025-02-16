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
 * Represents the details of a resource, including its type, identifier, and additional properties.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * ResourceDetail resourceDetail = new ResourceDetail.Builder()
 *     .type("MagicFarmacia::Platform::Subscription")
 *     .id("e3a786fd07e24bfa95ba4341d3695ae8")
 *     .properties(Map.of("key1", "value1"))
 *     .build();
 * }</pre>
 */
public class Resource {

    private final String type;
    private final String id;
    private final Map<String, Object> properties;

    private Resource(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.properties = builder.properties;
    }

    /**
     * Builder class for {@link Resource}.
     */
    public static class Builder {
        private String type;
        private String id;
        private Map<String, Object> properties;

        public Builder(String type, String id, Map<String, Object> properties) {
            this.type = type;
            this.id = id;
            this.properties = properties;
        }

        /**
         * Sets the type of the resource.
         *
         * @param type the type of the resource
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the identifier of the resource.
         *
         * @param id the identifier of the resource
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the additional properties of the resource.
         *
         * @param properties a key-value map of properties
         * @return the builder instance
         */
        public Builder properties(Map<String, Object> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Resource}.
         *
         * @return a new instance of {@link Resource}
         */
        public Resource build() {
            return new Resource(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the resource.
     *
     * @return the type of the resource
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the identifier of the resource.
     *
     * @return the identifier of the resource
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the additional properties of the resource.
     *
     * @return a key-value map of properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
}
