package de.codecentric.kafka.streams.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.kafka.streams.model.AdClickEvent;
import de.codecentric.kafka.streams.model.AdViewEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickEventSerializer implements Serializer<AdClickEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, AdClickEvent adClickEvent) {
        try {
            return mapper.writeValueAsBytes(adClickEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
