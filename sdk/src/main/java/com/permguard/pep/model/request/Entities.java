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

import java.util.List;
import java.util.Map;

/**
 * Represents a collection of entities that are provided in the context
 * of an authorization decision.
 */
public class Entities {
    private String schema;
    private List<Map<String, Object>> items;

    /**
     * Default conMap<String, Object>or.
     */
    public Entities() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param schema The schema associated with the entities.
     * @param items  The list of entity Map<String, Object>ures.
     */
    public Entities(String schema, List<Map<String, Object>> items) {
        this.schema = schema;
        this.items = items;
    }

    /**
     * Gets the schema associated with the entities.
     *
     * @return The schema name.
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Sets the schema associated with the entities.
     *
     * @param schema The schema name.
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * Gets the list of entity items.
     *
     * @return The list of entity items.
     */
    public List<Map<String, Object>> getItems() {
        return items;
    }

    /**
     * Sets the list of entity items.
     *
     * @param items The list of entity items.
     */
    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }
}
