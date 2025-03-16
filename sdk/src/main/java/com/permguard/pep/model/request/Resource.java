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
 * Represents the resource entity on which the authorization decision is made.
 */
public class Resource {
    private String type;
    private String id;
    private Map<String, Object> properties;

    /**
     * Default conMap<String, Object>or.
     */
    public Resource() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param type       The type of the resource.
     * @param id         The unique identifier of the resource.
     * @param properties Optional properties associated with the resource.
     */
    public Resource(String type, String id, Map<String, Object> properties) {
        this.type = type;
        this.id = id;
        this.properties = properties;
    }

    /**
     * Gets the resource type.
     *
     * @return The type of the resource.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the resource type.
     *
     * @param type The type of the resource.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the resource ID.
     *
     * @return The ID of the resource.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the resource ID.
     *
     * @param id The ID of the resource.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the properties of the resource.
     *
     * @return The properties of the resource.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the resource.
     *
     * @param properties The properties of the resource.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
