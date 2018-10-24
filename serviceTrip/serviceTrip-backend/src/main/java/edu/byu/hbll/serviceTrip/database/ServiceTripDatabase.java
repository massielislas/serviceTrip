package edu.byu.hbll.serviceTrip.database;


import edu.byu.hbll.serviceTrip.Configuration;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

@Stateless
public class ServiceTripDatabase
{

  @Inject
  Configuration configuration;

  BasicDataSource ds;
  QueryRunner runner;

  @PostConstruct
  public void init(){
    ds = configuration.getDs();
    runner = new QueryRunner(ds);
  }
  
  public void insertEventInformation(String eventId, String eventName, String organization)
      throws SQLException {

    String insert = "INSERT INTO EventInformation (EventId, EventName, Organization) VALUES (?,?,"
        + "?)";
    runner.update(insert, eventId, eventName, organization);
  }

  public void insertMedia(String eventId, String eventDescription) throws SQLException {

    String insert = "INSERT INTO Media (EventId, EventDescription) VALUES (?,?)";
    runner.update(insert, eventId, eventDescription);
  }

  public void insertTag(String eventId, String tag) throws SQLException {

    String insert = "INSERT INTO Tag (EventId, Tag) VALUES (?,?)";
    runner.update(insert, eventId, tag);
  }

  public void insertSpecification(String eventId, String numEnrolled, String cost, String place,
      String startDate, String endDate) throws SQLException {

    String insert = "INSERT INTO Specifications(EventId, NumberEnrolled, Cost, Place, StartDate, "
        + "EndDate ) VALUES (?,?,?,?,?,?)";
    runner.update(insert, eventId, numEnrolled, cost, place, startDate, endDate);

  }

}
