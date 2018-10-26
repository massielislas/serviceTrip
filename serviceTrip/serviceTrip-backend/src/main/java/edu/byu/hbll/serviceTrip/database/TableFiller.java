package edu.byu.hbll.serviceTrip.database;

import edu.byu.hbll.serviceTrip.Configuration;
import edu.byu.hbll.serviceTrip.mockdata.MockDataGenerator;
import java.sql.SQLException;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;


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
  public void fillTables() throws SQLException{
    for (int i = 0; i < 5; i++){
      String organizationName = mockDataGenerator.getRandomOrganizationName();

      for(int j = 0; j < 5; j++){
        UUID eventID = UUID.randomUUID();
        String eventName = mockDataGenerator.getRandomEventName();
        String eventDescription = mockDataGenerator.getRandomEventDescription();
        int numEnrolled = mockDataGenerator.getRandomNumberEnrolled();
        int cost = mockDataGenerator.getRandomCost();
        String location = mockDataGenerator.getRandomLocation();
        String[] dates = mockDataGenerator.getRandomFutureDates();
        db.insertEventInformation(eventID.toString(),eventName, organizationName);
        db.insertMedia(eventID.toString(), eventDescription);
        db.insertSpecification(eventID.toString(), numEnrolled + "",  cost+ "", location, dates[1], dates[2]);
        for(int k = 0; k < 3; k++){
          String tag = mockDataGenerator.getRandomTag();
          db.insertTag(eventID.toString(), tag);
        }
      }
    }
  }

}