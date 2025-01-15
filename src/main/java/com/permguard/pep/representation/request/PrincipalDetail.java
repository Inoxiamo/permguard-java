package com.permguard.pep.representation.request;

public class PrincipalDetail {
    private String type;
    private String id;
    private String source;
    private String identityToken;
    private String accessToken;

    public PrincipalDetail(String type, String id, String source, String identityToken, String accessToken) {
        this.type = type;
        this.id = id;
        this.source = source;
        this.identityToken = identityToken;
        this.accessToken = accessToken;
    }

    public PrincipalDetail() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIdentityToken() {
        return identityToken;
    }

    public void setIdentityToken(String identityToken) {
        this.identityToken = identityToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
