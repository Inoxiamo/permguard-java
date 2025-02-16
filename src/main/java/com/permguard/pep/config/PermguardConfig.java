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

public class PermguardConfig {
    private final String host;
    private final int port;
    private final boolean usePlaintext;

    private PermguardConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.usePlaintext = builder.usePlaintext;
    }

    public static class Builder {
        private String host = "localhost"; // Default host
        private int port = 9094;        // Default port
        private boolean usePlaintext = true; // Default usePlaintext

        /**
         * Creates a builder instance with the specified host and port.
         * @param host The host address.
         * @param port The port number.
         */
        public Builder(String host, int port) {
            this.host = host;
            this.port = port;
            this.usePlaintext = true;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder usePlaintext(boolean usePlaintext) {
            this.usePlaintext = usePlaintext;
            return this;
        }


        public PermguardConfig build() {
            return new PermguardConfig(this);
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isUsePlaintext() {
        return usePlaintext;
    }
}
