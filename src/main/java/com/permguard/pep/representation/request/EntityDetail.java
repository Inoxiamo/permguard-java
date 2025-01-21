package com.permguard.pep.representation.request;

import java.util.List;

public class EntityDetail {
    private String schema;
    private List<ItemDetails> itemDetails;

    public EntityDetail(String name, List<ItemDetails> itemDetails) {
        this.schema = name;
        this.itemDetails = itemDetails;
    }

    public EntityDetail() {
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public List<ItemDetails> getItems() {
        return itemDetails;
    }

    public void setItems(List<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }
}
