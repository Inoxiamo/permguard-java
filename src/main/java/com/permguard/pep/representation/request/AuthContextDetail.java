package com.permguard.pep.representation.request;


public class AuthContextDetail {

    private long applicationId;
    private EntityDetail entityDetail;
    private PolicyStoreDetail policyStore;
    private PrincipalDetail principal;

    public AuthContextDetail(long applicationId, EntityDetail entityDetail, PolicyStoreDetail policyStore, PrincipalDetail principal) {
        this.applicationId = applicationId;
        this.entityDetail = entityDetail;
        this.policyStore = policyStore;
        this.principal = principal;
    }

    public AuthContextDetail() {
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public EntityDetail getEntityDetail() {
        return entityDetail;
    }

    public void setEntityDetail(EntityDetail entityDetail) {
        this.entityDetail = entityDetail;
    }

    public PolicyStoreDetail getPolicyStore() {
        return policyStore;
    }

    public void setPolicyStore(PolicyStoreDetail policyStore) {
        this.policyStore = policyStore;
    }

    public PrincipalDetail getPrincipal() {
        return principal;
    }

    public void setPrincipal(PrincipalDetail principal) {
        this.principal = principal;
    }
}
