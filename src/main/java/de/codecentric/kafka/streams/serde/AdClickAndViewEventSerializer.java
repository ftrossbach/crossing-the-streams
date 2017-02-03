package de.codecentric.kafka.streams.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.kafka.streams.model.AdClickAndViewEvent;
import de.codecentric.kafka.streams.model.AdClickEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickAndViewEventSerializer implements Serializer<AdClickAndViewEvent> {

    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, AdClickAndViewEvent event) {
        try {
            return mapper.writeValueAsBytes(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {

    }
}
