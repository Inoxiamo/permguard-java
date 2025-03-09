package com.permguard.pep.model.request;


import java.util.Map;

/**
 * Represents the resource entity on which the authorization decision is made.
 */
public class Resource {
    private String type;
    private String id;
    private Map<String, Object> properties;

    /**
     * Default conMap<String, Object>or.
     */
    public Resource() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param type       The type of the resource.
     * @param id         The unique identifier of the resource.
     * @param properties Optional properties associated with the resource.
     */
    public Resource(String type, String id, Map<String, Object> properties) {
        this.type = type;
        this.id = id;
        this.properties = properties;
    }

    /**
     * Gets the resource type.
     *
     * @return The type of the resource.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the resource type.
     *
     * @param type The type of the resource.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the resource ID.
     *
     * @return The ID of the resource.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the resource ID.
     *
     * @param id The ID of the resource.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the properties of the resource.
     *
     * @return The properties of the resource.
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * Sets the properties of the resource.
     *
     * @param properties The properties of the resource.
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
