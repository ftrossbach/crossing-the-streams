package de.codecentric.kafka.streams.serde;

import de.codecentric.kafka.streams.model.AdClickAndViewEvent;
import de.codecentric.kafka.streams.model.AdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickAndViewEventSerde implements Serde<AdClickAndViewEvent> {
    public void configure(Map<String, ?> map, boolean b) {

    }

    public void close() {

    }

    public Serializer<AdClickAndViewEvent> serializer() {
        return new AdClickAndViewEventSerializer();
    }

    public Deserializer<AdClickAndViewEvent> deserializer() {
        return new AdClickAndViewEventDeserializer();
    }
}
