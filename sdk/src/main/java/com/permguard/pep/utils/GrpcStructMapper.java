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

package com.permguard.pep.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.util.Map;

/**
 * Utility for converting between gRPC Struct and Java Map.
 */
public class GrpcStructMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a Map<String, Object> to a Protobuf Struct.
     *
     * @param map The Java Map to convert.
     * @return A Protobuf Struct representation of the map.
     */
    public static Struct toGrpcStruct(Map<String, Object> map) {
        try {
            String json = objectMapper.writeValueAsString(map);
            Struct.Builder structBuilder = Struct.newBuilder();
            JsonFormat.parser().merge(json, structBuilder);
            return structBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to convert Map to Struct", e);
        }
    }

    /**
     * Converts a Protobuf Struct to a Java Map<String, Object>.
     *
     * @param struct The Protobuf Struct to convert.
     * @return A Java Map representation of the Struct.
     */
    public static Map<String, Object> fromGrpcStruct(Struct struct) {
        try {
            String json = JsonFormat.printer().print(struct);
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to convert Struct to Map", e);
        }
    }
}
