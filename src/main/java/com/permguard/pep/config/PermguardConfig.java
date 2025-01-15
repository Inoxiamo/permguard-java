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
