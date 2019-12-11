package com.example.linkproject;
/*
  Event.java
  Luke Mason & JD Gruber
  CPSC 312 Final Project
  Link
  Event class that creates an event object
  to be placed in the Firebase database
*/
public class Event {
    String title;
    String description;
    String location;
    String date;
    String start;
    String end;


    public Event() {
        title = "n/a";
        description = "n/a";
        location = "n/a";
        date = "n/a";
        start = "n/a";
        end = "n/a";
    }

    public Event(String title, String description, String location,
                 String date, String start, String end) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return title + "    Date:" + date + "   Start Time:" + start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
