package com.permguard.pep.builder;


import com.permguard.pep.model.request.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating a Resource object.
 */
public class ResourceBuilder {
    private String type;
    private String id;
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Constructor with required resource type.
     *
     * @param type The type of the resource.
     */
    public ResourceBuilder(String type) {
        this.type = type;
    }

    /**
     * Sets the resource ID.
     *
     * @param id The unique identifier for the resource.
     * @return The current builder instance.
     */
    public ResourceBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Adds a property to the resource.
     *
     * @param key   The property key.
     * @param value The property value.
     * @return The current builder instance.
     */
    public ResourceBuilder withProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    /**
     * Builds the Resource object.
     *
     * @return A new Resource instance.
     */
    public Resource build() {
        return new Resource(type, id, properties);
    }
}
