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

package com.permguard.pep.config;

/**
 * Configuration class for the AZClient.
 * This class holds the settings required to connect to the Policy Decision Point (PDP) service.
 */
public class AZConfig {
    private String host;
    private int port;
    private boolean usePlaintext;

    /**
     * Default constructor with default settings.
     * Sets the default host to "localhost" and port to 9094 with plaintext enabled.
     */
    public AZConfig() {
        this.host = "localhost";
        this.port = 9094;
        this.usePlaintext = true;
    }

    /**
     * Constructor with custom settings.
     *
     * @param host         The hostname or IP address of the PDP service.
     * @param port         The port number of the PDP service.
     * @param usePlaintext Whether to use plaintext (true) or TLS (false).
     */
    public AZConfig(String host, int port, boolean usePlaintext) {
        this.host = host;
        this.port = port;
        this.usePlaintext = usePlaintext;
    }

    /**
     * Gets the host of the PDP service.
     *
     * @return The host address.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host of the PDP service.
     *
     * @param host The host address.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets the port of the PDP service.
     *
     * @return The port number.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port of the PDP service.
     *
     * @param port The port number.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Checks if plaintext communication is enabled.
     *
     * @return True if plaintext is enabled, false if TLS is required.
     */
    public boolean isUsePlaintext() {
        return usePlaintext;
    }

    /**
     * Sets whether to use plaintext communication.
     *
     * @param usePlaintext True to enable plaintext, false to require TLS.
     */
    public void setUsePlaintext(boolean usePlaintext) {
        this.usePlaintext = usePlaintext;
    }
}
