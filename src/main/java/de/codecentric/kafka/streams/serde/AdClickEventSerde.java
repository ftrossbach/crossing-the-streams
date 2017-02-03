package de.codecentric.kafka.streams.serde;

import de.codecentric.kafka.streams.model.AdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickEventSerde implements Serde<AdClickEvent> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<AdClickEvent> serializer() {
        return new AdClickEventSerializer();
    }

    public Deserializer<AdClickEvent> deserializer() {
        return new AdClickEventDeserializer();
    }
}
