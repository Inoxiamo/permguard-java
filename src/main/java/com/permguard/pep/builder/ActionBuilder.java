package com.permguard.pep.builder;

import com.permguard.pep.model.request.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for creating an Action object.
 */
public class ActionBuilder {
    private String name;
    private Map<String, Object> properties = new HashMap<>();

    /**
     * Constructor with required action name.
     *
     * @param name The name of the action.
     */
    public ActionBuilder(String name) {
        this.name = name;
    }

    /**
     * Adds a property to the action.
     *
     * @param key   The property key.
     * @param value The property value.
     * @return The current builder instance.
     */
    public ActionBuilder withProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    /**
     * Builds the Action object.
     *
     * @return A new Action instance.
     */
    public Action build() {
        return new Action(name, properties);
    }
}
