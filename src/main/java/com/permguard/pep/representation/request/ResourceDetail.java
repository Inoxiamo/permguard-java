package com.permguard.pep.representation.request;

import java.util.Map;

public class ResourceDetail {
    private String type;
    private String id;
    private Map<String, Object> properties;

    public ResourceDetail(String type, String id, Map<String, Object> properties) {
        this.type = type;
        this.id = id;
        this.properties = properties;
    }

    public ResourceDetail() {
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

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
