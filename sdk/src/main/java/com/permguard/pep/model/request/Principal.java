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

package com.permguard.pep.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a principal entity making an authorization request.
 * A principal can be a user, service, or any entity that initiates the request.
 */
public class Principal {
    private String type;
    private String id;
    private String source;
    @JsonProperty(value = "identity_token")
    private String identityToken;
    @JsonProperty(value = "access_token")
    private String accessToken;

    /**
     * Default conMap<String, Object>or.
     */
    public Principal() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param type           The type of the principal.
     * @param id             The unique identifier of the principal.
     * @param source         The source system of the principal.
     * @param identityToken  An optional identity token for authentication.
     * @param accessToken    An optional access token for authorization.
     */
    public Principal(String type, String id, String source, String identityToken, String accessToken) {
        this.type = type;
        this.id = id;
        this.source = source;
        this.identityToken = identityToken;
        this.accessToken = accessToken;
    }

    /**
     * Gets the principal type.
     *
     * @return The type of the principal.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the principal type.
     *
     * @param type The type of the principal.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the principal ID.
     *
     * @return The ID of the principal.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the principal ID.
     *
     * @param id The ID of the principal.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the source of the principal.
     *
     * @return The source of the principal.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the principal.
     *
     * @param source The source of the principal.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets the identity token.
     *
     * @return The identity token, if available.
     */
    public String getIdentityToken() {
        return identityToken;
    }

    /**
     * Sets the identity token.
     *
     * @param identityToken The identity token.
     */
    public void setIdentityToken(String identityToken) {
        this.identityToken = identityToken;
    }

    /**
     * Gets the access token.
     *
     * @return The access token, if available.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets the access token.
     *
     * @param accessToken The access token.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
