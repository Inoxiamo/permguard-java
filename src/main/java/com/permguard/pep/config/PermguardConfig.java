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

import com.permguard.pep.representation.request.PolicyStoreDetail;

public class PermguardConfig {
    private final String host;
    private final int port;
    private boolean usePlaintext;
    private PolicyStoreDetail policyStore;
    private long applicationId;

    public PermguardConfig() {
        this.host = "localhost";
        this.port = 9094;
        this.usePlaintext = true;
    }


    public PermguardConfig(String host, int port, boolean usePlaintext) {
        this.host = host;
        this.port = port;
        this.usePlaintext = usePlaintext;
    }

    public PermguardConfig(String host, int port, boolean usePlaintext, PolicyStoreDetail policyStore, long applicationId) {
        this.host = host;
        this.port = port;
        this.usePlaintext = usePlaintext;
        this.policyStore = policyStore;
        this.applicationId = applicationId;
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

    public PolicyStoreDetail getPolicyStore() {
        return policyStore;
    }

    public long getApplicationId() {
        return applicationId;
    }
}
