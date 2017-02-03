package de.codecentric.kafka.streams;

import de.codecentric.kafka.streams.model.AdClickEvent;
import de.codecentric.kafka.streams.model.AdViewEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by ftr on 03/02/2017.
 * bin/kafka-topics.sh --create  --zookeeper localhost:2181 --config message.timestamp.type=CreateTime --partitions 2 --replication-factor 1 --topic view
 * bin/kafka-topics.sh --create  --zookeeper localhost:2181 --config message.timestamp.type=CreateTime --partitions 2 --replication-factor 1 --topic click
 *
 */
public class StreamRuntime {

    final private String appId;
    final private int viewPartition;
    final private int clickPartition;
    final private String viewTopic;
    final private String clickTopic;

    final Producer<Long, AdViewEvent> viewProducer;
    final Producer<Long, AdClickEvent> clickProducer;

    public StreamRuntime(String appId,  int viewPartition, int clickPartition) {
        this.appId = appId;
        this.viewPartition = viewPartition;
        this.clickPartition = clickPartition;
        this.viewTopic = "view"+ UUID.randomUUID().toString();
        this.clickTopic = "click"+ UUID.randomUUID().toString();
        Properties viewProps = new Properties();
        viewProps.put("bootstrap.servers", "localhost:9092");
        viewProps.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        viewProps.put("value.serializer", "de.codecentric.kafka.streams.serde.AdViewEventSerializer");
        viewProps.put("linger.ms", 100);

        Properties clickProps = new Properties();
        clickProps.put("bootstrap.servers", "localhost:9092");
        clickProps.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        clickProps.put("value.serializer", "de.codecentric.kafka.streams.serde.AdClickEventSerializer");
        clickProps.put("linger.ms", 100);

        viewProducer = new KafkaProducer<Long, AdViewEvent>(viewProps);
        clickProducer = new KafkaProducer<Long, AdClickEvent>(clickProps);
    }

    public void run(TopologyBuilder topologyBuilder) {
        sendEvents();

       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        StreamsConfig config = new StreamsConfig(props);


        KStreamBuilder builder = new KStreamBuilder();

        topologyBuilder.buildTopology(viewTopic,clickTopic, builder);

        new KafkaStreams(builder, config).start();





    }

    private void sendEvents() {
        sendView(0, "click 1000 ms after view", 0);
        sendClick(0,  1000);

        sendView(1, "click 10,000 ms after view", 0);
        sendClick(1,  10000);

        sendView(2, "click 1000 ms before view", 1000);
        sendClick(2,  0);

        sendView(3, "no click", 0);

        //no view
        sendClick(4,  0);

        sendView(5, "duplicate view event1", 0);
        sendView(5, "duplicate view event2", 1);
        sendClick(5,  1000);


        sendView(6, "duplicate click 500 ms and 800 ms after view", 0);
        sendClick(6,  500);
        sendClick(6,  800);
    }

    public void sendView(long adDeliveryId, String adId, long timestamp){
        AdViewEvent adViewEvent = new AdViewEvent();
        adViewEvent.setAdDeliveryId(adDeliveryId);
        adViewEvent.setAdId(adId);
        adViewEvent.setTimestamp(timestamp);
        viewProducer.send(new ProducerRecord<Long, AdViewEvent>(viewTopic, viewPartition, timestamp, adDeliveryId, adViewEvent));
    }

    public void sendClick(long adDeliveryId, long timestamp){
        AdClickEvent adClickEvent = new AdClickEvent();
        adClickEvent.setAdDeliveryId(adDeliveryId);
        adClickEvent.setTimestamp(timestamp);
        clickProducer.send(new ProducerRecord<Long, AdClickEvent>(clickTopic, clickPartition, timestamp, adDeliveryId, adClickEvent));
    }


}
