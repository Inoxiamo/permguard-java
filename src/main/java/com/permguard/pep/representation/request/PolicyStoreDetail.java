package com.permguard.pep.representation.request;

public class PolicyStoreDetail {
    private String type;
    private String id;

    public PolicyStoreDetail(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public PolicyStoreDetail() {
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
}
