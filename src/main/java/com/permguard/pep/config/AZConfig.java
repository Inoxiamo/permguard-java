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
