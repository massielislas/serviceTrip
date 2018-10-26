package edu.byu.hbll.serviceTrip.api;

import edu.byu.hbll.serviceTrip.database.ServiceTripDatabase;
import edu.byu.hbll.serviceTrip.mockdata.Event;
import java.awt.PageAttributes.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import jdk.nashorn.internal.ir.ObjectNode;

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
  public Response getEvent()(
      @DefaultValue("") @QueryParam("tag") String tag,
      @DefaultValue("") @QueryParam("cost") String cost,{

        Event event = db.selectEvent(cost, tag);

        JsonNode response = mapper.convert(event, JsonNode.class);

    return Response.ok().entity(response).build();
  }
}
