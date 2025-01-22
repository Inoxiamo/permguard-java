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
