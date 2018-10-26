package edu.byu.hbll.serviceTrip.mockdata;

import java.sql.Date;
import java.util.ArrayList;

public class Event {

    ArrayList<String> tags = new ArrayList<>();

    //Specifications table
    Integer numberEnrolled;
    Integer cost;
    String place;
    String startDate;
    String endDate;
    String eventDescription;

    public Event(Integer numberEnrolled, Integer cost, String place, String startDate, String endDate,
        String eventDescription) {
        this.numberEnrolled = numberEnrolled;
        this.cost = cost;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
    }

}
