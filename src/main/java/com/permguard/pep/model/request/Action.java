package com.permguard.pep.model.request;


import java.util.Map;

/**
 * Represents the action being evaluated in an authorization decision.
 */
public class Action {
    private String name;
    private Map<String, Object> properties;

    /**
     * Default conMap<String, Object>or.
     */
    public Action() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param name       The name of the action.
     * @param properties Optional properties associated with the action.
     */
    public Action(String name, Map<String, Object> properties) {
        this.name = name;
        this.properties = properties;
    }

    /**
     * Gets the action name.
     *
     * @return The name of the action.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the action name.
     *
     * @param name The name of the action.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the properties of the action.
     *
     * @return The properties of the action.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the action.
     *
     * @param properties The properties of the action.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
