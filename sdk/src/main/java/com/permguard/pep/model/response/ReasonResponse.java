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

package com.permguard.pep.model.response;

/**
 * Represents the reasoning behind an authorization decision.
 */
public class ReasonResponse {
    private String code;
    private String message;

    /**
     * Default conMap<String, Object>or.
     */
    public ReasonResponse() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param code    The response code associated with the decision.
     * @param message The detailed message explaining the decision.
     */
    public ReasonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the response code.
     *
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the response code.
     *
     * @param code The response code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the response message.
     *
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message The response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
