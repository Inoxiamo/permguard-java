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

/** Represents the principal (the user or entity making the request).
 *
 * Permette di specificare dati obbligatori (ad esempio l'email) e opzionali tramite una mappa.
 *
 * Esempio d’uso:
 * <pre>{@code
 * Principal principal = new Principal.Builder("amy.smith@acmecorp.com")
 *     .type("user")
 *     .source("keycloak")
 *     .addData("department", "sales")
 *     .build();
 * }</pre>
 * <pre>{@code
 *     .addData("department", "sales")
 *     .build();
 * }</pre>
 */
@JsonDeserialize(builder = Principal.Builder.class)
public final class Principal {

    private final String email;
    private final String type;
    private final String source;
    private final Map<String, Object> additionalData;

    private Principal(Builder builder) {
        this.email = builder.email;
        this.type = builder.type;
        this.source = builder.source;
        this.additionalData = builder.additionalData != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.additionalData))
                : Collections.emptyMap();
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final String email;
        private String type;
        private String source;
        private Map<String, Object> additionalData = new HashMap<>();

        public Builder(String email) {
            this.email = email;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder additionalData(Map<String, Object> data) {
            this.additionalData = new HashMap<>(data);
            return this;
        }

        public Builder addData(String key, Object value) {
            this.additionalData.put(key, value);
            return this;
        }

        public Principal build() {
            return new Principal(this);
        }
    }
}
