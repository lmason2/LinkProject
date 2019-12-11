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

    /**
     * DVC that fills the event with n/a for all sections
     */
    public Event() {
        title = "n/a";
        description = "n/a";
        location = "n/a";
        date = "n/a";
        start = "n/a";
        end = "n/a";
    }

    /**
     * EVC that sets events private data members
     * @param title of the event
     * @param description of the event
     * @param location of the event
     * @param date of the event
     * @param start time of the event
     * @param end time of the event
     */
    public Event(String title, String description, String location,
                 String date, String start, String end) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    /**
     * To string of an event to show in listview
     * @return the string version of an event
     */
    @Override
    public String toString() {
        return title + "    Date:" + date + "   Start Time:" + start;
    }

    /**
     * Getter for title
     * @return title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title of the event
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description
     * @return description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location
     * @return Location of the event
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for date
     * @return Date of the event
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for start
     * @return Start time fo the event
     */
    public String getStart() {
        return start;
    }

    /**
     * Getter for end
     * @return end time of the event
     */
    public String getEnd() {
        return end;
    }

    /**
     * Setter for location
     * @param location of the event
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Setter for date
     * @param date of the event
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter for start
     * @param start time of the event
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Setter for end
     * @param end time of the event
     */
    public void setEnd(String end) {
        this.end = end;
    }
}
