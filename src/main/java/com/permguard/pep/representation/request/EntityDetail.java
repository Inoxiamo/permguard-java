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

/**
 * Represents the details of an entity in the system.
 * <p>
 * This class includes the schema name and a list of associated item details.
 * It uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * EntityDetail entityDetail = new EntityDetail.Builder()
 *     .schema("cedar")
 *     .items(List.of(item1, item2))
 *     .build();
 * }</pre>
 */
public class EntityDetail {

    private final String schema;
    private final List<ItemDetails> itemDetails;

    private EntityDetail(Builder builder) {
        this.schema = builder.schema;
        this.itemDetails = builder.itemDetails;
    }

    /**
     * Builder class for {@link EntityDetail}.
     */
    public static class Builder {
        private String schema;
        private List<ItemDetails> itemDetails;

        /**
         * Sets the schema name.
         *
         * @param schema the schema name
         * @return the builder instance
         */
        public Builder schema(String schema) {
            this.schema = schema;
            return this;
        }

        /**
         * Sets the list of item details.
         *
         * @param itemDetails the list of item details
         * @return the builder instance
         */
        public Builder items(List<ItemDetails> itemDetails) {
            this.itemDetails = itemDetails;
            return this;
        }

        /**
         * Builds and returns an instance of {@link EntityDetail}.
         *
         * @return a new instance of {@link EntityDetail}
         */
        public EntityDetail build() {
            return new EntityDetail(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the schema name.
     *
     * @return the schema name
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Gets the list of item details.
     *
     * @return the list of item details
     */
    public List<ItemDetails> getItems() {
        return itemDetails;
    }
}