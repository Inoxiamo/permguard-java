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
 * Represents the action being evaluated in an authorization decision.
 */
public class Action {
    private String name;
    private Map<String, Object> properties;

    /**
     * Default conMap<String, Object>or.
     */
    public Action() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param name       The name of the action.
     * @param properties Optional properties associated with the action.
     */
    public Action(String name, Map<String, Object> properties) {
        this.name = name;
        this.properties = properties;
    }

    /**
     * Gets the action name.
     *
     * @return The name of the action.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the action name.
     *
     * @param name The name of the action.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the properties of the action.
     *
     * @return The properties of the action.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the action.
     *
     * @param properties The properties of the action.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
