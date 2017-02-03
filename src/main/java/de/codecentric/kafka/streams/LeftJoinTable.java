package de.codecentric.kafka.streams;

import de.codecentric.kafka.streams.model.AdClickAndViewEvent;
import de.codecentric.kafka.streams.model.AdClickEvent;
import de.codecentric.kafka.streams.model.AdViewEvent;
import de.codecentric.kafka.streams.serde.AdSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KTable;

import java.util.UUID;

/**
 * Created by ftr on 03/02/2017.
 */
public class LeftJoinTable {

    public static void main(String[] args) {


        new StreamRuntime("innerWindowedJoinTables" + UUID.randomUUID(), 0, 0).run((viewTopic, clickTopic, builder) -> {

            KTable<Long, AdViewEvent> viewStream = builder.table(Serdes.Long(), AdSerdes.AD_VIEW_SERDE, viewTopic, "Views");
            KTable<Long, AdClickEvent> clickStream = builder.table(Serdes.Long(), AdSerdes.AD_CLICK_SERDE, clickTopic, "Clicks");
            KTable<Long, AdClickAndViewEvent> leftJoin = viewStream.leftJoin(clickStream, (view, click) -> {
                AdClickAndViewEvent adClickAndViewEvent = new AdClickAndViewEvent();
                adClickAndViewEvent.setClickEvent(click);
                adClickAndViewEvent.setViewEvent(view);
                return adClickAndViewEvent;


            });
            leftJoin.print();


        });

    }
}
