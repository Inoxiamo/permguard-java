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