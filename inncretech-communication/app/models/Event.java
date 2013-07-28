package models;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 25/7/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Event {

    private EventType eventType;
    private Map<String, String> eventData;
    private User user;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Map<String, String> getEventData() {
        return eventData;
    }

    public void setEventData(Map<String, String> eventData) {
        this.eventData = eventData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
