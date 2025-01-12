package com.github.permguard.java.sdk.config;

public class PermguardConfig {
    private final String host;
    private final int port;
    private final boolean usePlaintext;


    public PermguardConfig(String host, int port, boolean usePlaintext) {
        this.host = host;
        this.port = port;
        this.usePlaintext = usePlaintext;
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
