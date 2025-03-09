package com.permguard.pep.model.request;

import com.google.protobuf.Struct;

/**
 * Represents the action being evaluated in an authorization decision.
 */
public class Action {
    private String name;
    private Struct properties;

    /**
     * Default constructor.
     */
    public Action() {
    }

    /**
     * Constructor with parameters.
     *
     * @param name       The name of the action.
     * @param properties Optional properties associated with the action.
     */
    public Action(String name, Struct properties) {
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
    public Struct getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the action.
     *
     * @param properties The properties of the action.
     */
    public void setProperties(Struct properties) {
        this.properties = properties;
    }
}
