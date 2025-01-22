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