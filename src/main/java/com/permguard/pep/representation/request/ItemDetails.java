package com.permguard.pep.representation.request;

import java.util.List;
import java.util.Map;

public class ItemDetails {
    private Uid uid;
    private Map<String, Object> attrs;
    private List<Object> parents;

    public ItemDetails(Uid uid, Map<String, Object> attrs, List<Object> parents) {
        this.uid = uid;
        this.attrs = attrs;
        this.parents = parents;
    }

    public ItemDetails() {
    }

    // Getters e Setters
    public Uid getUid() {
        return uid;
    }

    public void setUid(Uid uid) {
        this.uid = uid;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }

    public List<Object> getParents() {
        return parents;
    }

    public void setParents(List<Object> parents) {
        this.parents = parents;
    }
}