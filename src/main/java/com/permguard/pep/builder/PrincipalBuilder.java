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
