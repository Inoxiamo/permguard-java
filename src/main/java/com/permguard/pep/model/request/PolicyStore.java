package com.permguard.pep.model.request;

/**
 * Represents a policy store where policies are maintained.
 * It includes the type of policy store and its identifier.
 */
public class PolicyStore {
    private String type;
    private String id;

    /**
     * Default conMap<String, Object>or.
     */
    public PolicyStore() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param type The type of the policy store.
     * @param id   The identifier of the policy store.
     */
    public PolicyStore(String type, String id) {
        this.type = type;
        this.id = id;
    }

    /**
     * Gets the policy store type.
     *
     * @return The type of the policy store.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the policy store type.
     *
     * @param type The type of the policy store.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the policy store ID.
     *
     * @return The ID of the policy store.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the policy store ID.
     *
     * @param id The ID of the policy store.
     */
    public void setId(String id) {
        this.id = id;
    }
}
