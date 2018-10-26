package edu.byu.hbll.serviceTrip.database;


import edu.byu.hbll.serviceTrip.Configuration;
import edu.byu.hbll.serviceTrip.mockdata.Event;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.MapHandler;

@Stateless
@SuppressWarnings("CheckStyle")
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

  public Event selectEvent(String cost, String tag){
    String sql = "SELECT c.* FROM EventInformation i"
        + "JOIN Media m ON (c.EventId = i.EventId)"
        + "LEFT JOIN Tag t on (t.EventId = i.EventId)"
        + "LEFT JOIN Specifications s ON (s.EventID = t.EventID"
        + "WHERE t.Tag=?";

    Map<String, Object> results = runner.query(sql, new MapHandler(), userId);
    //    public Event(Integer numberEnrolled, Integer cost, String place, String startDate, String endDate,
    //        String eventDescription) {
    for (Map<String, Object> result : results) {
      return new Event((Integer) result.get("NumberEnrolled"), (Integer) result.get("Cost"), (String) result.get("Place"), (String) result.get("StartDate"),
          (String) result.get("EndDate"), (String) result.get("EventDescription"));

    }

  }

}
