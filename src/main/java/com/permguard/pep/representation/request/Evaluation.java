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
 * Represents an authorization evaluation, associating a Subject, a Resource, and an Action,
 * with a request identifier and additional context.
 *
 * Example usage:
 * <pre>{@code
 * Evaluation evaluation = new Evaluation.Builder(subject, resource, action)
 *     .requestId("request-1234")
 *     .addContext("time", "2025-01-23T16:17:46+00:00")
 *     .build();
 * }</pre>
 */
@JsonDeserialize(builder = Evaluation.Builder.class)
public final class Evaluation {

    private final Subject subject;
    private final Resource resource;
    private final Action action;
    private final String requestId;
    private final Map<String, Object> context;

    private Evaluation(Builder builder) {
        this.subject = builder.subject;
        this.resource = builder.resource;
        this.action = builder.action;
        this.requestId = builder.requestId;
        this.context = builder.context != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.context))
                : Collections.emptyMap();
    }

    public Subject getSubject() {
        return subject;
    }

    public Resource getResource() {
        return resource;
    }

    public Action getAction() {
        return action;
    }

    public String getRequestId() {
        return requestId;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final Subject subject;
        private final Resource resource;
        private final Action action;
        private String requestId;
        private Map<String, Object> context = new HashMap<>();

        public Builder(Subject subject, Resource resource, Action action) {
            this.subject = subject;
            this.resource = resource;
            this.action = action;
        }

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder context(Map<String, Object> context) {
            this.context = new HashMap<>(context);
            return this;
        }

        public Builder addContext(String key, Object value) {
            this.context.put(key, value);
            return this;
        }

        public Evaluation build() {
            if (this.requestId == null) {
                throw new IllegalStateException("requestId is required");
            }
            return new Evaluation(this);
        }
    }
}
