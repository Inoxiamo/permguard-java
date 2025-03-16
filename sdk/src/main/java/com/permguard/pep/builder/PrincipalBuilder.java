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

package com.permguard.pep.builder;

import com.permguard.pep.model.request.Principal;

/**
 * Builder for creating a Principal object.
 */
public class PrincipalBuilder {
    private String type = "user"; // Default type
    private String id;
    private String source;
    private String identityToken;
    private String accessToken;

    /**
     * Constructor with required principal ID.
     *
     * @param id The unique identifier for the principal.
     */
    public PrincipalBuilder(String id) {
        this.id = id;
    }

    /**
     * Sets the principal type.
     *
     * @param type The type of the principal.
     * @return The current builder instance.
     */
    public PrincipalBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the source of the principal.
     *
     * @param source The source system of the principal.
     * @return The current builder instance.
     */
    public PrincipalBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Sets the identity token.
     *
     * @param identityToken The identity token for the principal.
     * @return The current builder instance.
     */
    public PrincipalBuilder withIdentityToken(String identityToken) {
        this.identityToken = identityToken;
        return this;
    }

    /**
     * Sets the access token.
     *
     * @param accessToken The access token for the principal.
     * @return The current builder instance.
     */
    public PrincipalBuilder withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * Builds the Principal object.
     *
     * @return A new Principal instance.
     */
    public Principal build() {
        return new Principal(type, id, source, identityToken, accessToken);
    }
}
