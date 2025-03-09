package com.permguard.pep.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the authorization model used in an authorization request.
 * It includes the zone ID, policy store details, principal, and entity definitions.
 */
public class AZModel {
    @JsonProperty(value = "zone_id")
    private long zoneId;
    @JsonProperty(value = "policy_store")
    private PolicyStore policyStore;
    private Principal principal;
    private Entities entities;

    /**
     * Default conMap<String, Object>or.
     */
    public AZModel() {
    }

    /**
     * ConMap<String, Object>or with parameters.
     *
     * @param zoneId       The ID of the authorization zone.
     * @param policyStore  The policy store associated with the authorization model.
     * @param principal    The principal making the authorization request.
     * @param entities     The entities relevant to the authorization decision.
     */
    @JsonCreator
    public AZModel(
            @JsonProperty("zone_id") long zoneId,
            @JsonProperty("policy_store") PolicyStore policyStore,
            @JsonProperty("principal") Principal principal,
            @JsonProperty("entities") Entities entities) {
        this.zoneId = zoneId;
        this.policyStore = policyStore;
        this.principal = principal;
        this.entities = entities;
    }

    /**
     * Gets the zone ID.
     *
     * @return The zone ID.
     */
    public long getZoneId() {
        return zoneId;
    }

    /**
     * Sets the zone ID.
     *
     * @param zoneId The zone ID.
     */
    public void setZoneId(long zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * Gets the policy store.
     *
     * @return The policy store.
     */
    public PolicyStore getPolicyStore() {
        return policyStore;
    }

    /**
     * Sets the policy store.
     *
     * @param policyStore The policy store.
     */
    public void setPolicyStore(PolicyStore policyStore) {
        this.policyStore = policyStore;
    }

    /**
     * Gets the principal.
     *
     * @return The principal.
     */
    public Principal getPrincipal() {
        return principal;
    }

    /**
     * Sets the principal.
     *
     * @param principal The principal.
     */
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    /**
     * Gets the entities.
     *
     * @return The entities.
     */
    public Entities getEntities() {
        return entities;
    }

    /**
     * Sets the entities.
     *
     * @param entities The entities.
     */
    public void setEntities(Entities entities) {
        this.entities = entities;
    }
}
