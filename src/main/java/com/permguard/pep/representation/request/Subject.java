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
 * Rappresenta il subject della richiesta, ovvero il soggetto a cui si riferisce la richiesta.
 * Permette di specificare dati obbligatori (ad esempio l'email) e opzionali tramite una mappa.
 *
 * Esempio d’uso:
 * <pre>{@code
 * Subject subject = new Subject.Builder("amy.smith@acmecorp.com")
 *     .kind("user")
 *     .source("keycloak")
 *     .addProperty("isSuperUser", true)
 *     .build();
 * }</pre>
 */
@JsonDeserialize(builder = Subject.Builder.class)
public final class Subject {

    private final String email;
    private final String kind;
    private final String source;
    private final Map<String, Object> properties;

    private Subject(Builder builder) {
        this.email = builder.email;
        this.kind = builder.kind;
        this.source = builder.source;
        this.properties = builder.properties != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.properties))
                : Collections.emptyMap();
    }

    public String getEmail() {
        return email;
    }

    public String getKind() {
        return kind;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final String email;
        private String kind;
        private String source;
        private Map<String, Object> properties = new HashMap<>();

        public Builder(String email) {
            this.email = email;
        }

        public Builder kind(String kind) {
            this.kind = kind;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder properties(Map<String, Object> properties) {
            this.properties = new HashMap<>(properties);
            return this;
        }

        public Builder addProperty(String key, Object value) {
            this.properties.put(key, value);
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }
}
