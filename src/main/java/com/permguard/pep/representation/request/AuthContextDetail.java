/**
 *   Copyright 2024 Nitro Agility S.r.l.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  SPDX-License-Identifier: Apache-2.0
  */

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
