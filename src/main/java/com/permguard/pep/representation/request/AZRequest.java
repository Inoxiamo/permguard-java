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
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents a non-atomic authorization request.
 * <p>
 * Example of use:
 * <pre>{@code
 * AZRequest request = new AZRequest.Builder(204510383118L, "181e252e247747338ad062abad0086a5")
 *     .principal(principal)
 *     .entities("cedar", List.of(entity))
 *     .evaluation(evaluation1)
 *     .evaluation(evaluation2)
 *     .build();
 * }</pre>
 * </p>
 */
@JsonDeserialize(builder = AZRequest.Builder.class)
public final class AZRequest {

    private final long zoneId;
    private final String policyCodeId;
    private final Principal principal;
    private final Map<String, List<Entity>> entities;
    private final List<Evaluation> evaluations;

    private AZRequest(Builder builder) {
        this.zoneId = builder.zoneId;
        this.policyCodeId = builder.policyCodeId;
        this.principal = builder.principal;
        this.entities = builder.entities != null
                ? Collections.unmodifiableMap(new HashMap<>(builder.entities))
                : Collections.emptyMap();
        this.evaluations = builder.evaluations != null
                ? Collections.unmodifiableList(new ArrayList<>(builder.evaluations))
                : Collections.emptyList();
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

    public Map<String, List<Entity>> getEntities() {
        return entities;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private final long zoneId;
        private final String policyCodeId;
        private Principal principal;
        private Map<String, List<Entity>> entities = new HashMap<>();
        private List<Evaluation> evaluations = new ArrayList<>();

        /**
         * Mandatory constructor.
         *
         * @param zoneId       the zone identifier
         * @param policyCodeId the policy store identifier
         */
        public Builder(long zoneId, String policyCodeId) {
            this.zoneId = zoneId;
            this.policyCodeId = policyCodeId;
        }

        public Builder principal(Principal principal) {
            this.principal = principal;
            return this;
        }

        public Builder entities(String key, List<Entity> entities) {
            this.entities.put(key, entities);
            return this;
        }

        public Builder evaluation(Evaluation evaluation) {
            this.evaluations.add(evaluation);
            return this;
        }

        public AZRequest build() {
            if (principal == null) {
                throw new IllegalStateException("Principal must not be null");
            }
            return new AZRequest(this);
        }
    }
}

