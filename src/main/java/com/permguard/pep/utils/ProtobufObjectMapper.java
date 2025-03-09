package com.permguard.pep.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;

/**
 * Custom ObjectMapper that automatically registers Protobuf support and deserializers.
 */
public class ProtobufObjectMapper extends ObjectMapper {
    public ProtobufObjectMapper() {
        super();
        // ✅ Register Protobuf support automatically
        this.registerModule(new ProtobufModule());

        // ✅ Register custom Protobuf serializers & deserializers
        SimpleModule module = new SimpleModule();

        this.registerModule(module);
    }
}
