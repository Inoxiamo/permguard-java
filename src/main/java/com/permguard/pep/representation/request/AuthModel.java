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
 *     .entityDetail(entityDetail)
 *     .policyStore(policyStoreDetail)
 *     .principal(principalDetail)
 *     .build();
 * }</pre>
 */
public class AuthModel {

    private final Entity entity;
    private final PolicyStore policyStore;
    private final Principal principal;

    private AuthModel(Builder builder) {
        this.entity = builder.entity;
        this.policyStore = builder.policyStore;
        this.principal = builder.principal;
    }

    /**
     * Builder class for {@link AuthModel}.
     */
    public static class Builder {
        private Entity entity;
        private PolicyStore policyStore;
        private Principal principal;

        public Builder(Entity entity, PolicyStore policyStore, Principal principal) {
            this.entity = entity;
            this.policyStore = policyStore;
            this.principal = principal;
        }


        /**
         * Sets the entity detail.
         *
         * @param entity the entity detail
         * @return the builder instance
         */
        public Builder entityDetail(Entity entity) {
            this.entity = entity;
            return this;
        }

        /**
         * Sets the policy store detail.
         *
         * @param policyStore the policy store detail
         * @return the builder instance
         */
        public Builder policyStore(PolicyStore policyStore) {
            this.policyStore = policyStore;
            return this;
        }

        /**
         * Sets the principal detail.
         *
         * @param principal the principal detail
         * @return the builder instance
         */
        public Builder principal(Principal principal) {
            this.principal = principal;
            return this;
        }

        /**
         * Builds and returns an instance of {@link AuthModel}.
         *
         * @return a new instance of {@link AuthModel}
         */
        public AuthModel build() {
            return new AuthModel(this);
        }
    }

    // Getters with JavaDoc



    /**
     * Gets the entity detail.
     *
     * @return the entity detail
     */
    public Entity getEntityDetail() {
        return entity;
    }

    /**
     * Gets the policy store detail.
     *
     * @return the policy store detail
     */
    public PolicyStore getPolicyStore() {
        return policyStore;
    }

    /**
     * Gets the principal detail.
     *
     * @return the principal detail
     */
    public Principal getPrincipal() {
        return principal;
    }
}
