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


/**
 * Represents the authentication context details used in requests.
 * <p>
 * This class encapsulates the zone ID, entity details, policy store, and principal details,
 * providing all necessary information for the authentication context.
 * <p>
 * Usage example:
 * <pre>{@code
 * AuthContextDetail contextDetail = new AuthContextDetail.Builder()
 *     .zoneId(145748228796L)
 *     .entityDetail(entityDetail)
 *     .policyStore(policyStoreDetail)
 *     .principal(principalDetail)
 *     .build();
 * }</pre>
 */
public class AuthModelDetail {

    private final long zoneId;
    private final EntityDetail entityDetail;
    private final PolicyStoreDetail policyStore;
    private final PrincipalDetail principal;

    private AuthModelDetail(Builder builder) {
        this.zoneId = builder.zoneId;
        this.entityDetail = builder.entityDetail;
        this.policyStore = builder.policyStore;
        this.principal = builder.principal;
    }

    /**
     * Builder class for {@link AuthModelDetail}.
     */
    public static class Builder {
        private long zoneId;
        private EntityDetail entityDetail;
        private PolicyStoreDetail policyStore;
        private PrincipalDetail principal;

        /**
         * Sets the zone ID.
         *
         * @param zoneId the zone ID
         * @return the builder instance
         */
        public Builder zoneId(long zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        /**
         * Sets the entity detail.
         *
         * @param entityDetail the entity detail
         * @return the builder instance
         */
        public Builder entityDetail(EntityDetail entityDetail) {
            this.entityDetail = entityDetail;
            return this;
        }

        /**
         * Sets the policy store detail.
         *
         * @param policyStore the policy store detail
         * @return the builder instance
         */
        public Builder policyStore(PolicyStoreDetail policyStore) {
            this.policyStore = policyStore;
            return this;
        }

        /**
         * Sets the principal detail.
         *
         * @param principal the principal detail
         * @return the builder instance
         */
        public Builder principal(PrincipalDetail principal) {
            this.principal = principal;
            return this;
        }

        /**
         * Builds and returns an instance of {@link AuthModelDetail}.
         *
         * @return a new instance of {@link AuthModelDetail}
         */
        public AuthModelDetail build() {
            return new AuthModelDetail(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the zone ID.
     *
     * @return the zone ID
     */
    public long getZoneId() {
        return zoneId;
    }

    /**
     * Gets the entity detail.
     *
     * @return the entity detail
     */
    public EntityDetail getEntityDetail() {
        return entityDetail;
    }

    /**
     * Gets the policy store detail.
     *
     * @return the policy store detail
     */
    public PolicyStoreDetail getPolicyStore() {
        return policyStore;
    }

    /**
     * Gets the principal detail.
     *
     * @return the principal detail
     */
    public PrincipalDetail getPrincipal() {
        return principal;
    }
}
