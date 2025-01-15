package com.permguard.pep.representation.request;

import java.util.Map;

public class SubjectDetail {
    private String type;
    private String id;
    private String source;
    private Map<String, Object> properties;

    public SubjectDetail(String type, String id, String source, Map<String, Object> properties) {
        this.type = type;
        this.id = id;
        this.source = source;
        this.properties = properties;
    }

    public SubjectDetail() {
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

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
