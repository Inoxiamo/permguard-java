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
 * Represents the details of an item, including its unique identifier (UID),
 * attributes, and parent relationships.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * Item item = new Item.Builder()
 *     .id("id", "asd"))
 *     .type("type", "asd"))
 *     .attrs(Map.of("isSuperUser", true))
 *     .parents(List.of(parent1, parent2))
 *     .build();
 * }</pre>
 */
public class Item {

    private final String type;
    private final String id;
    private final Map<String, Object> attrs;
    private final List<Object> parents;

    private Item(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.attrs = builder.attrs;
        this.parents = builder.parents;
    }

    /**
     * Builder class for {@link Item}.
     */
    public static class Builder {
        private String type;
        private String id;
        private Map<String, Object> attrs;
        private List<Object> parents;

        public Builder(String type, String id, Map<String, Object> attrs, List<Object> parents) {
            this.type = type;
            this.id = id;
            this.attrs = attrs;
            this.parents = parents;
        }

        /**
         * Sets the type of the item.
         *
         * @param type the type for the item
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the id of the item.
         *
         * @param id the id for the item
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the attributes of the item.
         *
         * @param attrs a key-value map of attributes
         * @return the builder instance
         */
        public Builder attrs(Map<String, Object> attrs) {
            this.attrs = attrs;
            return this;
        }

        /**
         * Sets the parent relationships for the item.
         *
         * @param parents a list of parent objects
         * @return the builder instance
         */
        public Builder parents(List<Object> parents) {
            this.parents = parents;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Item}.
         *
         * @return a new instance of {@link Item}
         */
        public Item build() {
            return new Item(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the item.
     *
     * @return the type  for the item
     */
    public String getType() {
        return type;
    }


    /**
     *  Get the id of the item.
     *
     * @return the id of the item
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the attributes of the item.
     *
     * @return a key-value map of attributes
     */
    public Map<String, Object> getAttrs() {
        return attrs;
    }

    /**
     * Gets the parent relationships of the item.
     *
     * @return a list of parent objects
     */
    public List<Object> getParents() {
        return parents;
    }
}