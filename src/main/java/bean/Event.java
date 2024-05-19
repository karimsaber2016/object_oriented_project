package bean;

import java.sql.Timestamp;

public class Event {
    private int id;
    private String eventType;
    private String eventData;
    private Timestamp timestamp;

    public Event(String eventType, String eventData) {
        this.eventType = eventType;
        this.eventData = eventData;
    }

    public Event(int id, String eventType, String eventData, Timestamp timestamp) {
        this.id = id;
        this.eventType = eventType;
        this.eventData = eventData;
        this.timestamp = timestamp;
    }

    public final int getId() {
        return id;
    }

    public final String getEventType() {
        return eventType;
    }

    public final String getEventData() {
        return eventData;
    }

    public final Timestamp getTimestamp() {
        return timestamp;
    }

    public final void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
