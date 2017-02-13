package de.codecentric.kafka.streams;

import de.codecentric.kafka.streams.model.AdClickAndViewEvent;
import de.codecentric.kafka.streams.model.AdClickEvent;
import de.codecentric.kafka.streams.model.AdViewEvent;
import de.codecentric.kafka.streams.serde.AdSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;

import java.util.UUID;

/**
 * Created by ftr on 03/02/2017.
 */
public class InnerWindowedJoinStreamDifferentPartitions {

    public static void main(String[] args) {


        new StreamRuntime("innerWindowedJoin5000ms2" + UUID.randomUUID(), 0, 1).run((viewTopic, clickTopic, builder) -> {

            KStream<Long, AdViewEvent> viewStream = builder.stream(Serdes.Long(), AdSerdes.AD_VIEW_SERDE, viewTopic);
            KStream<Long, AdClickEvent> clickStream = builder.stream(Serdes.Long(), AdSerdes.AD_CLICK_SERDE, clickTopic);
            KStream<Long, AdClickAndViewEvent> innerJoin = viewStream.join(clickStream, (view, click) ->  new AdClickAndViewEvent(view, click)
                    , JoinWindows.of(5000), Serdes.Long(), AdSerdes.AD_VIEW_SERDE, AdSerdes.AD_CLICK_SERDE);
            innerJoin.print();


        });

    }
}
