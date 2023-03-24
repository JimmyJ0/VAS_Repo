package de.leuphana.customer.configuration;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

public class IntegerSerializer implements Serializer<Integer> {

    @Override
    public byte[] serialize(String topic, Integer data) {
        if (data == null) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(data);
        return buffer.array();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No custom configuration is needed for this serializer
    }

    @Override
    public void close() {
        // No resources need to be cleaned up for this serializer
    }
}
