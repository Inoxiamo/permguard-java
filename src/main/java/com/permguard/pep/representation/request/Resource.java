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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Rappresenta i dettagli di una resource, inclusi tipo, identificatore e proprietà aggiuntive.
 *
 * Represents the details of a resource, including type, identifier, and additional properties.
 * <pre>{@code
 * Resource resource = Resource.builder("MagicFarmacia::Platform::Subscription")
 *     .id("e3a786fd07e24bfa95ba4341d3695ae8")
 *     .property("isEnabled", true)
 *     .build();
 * }</pre>
 */
@JsonDeserialize(builder = Resource.Builder.class)
public final class Resource {

    private final String type;
    private final String id;
    private final Map<String, Object> properties;

    private Resource(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.properties = builder.properties != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.properties))
                : Collections.emptyMap();
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonPOJOBuilder(withPrefix = "")

    public static class Builder {
        private String type;
        private String id;
        private Map<String, Object> properties = new HashMap<>();

        public Builder() {}

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder properties(Map<String, Object> properties) {
            this.properties = new HashMap<>(properties);
            return this;
        }

        public Builder property(String key, Object value) {
            this.properties.put(key, value);
            return this;
        }

        public Resource build() {
            if (type == null || id == null) {
                throw new IllegalStateException("Type and id are required");
            }
            return new Resource(this);
        }
    }

    public static Builder builder(String type) {
        Builder b = new Builder();
        b.type(type);
        return b;
    }
}

