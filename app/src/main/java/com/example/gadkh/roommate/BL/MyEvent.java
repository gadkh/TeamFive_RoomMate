package com.example.gadkh.roommate.BL;

public class MyEvent {
    private String description;
    private String eventId;
    private String userName;

    public MyEvent() {
    }

    public MyEvent(String description, String eventId, String userName) {
        this.description = description;
        this.eventId = eventId;
        this.userName = userName;

    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
