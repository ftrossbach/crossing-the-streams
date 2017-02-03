package de.codecentric.kafka.streams.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.kafka.streams.model.AdClickAndViewEvent;
import de.codecentric.kafka.streams.model.AdClickEvent;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickEventDeserializer implements Deserializer<AdClickEvent>{
    private ObjectMapper mapper = new ObjectMapper();

    public void configure(Map<String, ?> map, boolean b) {

    }

    public AdClickEvent deserialize(String s, byte[] bytes) {

        try {

            return mapper.readValue(bytes, AdClickEvent.class);
        } catch (Exception e) {

            return null;
        }
    }

    public void close() {

    }
}
