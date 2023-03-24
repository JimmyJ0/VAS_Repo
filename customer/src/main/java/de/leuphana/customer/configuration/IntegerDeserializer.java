package de.leuphana.customer.configuration;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public class IntegerDeserializer implements Deserializer<Integer> {

    @Override
    public Integer deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        return buffer.getInt();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No custom configuration is needed for this deserializer
    }

    @Override
    public void close() {
        // No resources need to be cleaned up for this deserializer
    }
}
