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

import java.util.List;
import java.util.Map;

public class AuthRequestPayload {

    private AuthContextDetail authContextDetail;
    private SubjectDetail subject;
    private ResourceDetail resource;
    private ActionDetail action;
    private List<EvaluationRequestDetail> evaluations;
    private Map<String, Object> context;

    public AuthRequestPayload() {
    }

    public AuthRequestPayload(AuthContextDetail authContextDetail, SubjectDetail subject, ResourceDetail resource, ActionDetail action, List<EvaluationRequestDetail> evaluations, Map<String, Object> context) {
        this.authContextDetail = authContextDetail;
        this.subject = subject;
        this.resource = resource;
        this.action = action;
        this.evaluations = evaluations;
        this.context = context;
    }

    // Getters and Setters

    public SubjectDetail getSubject() {
        return subject;
    }

    public void setSubject(SubjectDetail subject) {
        this.subject = subject;
    }

    public ResourceDetail getResource() {
        return resource;
    }

    public void setResource(ResourceDetail resource) {
        this.resource = resource;
    }

    public ActionDetail getAction() {
        return action;
    }

    public void setAction(ActionDetail action) {
        this.action = action;
    }

    public List<EvaluationRequestDetail> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<EvaluationRequestDetail> evaluations) {
        this.evaluations = evaluations;
    }


    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public AuthContextDetail getAuthContextDetail() {
        return authContextDetail;
    }

    public void setAuthContextDetail(AuthContextDetail authContextDetail) {
        this.authContextDetail = authContextDetail;
    }
}


