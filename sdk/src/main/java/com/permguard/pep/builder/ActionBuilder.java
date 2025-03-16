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

package com.permguard.pep.builder;

import com.permguard.pep.model.request.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating an Action object.
 */
public class ActionBuilder {
    private String name;
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Constructor with required action name.
     *
     * @param name The name of the action.
     */
    public ActionBuilder(String name) {
        this.name = name;
    }

    /**
     * Adds a property to the action.
     *
     * @param key   The property key.
     * @param value The property value.
     * @return The current builder instance.
     */
    public ActionBuilder withProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    /**
     * Builds the Action object.
     *
     * @return A new Action instance.
     */
    public Action build() {
        return new Action(name, properties);
    }
}
