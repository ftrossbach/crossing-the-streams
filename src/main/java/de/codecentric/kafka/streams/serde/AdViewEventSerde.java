package de.codecentric.kafka.streams.serde;

import de.codecentric.kafka.streams.model.AdViewEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdViewEventSerde implements Serde<AdViewEvent> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<AdViewEvent> serializer() {
        return new AdViewEventSerializer();
    }

    public Deserializer<AdViewEvent> deserializer() {
        return new AdViewEventDeserializer();
    }
}
