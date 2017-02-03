package de.codecentric.kafka.streams;

import org.apache.kafka.streams.kstream.KStreamBuilder;

/**
 * Created by ftr on 03/02/2017.
 */
@FunctionalInterface
public interface TopologyBuilder {

    void buildTopology(String viewTopic, String clickTopic, KStreamBuilder builder);
}
