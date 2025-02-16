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
 * Represents the details of an action to be performed.
 * <p>
 * This class includes the action's name and additional properties as a key-value map.
 * It uses the Builder pattern for flexible and fluent construction.
 * <p>
 * Usage example:
 * <pre>{@code
 * ActionDetail actionDetail = new ActionDetail.Builder()
 *     .name("MagicFarmacia::Platform::Action::create")
 *     .properties(Map.of("key1", "value1", "key2", "value2"))
 *     .build();
 * }</pre>
 */
public class Action {

    private final String name;
    private final Map<String, Object> properties;

    private Action(Builder builder) {
        this.name = builder.name;
        this.properties = builder.properties;
    }

    /**
     * Builder class for {@link Action}.
     */
    public static class Builder {
        private String name;
        private Map<String, Object> properties;


        /**
         * Constructs a new Builder instance with the given name and properties.
         * @param name the name of the action
         * @param properties the properties of the action
         */
        public Builder(String name, Map<String, Object> properties) {
            this.name = name;
            this.properties = new java.util.HashMap<>(properties);
        }

        /**
         * Sets the name of the action.
         *
         * @param name the name of the action
         * @return the builder instance
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the properties of the action.
         *
         * @param properties a key-value map of properties for the action
         * @return the builder instance
         */
        public Builder properties(Map<String, Object> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Action}.
         *
         * @return a new instance of {@link Action}
         */
        public Action build() {
            return new Action(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the name of the action.
     *
     * @return the name of the action
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the properties of the action.
     *
     * @return a key-value map of properties for the action
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
}
