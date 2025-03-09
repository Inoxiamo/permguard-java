package com.permguard.pep.model.request;

import com.google.protobuf.Struct;

import java.util.List;

/**
 * Represents a collection of entities that are provided in the context
 * of an authorization decision.
 */
public class Entities {
    private String schema;
    private List<Struct> items;

    /**
     * Default constructor.
     */
    public Entities() {
    }

    /**
     * Constructor with parameters.
     *
     * @param schema The schema associated with the entities.
     * @param items  The list of entity structures.
     */
    public Entities(String schema, List<Struct> items) {
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
    public List<Struct> getItems() {
        return items;
    }

    /**
     * Sets the list of entity items.
     *
     * @param items The list of entity items.
     */
    public void setItems(List<Struct> items) {
        this.items = items;
    }
}
