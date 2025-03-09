package com.permguard.pep.builder;


import com.permguard.pep.model.request.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating a Subject object.
 */
public class SubjectBuilder {
    private String type = "user"; // Default type
    private String id;
    private String source;
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Constructor with required subject ID.
     *
     * @param id The unique identifier for the subject.
     */
    public SubjectBuilder(String id) {
        this.id = id;
    }

    /**
     * Sets the subject type.
     *
     * @param type The type of the subject.
     * @return The current builder instance.
     */
    public SubjectBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the source of the subject.
     *
     * @param source The source system of the subject.
     * @return The current builder instance.
     */
    public SubjectBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Adds a property to the subject.
     *
     * @param key   The property key.
     * @param value The property value.
     * @return The current builder instance.
     */
    public SubjectBuilder withProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    /**
     * Builds the Subject object.
     *
     * @return A new Subject instance.
     */
    public Subject build() {
        return new Subject(type, id, source, properties);
    }
}
