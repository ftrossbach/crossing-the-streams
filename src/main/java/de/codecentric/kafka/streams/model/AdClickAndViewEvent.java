package de.codecentric.kafka.streams.model;

/**
 * Created by ftr on 03/02/2017.
 */
public class AdClickAndViewEvent {

    private AdViewEvent viewEvent;
    private AdClickEvent clickEvent;


    public AdViewEvent getViewEvent() {
        return viewEvent;
    }

    public void setViewEvent(AdViewEvent viewEvent) {
        this.viewEvent = viewEvent;
    }

    public AdClickEvent getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(AdClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    public long duration() {
        return clickEvent.getTimestamp() - viewEvent.getTimestamp();
    }

    @Override
    public String toString() {
        return "AdClickAndViewEvent{" +
                "viewEvent=" + viewEvent +
                ", clickEvent=" + clickEvent +
                '}';
    }
}
