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


import com.permguard.pep.model.request.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating a Resource object.
 */
public class ResourceBuilder {
    private String type;
    private String id;
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Constructor with required resource type.
     *
     * @param type The type of the resource.
     */
    public ResourceBuilder(String type) {
        this.type = type;
    }

    /**
     * Sets the resource ID.
     *
     * @param id The unique identifier for the resource.
     * @return The current builder instance.
     */
    public ResourceBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Adds a property to the resource.
     *
     * @param key   The property key.
     * @param value The property value.
     * @return The current builder instance.
     */
    public ResourceBuilder withProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    /**
     * Builds the Resource object.
     *
     * @return A new Resource instance.
     */
    public Resource build() {
        return new Resource(type, id, properties);
    }
}
