package com.permguard.pep.representation.request;

import java.util.Map;

public class ActionDetail {
    private String name;
    private Map<String, Object> properties;

    public ActionDetail(String name, Map<String, Object> properties) {
        this.name = name;
        this.properties = properties;
    }

    public ActionDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
