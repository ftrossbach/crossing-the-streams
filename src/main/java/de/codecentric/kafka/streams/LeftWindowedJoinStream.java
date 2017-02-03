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
public class LeftWindowedJoinStream {

    public static void main(String[] args) {
        new StreamRuntime("leftJoin5000ms" + UUID.randomUUID(), 0, 0).run((viewTopic, clickTopic, builder) -> {

            KStream<Long, AdViewEvent> viewStream = builder.stream(Serdes.Long(), AdSerdes.AD_VIEW_SERDE, viewTopic);
            KStream<Long, AdClickEvent> clickStream = builder.stream(Serdes.Long(), AdSerdes.AD_CLICK_SERDE, clickTopic);
            KStream<Long, AdClickAndViewEvent> leftJoin = viewStream.leftJoin(clickStream, (view, click) -> {
                AdClickAndViewEvent adClickAndViewEvent = new AdClickAndViewEvent();
                adClickAndViewEvent.setClickEvent(click);
                adClickAndViewEvent.setViewEvent(view);
                return adClickAndViewEvent;


            }, JoinWindows.of(5000), Serdes.Long(), AdSerdes.AD_VIEW_SERDE, AdSerdes.AD_CLICK_SERDE);
            leftJoin.print();


        });

    }
}
