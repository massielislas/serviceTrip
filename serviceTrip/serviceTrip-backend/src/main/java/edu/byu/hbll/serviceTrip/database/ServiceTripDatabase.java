package edu.byu.hbll.serviceTrip.database;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.byu.hbll.serviceTrip.Configuration;
import edu.byu.hbll.serviceTrip.mockdata.Event;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

@Stateless
@SuppressWarnings("CheckStyle")
public class ServiceTripDatabase
{

  @Inject
  Configuration configuration;

  BasicDataSource ds;
  QueryRunner runner;

  private ObjectMapper mapper = new ObjectMapper();

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

/*
SELECT * FROM EventInformation i JOIN Media m ON (m.EventId = i.EventId) LEFT JOIN Tag t on (t.EventId = i.EventId) LEFT JOIN Specifications s ON (s.EventID = t.EventId)
 */
  public List<Event> selectEvent(String tag, String cost) throws SQLException {
    String sql = "SELECT * FROM EventInformation i "
        + "JOIN Media m ON (m.EventId = i.EventId) "
        + "LEFT JOIN Tag t on (t.EventId = i.EventId) "
        + "LEFT JOIN Specifications s ON (s.EventID = t.EventId) "
        + "WHERE t.Tag=? AND s.Cost < ?";

    List<Map<String, Object>> results = runner.query(sql, new MapListHandler(), tag, cost);
    List<Event> events = new ArrayList();
    for (Map<String, Object> result : results) {
      events.add(new Event((Integer) result.get("NumberEnrolled"), (BigDecimal) result.get("Cost"),
          (String) result.get("Place"), result.get("StartDate").toString(),
          result.get("EndDate").toString(), (String) result.get("EventDescription"), (String) result.get
          ("Organization"), (String) result.get("EventName")));
    }

    return events;

  }

}
