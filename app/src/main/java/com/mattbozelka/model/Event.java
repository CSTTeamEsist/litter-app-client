package com.mattbozelka.model;

/*
*
* Event - Model Object
*
* This is the Object used to represent an event presented to the user.
*
* Data elements include:
* String eventDate - date of the event
* String eventLocation - location of the event
* String eventId - id of the event stored in DB
*
* Default constructor sets all to null. Non default takes an eventDate, eventLocation, and eventId
* and sets the correct data elements.
*
* Includes getters and setters for each element
*
* */

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
