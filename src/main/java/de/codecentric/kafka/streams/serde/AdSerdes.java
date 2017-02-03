package de.codecentric.kafka.streams.serde;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdSerdes {

    public static AdViewEventSerde AD_VIEW_SERDE = new AdViewEventSerde();
    public static AdClickEventSerde AD_CLICK_SERDE = new AdClickEventSerde();
    public static AdClickAndViewEventSerde AD_CLICK_VIEW_SERDE = new AdClickAndViewEventSerde();
}
