package com.permguard.pep.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.permguard.pep.model.request.AZRequest;
import com.permguard.pep.utils.ProtobufObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Builder that converts a JSON InputStream into an AZRequest object.
 */
public class AZRequestBuilderFromJson {

    /**
     * Converts a JSON InputStream into an AZRequest.
     *
     * @param jsonInputStream The input JSON stream.
     * @return The corresponding AZRequest object.
     * @throws IOException If the JSON parsing fails.
     */
    public static AZRequest fromJson(InputStream jsonInputStream) throws IOException {
        if (jsonInputStream == null) {
            throw new IOException("❌ InputStream is null. Cannot parse JSON.");
        }

        // ✅ Use ProtobufObjectMapper to automatically handle Protobuf fields
        ObjectMapper objectMapper = new ProtobufObjectMapper();

        // Convert InputStream to AZRequest
        return objectMapper.readValue(jsonInputStream, AZRequest.class);
    }

    /**
     * Converts a JSON string into an AZRequest.
     *
     * @param jsonString The input JSON string.
     * @return The corresponding AZRequest object.
     * @throws IOException If the JSON parsing fails.
     */
    public static AZRequest fromJson(String jsonString) throws IOException {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            throw new IOException("❌ JSON string is empty. Cannot parse.");
        }

        // ✅ Use ProtobufObjectMapper to automatically handle Protobuf fields
        ObjectMapper objectMapper = new ProtobufObjectMapper();

        // Convert JSON string to AZRequest
        return objectMapper.readValue(jsonString, AZRequest.class);
    }
}
