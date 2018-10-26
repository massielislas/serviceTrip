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

  @Inject
  Servi

  @PostConstruct
  public void init() {

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
        UUID eventID = UUID.randomUUID();
        String eventName = mockDataGenerator.getRandomEventName();
        String eventDescription = mockDataGenerator.getRandomEventDescription();
        runner.update
        int cost = mockDataGenerator.getRandomCost();
        int numberEnrolled = mockDataGenerator.getRandomNumberEnrolled();
        for(int k = 0; k < 3; k++){
          String tag = mockDataGenerator.getRandomTag();
        }
      }
    }
  }

}