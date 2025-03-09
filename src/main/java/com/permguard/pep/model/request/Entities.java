package com.permguard.pep.model.request;

import java.util.List;
import java.util.Map;

/**
 * Represents a collection of entities that are provided in the context
 * of an authorization decision.
 */
public class Entities {
    private String schema;
    private List<Map<String, Object>> items;

    /**
     * Default conMap<String, Object>or.
     */
    public Entities() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param schema The schema associated with the entities.
     * @param items  The list of entity Map<String, Object>ures.
     */
    public Entities(String schema, List<Map<String, Object>> items) {
        this.schema = schema;
        this.items = items;
    }

    /**
     * Gets the schema associated with the entities.
     *
     * @return The schema name.
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Sets the schema associated with the entities.
     *
     * @param schema The schema name.
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * Gets the list of entity items.
     *
     * @return The list of entity items.
     */
    public List<Map<String, Object>> getItems() {
        return items;
    }

    /**
     * Sets the list of entity items.
     *
     * @param items The list of entity items.
     */
    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }
}
