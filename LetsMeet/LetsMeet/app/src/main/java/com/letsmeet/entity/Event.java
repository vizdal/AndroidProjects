package com.letsmeet.entity;

import java.util.Date;

/**
 * Created by Shrinivas Khandekar on 2018-02-18.
 */
public class Event {
    private String eventName;
    private String eventDescription;
    private Date eventCreationDate;
    private Date eventDate;
    private String category;
    private String location;
    private String type;
    private Integer capacity;
    private String username;

    public Event() {}

    public Event(String eventName, String eventDescription, Date eventCreationDate, Date eventDate, String category, String location, String type, Integer capacity, String username) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventCreationDate = eventCreationDate;
        this.eventDate = eventDate;
        this.category = category;
        this.location = location;
        this.type = type;
        this.capacity = capacity;
        this.username = username;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public Date getEventCreationDate() {
        return eventCreationDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getUsername() {
        return username;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventCreationDate(Date eventCreationDate) {
        this.eventCreationDate = eventCreationDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventCreationDate=" + eventCreationDate +
                ", eventDate=" + eventDate +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", username='" + username + '\'' +
                '}';
    }
}
