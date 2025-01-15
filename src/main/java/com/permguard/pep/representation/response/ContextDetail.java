package com.permguard.pep.representation.response;

public class ContextDetail {
    private String id;
    private ReasonDetail reasonAdmin;
    private ReasonDetail reasonUser;

    public ContextDetail(String id, ReasonDetail reasonAdmin, ReasonDetail reasonUser) {
        this.id = id;
        this.reasonAdmin = reasonAdmin;
        this.reasonUser = reasonUser;
    }

    public ContextDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReasonDetail getReasonAdmin() {
        return reasonAdmin;
    }

    public void setReasonAdmin(ReasonDetail reasonAdmin) {
        this.reasonAdmin = reasonAdmin;
    }

    public ReasonDetail getReasonUser() {
        return reasonUser;
    }

    public void setReasonUser(ReasonDetail reasonUser) {
        this.reasonUser = reasonUser;
    }
}