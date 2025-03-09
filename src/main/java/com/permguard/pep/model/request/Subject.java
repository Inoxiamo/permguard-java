package com.permguard.pep.model.request;

import com.google.protobuf.Struct;

/**
 * Represents the subject entity on which the authorization decision is made.
 */
public class Subject {
    private String type;
    private String id;
    private String source;
    private Struct properties;

    /**
     * Default constructor.
     */
    public Subject() {
    }

    /**
     * Constructor with parameters.
     *
     * @param type       The type of the subject.
     * @param id         The unique identifier of the subject.
     * @param source     The source system of the subject.
     * @param properties Optional properties associated with the subject.
     */
    public Subject(String type, String id, String source, Struct properties) {
        this.type = type;
        this.id = id;
        this.source = source;
        this.properties = properties;
    }

    /**
     * Gets the subject type.
     *
     * @return The type of the subject.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the subject type.
     *
     * @param type The type of the subject.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the subject ID.
     *
     * @return The ID of the subject.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the subject ID.
     *
     * @param id The ID of the subject.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the source of the subject.
     *
     * @return The source of the subject.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the subject.
     *
     * @param source The source of the subject.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Gets the properties of the subject.
     *
     * @return The properties of the subject.
     */
    public Struct getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the subject.
     *
     * @param properties The properties of the subject.
     */
    public void setProperties(Struct properties) {
        this.properties = properties;
    }
}
