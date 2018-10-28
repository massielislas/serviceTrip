package edu.byu.hbll.serviceTrip.mockdata;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Event {

    ArrayList<String> tags = new ArrayList<>();

    //Specifications table
    Integer numberEnrolled;
    BigDecimal cost;
    String place;
    String startDate;
    String endDate;
    String eventDescription;
    String organization;
    String eventName;

    public Event(Integer numberEnrolled, BigDecimal cost, String place, String startDate, String
        endDate,
        String eventDescription, String organization, String eventName) {
        this.numberEnrolled = numberEnrolled;
        this.cost = cost;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
        this.organization = organization;
        this.eventName = eventName;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Integer getNumberEnrolled() {
        return numberEnrolled;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public String getPlace() {
        return place;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOrganization() {
        return organization;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
