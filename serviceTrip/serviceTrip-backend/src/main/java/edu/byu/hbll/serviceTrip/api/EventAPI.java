package edu.byu.hbll.serviceTrip.api;

import edu.byu.hbll.serviceTrip.database.ServiceTripDatabase;
import edu.byu.hbll.serviceTrip.mockdata.Event;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("")
public class EventAPI {

  @Inject
  private ServiceTripDatabase db;

  private ObjectMapper mapper = new ObjectMapper();
  /*
    For now, we will allow the user to select an event based on a tag and a cost
   */
  @GET
  @Path("event")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEvent(
      @DefaultValue("") @QueryParam("tag") String tag,
      @DefaultValue("") @QueryParam("cost") String cost){

    List<Event> events = null;
    try {
      events = db.selectEvent(tag, cost);
    } catch (SQLException e) {
      e.printStackTrace();

      return Response.ok().entity("{\"error\":\"something went wrong\"}").build();
    }

    JsonNode response = mapper.valueToTree(events);
    System.out.println(response.toString());
    System.out.println(mapper.convertValue(events, JsonNode.class));


    return Response.ok().entity(response.toString()).build();
  }

  @GET
  @Path("addevent")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEvent(
      @DefaultValue("") @QueryParam("tag") String tag,
      @DefaultValue("") @QueryParam("cost") String cost,
      @DefaultValue("") @QueryParam("eventName") String eventName,
      @DefaultValue("") @QueryParam("organization") String organization,
      @DefaultValue("") @QueryParam("startDate") String startDate,
      @DefaultValue("") @QueryParam("endDate") String endDate,
      @DefaultValue("") @QueryParam("numEnrolled") String numEnrolled,
      @DefaultValue("") @QueryParam("place") String place,
      @DefaultValue("") @QueryParam("eventDescription") String eventDescription){

    UUID eventID = UUID.randomUUID();
    try {
      db.insertEventInformation(eventID.toString(),eventName, organization);
      db.insertMedia(eventID.toString(), eventDescription);
      db.insertSpecification(eventID.toString(), numEnrolled, cost, place,
          startDate, endDate);
      db.insertTag(eventID.toString(), tag);
    } catch (SQLException e) {
      return Response.ok().entity("{\"message\":\"Something went wrong\"}").build();
    }
    return Response.ok().entity("{\"message\":\"Adding Event "+eventID.toString() +  " was "
        + "Successful!\"}").build();
  }



}
