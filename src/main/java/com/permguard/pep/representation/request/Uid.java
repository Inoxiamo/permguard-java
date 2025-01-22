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
 * Represents a unique identifier (UID) with a type and an identifier string.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * Uid uid = new Uid.Builder()
 *     .type("Permguard::IAM::User")
 *     .id("amy.smith@acmecorp.com")
 *     .build();
 * }</pre>
 */
public class Uid {

    private final String type;
    private final String id;

    private Uid(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
    }

    /**
     * Builder class for {@link Uid}.
     */
    public static class Builder {
        private String type;
        private String id;

        /**
         * Sets the type of the UID.
         *
         * @param type the type of the UID
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the identifier string of the UID.
         *
         * @param id the identifier string of the UID
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Uid}.
         *
         * @return a new instance of {@link Uid}
         */
        public Uid build() {
            return new Uid(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the UID.
     *
     * @return the type of the UID
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the identifier string of the UID.
     *
     * @return the identifier string of the UID
     */
    public String getId() {
        return id;
    }
}
