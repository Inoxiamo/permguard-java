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
 * Represents the details of a policy store, including its type and identifier.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * PolicyStoreDetail policyStoreDetail = new PolicyStoreDetail.Builder()
 *     .type("ledger")
 *     .id("5740a9c648a04f7db08ac2f44a3779da")
 *     .build();
 * }</pre>
 */
public class PolicyStore {
    private final String zoneId;
    private final String type;
    private final String id;

    private PolicyStore(Builder builder) {
        this.zoneId = builder.zoneId;
        this.type = builder.type;
        this.id = builder.id;
    }


    /**
     * Builder class for {@link PolicyStore}.
     */
    public static class Builder {
        private String zoneId;
        private String type;
        private String id;

        public Builder(String zoneId, String type, String id) {
            this.zoneId = zoneId;
            this.type = type;
            this.id = id;
        }

        /**
         * Sets the type of the policy store.
         *
         * @param type the type of the policy store
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the identifier of the policy store.
         *
         * @param id the identifier of the policy store
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

         /**
         * Sets the zoneId of the policy store.
         *
         * @param zoneId the zoneId of the policy store
         * @return the builder instance
         */
        public Builder zoneId(String zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        /**
         * Builds and returns an instance of {@link PolicyStore}.
         *
         * @return a new instance of {@link PolicyStore}
         */
        public PolicyStore build() {
            return new PolicyStore(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the policy store.
     *
     * @return the type of the policy store
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the identifier of the policy store.
     *
     * @return the identifier of the policy store
     */
    public String getId() {
        return id;
    }


    /**
     * Gets the zoneId of the policy store.
     *
     * @return the zoneId of the policy store
     */
    public String getZoneId() {
        return zoneId;
    }

}
