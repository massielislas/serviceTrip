package edu.byu.hbll.serviceTrip.database;

import edu.byu.hbll.serviceTrip.mockdata.Event;
import edu.byu.hbll.serviceTrip.mockdata.MockDataGenerator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;


@Stateless
//@SuppressWarnings("CheckStyle")
public class TableFiller {

  MockDataGenerator mockDataGenerator;

  @Inject
  ServiceTripDatabase db;

  @PostConstruct
  public void init() {
    mockDataGenerator = new MockDataGenerator();
  }

  /*
    Loads 5 mock organizations into the database
      Loads 5 events for each organization
        Each event will have 3 tags
   */
  public void fillTables(){
    for (int i = 0; i < 5; i++){
      String organizationName = mockDataGenerator.getRandomOrganizationName();

      for(int j = 0; j < 5; j++){
        //UUID eventID = UUID.randomUUID();
        String eventName = mockDataGenerator.getRandomEventName();
        String eventDescription = mockDataGenerator.getRandomEventDescription();
        int numEnrolled = mockDataGenerator.getRandomNumberEnrolled();
        int cost = mockDataGenerator.getRandomCost();
        String location = mockDataGenerator.getRandomLocation();
        String[] dates = mockDataGenerator.getRandomFutureDates();
        ArrayList<String>tags = new ArrayList<>();
        for(int k = 0; k < 3; k++){
          String tag = mockDataGenerator.getRandomTag();
          tags.add(tag);
        }
        //Integer numberEnrolled, BigDecimal cost, String place, String startDate, String endDate, String eventDescription, String organization, String eventName,
        Event event = new Event(numEnrolled, new BigDecimal(cost), location, dates[0], dates[1], eventDescription, organizationName, eventName, tags);
        db.insertEvent(event);
      }
    }
  }

}