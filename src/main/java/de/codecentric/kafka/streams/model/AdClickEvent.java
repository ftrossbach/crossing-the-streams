package de.codecentric.kafka.streams.model;

import java.util.UUID;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickEvent {


    private long adDeliveryId;

    private long timestamp;

    @Override
    public String toString() {
        return "AdClickEvent{" +
                "adDeliveryId=" + adDeliveryId +
                ", timestamp=" + timestamp +
                '}';
    }

    public long getAdDeliveryId() {
        return adDeliveryId;
    }

    public void setAdDeliveryId(long adDeliveryId) {
        this.adDeliveryId = adDeliveryId;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
