package edu.byu.hbll.serviceTrip.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.byu.hbll.serviceTrip.database.ServiceTripDatabase;
import edu.byu.hbll.serviceTrip.mockdata.Event;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    List<JsonNode> events = null;
    try {
      events = db.selectEvent(tag, cost);
    } catch (Exception e) {
      e.printStackTrace();

      return Response.ok().entity("{\"error\":\"something went wrong\"}").build();
    }

    JsonNode response = mapper.valueToTree(events);
    System.out.println(response.toString());


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
    //Integer numberEnrolled, BigDecimal cost, String place, String startDate, String endDate, String eventDescription, String organization, String eventName,
    ArrayList tags = new ArrayList<String>();
    tags.add(tag);
    Integer numberEnrolled = Integer.valueOf(numEnrolled);
    Event event = new Event(numberEnrolled, new BigDecimal(cost), place, startDate, endDate, eventDescription, organization, eventName, tags);
    db.insertEvent(event);

    return Response.ok().entity("{\"message\":\"Adding Event "+eventID.toString() +  " was "
        + "Successful!\"}").build();
  }



}
