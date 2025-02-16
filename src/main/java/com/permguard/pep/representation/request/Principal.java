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
 * Represents the details of a principal, including type, identifier, source, and associated tokens.
 * <p>
 * This class uses the Builder pattern for flexible and fluent object creation.
 * <p>
 * Usage example:
 * <pre>{@code
 * PrincipalDetail principalDetail = new PrincipalDetail.Builder()
 *     .type("user")
 *     .id("amy.smith@acmecorp.com")
 *     .source("keycloak")
 *     .identityToken("eyJhbGciOiJI...")
 *     .accessToken("eyJhbGciOiJI...")
 *     .build();
 * }</pre>
 */
public class Principal {

    private final String type;
    private final String id;
    private final String source;
    private final String identityToken;
    private final String accessToken;

    private Principal(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.source = builder.source;
        this.identityToken = builder.identityToken;
        this.accessToken = builder.accessToken;
    }

    /**
     * Builder class for {@link Principal}.
     */
    public static class Builder {
        private String type;
        private String id;
        private String source;
        private String identityToken;
        private String accessToken;

        public Builder(String type, String id, String source) {
            this.type = type;
            this.id = id;
            this.source = source;
        }

        /**
         * Sets the type of the principal.
         *
         * @param type the type of the principal
         * @return the builder instance
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the identifier of the principal.
         *
         * @param id the identifier of the principal
         * @return the builder instance
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the source of the principal.
         *
         * @param source the source of the principal
         * @return the builder instance
         */
        public Builder source(String source) {
            this.source = source;
            return this;
        }

        /**
         * Sets the identity token of the principal.
         *
         * @param identityToken the identity token of the principal
         * @return the builder instance
         */
        public Builder identityToken(String identityToken) {
            this.identityToken = identityToken;
            return this;
        }

        /**
         * Sets the access token of the principal.
         *
         * @param accessToken the access token of the principal
         * @return the builder instance
         */
        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * Builds and returns an instance of {@link Principal}.
         *
         * @return a new instance of {@link Principal}
         */
        public Principal build() {
            return new Principal(this);
        }
    }

    // Getters with JavaDoc

    /**
     * Gets the type of the principal.
     *
     * @return the type of the principal
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the identifier of the principal.
     *
     * @return the identifier of the principal
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the source of the principal.
     *
     * @return the source of the principal
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the identity token of the principal.
     *
     * @return the identity token of the principal
     */
    public String getIdentityToken() {
        return identityToken;
    }

    /**
     * Gets the access token of the principal.
     *
     * @return the access token of the principal
     */
    public String getAccessToken() {
        return accessToken;
    }
}