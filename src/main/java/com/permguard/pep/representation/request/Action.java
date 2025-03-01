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
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Rappresenta i dettagli di un’azione da eseguire, inclusi nome e proprietà aggiuntive.
 *
 * Esempio d’uso:
 * <pre>{@code
 * Action action = Action.builder("MagicFarmacia::Platform::Action::create")
 *     .property("isEnabled", true)
 *     .build();
 * }</pre>
 */
@JsonDeserialize(builder = Action.Builder.class)
public final class Action {

    private final String name;
    private final Map<String, Object> properties;

    private Action(Builder builder) {
        this.name = builder.name;
        this.properties = builder.properties != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.properties))
                : Collections.emptyMap();
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String name;
        private Map<String, Object> properties = new HashMap<>();

        public Builder() {}

        public Builder name(String name) {
            this.name = name;
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

        public Action build() {
            if (name == null) {
                throw new IllegalStateException("Name is required");
            }
            return new Action(this);
        }
    }

    public static Builder builder(String name) {
        Builder b = new Builder();
        b.name(name);
        return b;
    }
}
