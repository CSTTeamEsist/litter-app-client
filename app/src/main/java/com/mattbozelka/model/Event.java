package com.mattbozelka.model;


public class Event {
    private String eventDate;
    private String eventLocation;
    private int eventId;


    public Event() {
        this.eventDate = null;
        this.eventLocation = null;
        this.eventId = -1;
    }

    public Event(String eventDate, String eventLocation, int eventId) {
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
