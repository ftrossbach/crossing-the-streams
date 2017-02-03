package de.codecentric.kafka.streams.model;

import java.util.UUID;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdViewEvent {


    private long adDeliveryId;
    private String adId;
    private long timestamp;



    public long getAdDeliveryId() {
        return adDeliveryId;
    }

    public void setAdDeliveryId(long adDeliveryId) {
        this.adDeliveryId = adDeliveryId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AdViewEvent{" +
                "adDeliveryId=" + adDeliveryId +
                ", adId='" + adId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
