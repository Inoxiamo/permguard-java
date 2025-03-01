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
import java.util.List;
import java.util.ArrayList;

/**
 * Represents an atomic authorization request for a single evaluation.
 * <p>
 * Example of use:
 * <pre> {@code
 * AZAtomicRequest request = new AZAtomicRequest.Builder(
 *         204510383118L,
 *         "181e252e247747338ad062abad0086a5",
 *         "amy.smith@acmecorp.com",
 *         "MagicFarmacia::Platform::Subscription",
 *         "MagicFarmacia::Platform::Action::view")
 *     .requestId("1234")
 *     .principal(principal)
 *     .entitiesItems("cedar", entitiesList)
 *     .subjectKind("user")
 *     .subjectSource("keycloack")
 *     .subjectProperty("isSuperUser", true)
 *     .resourceID("e3a786fd07e24bfa95ba4341d3695ae8")
 *     .resourceProperty("isEnabled", true)
 *     .actionProperty("isEnabled", true)
 *     .contextProperty("time", "2025-01-23T16:17:46+00:00")
 *     .contextProperty("isSubscriptionActive", true)
 *     .build();
 * }</pre>
 * </p>
 */
@JsonDeserialize(builder = AZAtomicRequest.Builder.class)
public final class AZAtomicRequest {

    private final long zoneId;
    private final String policyCodeId;
    private final Principal principal;
    private final String requestId;
    private final String subjectKind;
    private final String subjectSource;
    private final Map<String, Object> subjectProperties;
    private final Resource resource;
    private final Action action;
    private final Map<String, Object> context;
    private final Map<String, List<Entity>> entities;

    private AZAtomicRequest(Builder builder) {
        this.zoneId = builder.zoneId;
        this.policyCodeId = builder.policyCodeId;
        this.principal = builder.principal;
        this.requestId = builder.requestId;
        this.subjectKind = builder.subjectKind;
        this.subjectSource = builder.subjectSource;
        this.subjectProperties = builder.subjectProperties != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.subjectProperties))
                : Collections.emptyMap();
        this.resource = builder.resourceBuilder.build();
        this.action = builder.actionBuilder.build();
        this.context = builder.context != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.context))
                : Collections.emptyMap();
        this.entities = builder.entities != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.entities))
                : Collections.emptyMap();
    }

    public long getZoneId() {
        return zoneId;
    }

    public String getPolicyCodeId() {
        return policyCodeId;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getSubjectKind() {
        return subjectKind;
    }

    public String getSubjectSource() {
        return subjectSource;
    }

    public Map<String, Object> getSubjectProperties() {
        return subjectProperties;
    }

    public Resource getResource() {
        return resource;
    }

    public Action getAction() {
        return action;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public Map<String, List<Entity>> getEntities() {
        return entities;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final long zoneId;
        private final String policyCodeId;
        private Principal principal;
        private String requestId;
        private String subjectKind;
        private String subjectSource;
        private Map<String, Object> subjectProperties = new HashMap<>();
        // Utilizziamo i builder per Resource e Action per ritardare la creazione
        private Resource.Builder resourceBuilder;
        private Action.Builder actionBuilder;
        private Map<String, Object> context = new HashMap<>();
        private Map<String, List<Entity>> entities = new HashMap<>();

        /**
         * Mandatory constructor.
         *
         * @param zoneId         the zone identifier
         * @param policyCodeId   the policy store identifier
         * @param principalEmail the email to create a default principal
         * @param resourceType   the resource type
         * @param actionType     the action type
         */
        public Builder(long zoneId, String policyCodeId, String principalEmail, String resourceType, String actionType) {
            this.zoneId = zoneId;
            this.policyCodeId = policyCodeId;
            this.principal = new Principal.Builder(principalEmail).build();
            this.resourceBuilder = Resource.builder(resourceType);
            this.actionBuilder = Action.builder(actionType);
        }

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder principal(Principal principal) {
            this.principal = principal;
            return this;
        }

        public Builder entitiesItems(String key, List<Entity> entities) {
            this.entities.put(key, entities);
            return this;
        }

        public Builder subjectKind(String subjectKind) {
            this.subjectKind = subjectKind;
            return this;
        }

        public Builder subjectSource(String subjectSource) {
            this.subjectSource = subjectSource;
            return this;
        }

        public Builder subjectProperty(String key, Object value) {
            this.subjectProperties.put(key, value);
            return this;
        }

        public Builder resourceID(String resourceId) {
            this.resourceBuilder.id(resourceId);
            return this;
        }

        public Builder resourceProperty(String key, Object value) {
            this.resourceBuilder.property(key, value);
            return this;
        }

        public Builder actionProperty(String key, Object value) {
            this.actionBuilder.property(key, value);
            return this;
        }

        public Builder contextProperty(String key, Object value) {
            this.context.put(key, value);
            return this;
        }

        public AZAtomicRequest build() {
            return new AZAtomicRequest(this);
        }
    }
}
