package edu.byu.hbll.serviceTrip.database;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.byu.hbll.json.ObjectMapperFactory;
import edu.byu.hbll.json.UncheckedObjectMapper;
import edu.byu.hbll.serviceTrip.Configuration;
import edu.byu.hbll.serviceTrip.mockdata.Event;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@SuppressWarnings("CheckStyle")
public class ServiceTripDatabase
{

  @Inject
  Configuration configuration;

  MongoProvider mongoProvider;
  private MongoCollection<Document>eventsCollection;


  private static UncheckedObjectMapper mapper = ObjectMapperFactory.newUnchecked();

  @PostConstruct
  public void init(){
    mongoProvider = new MongoProvider();
    eventsCollection = mongoProvider.getEventInformationCollection();
  }

  public void insertEvent(Event event){
    Document document = Document.parse(mapper.writeValueAsString(event));
    eventsCollection.insertOne(document);
  }


/*
SELECT * FROM EventInformation i JOIN Media m ON (m.EventId = i.EventId) LEFT JOIN Tag t on (t.EventId = i.EventId) LEFT JOIN Specifications s ON (s.EventID = t.EventId)
 */
  public List<Event> selectEvent(String tag, String cost) throws Exception {
    BasicDBObject costFilter = new BasicDBObject("cost", "{$lt:"+ cost+ "}");
    FindIterable<Document> docs = eventsCollection.find(costFilter);
    ObjectMapper objectMapper = new ObjectMapper();
    List<Event>events = new ArrayList<>();

    for(Document doc: docs){
      JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
      String json = doc.toJson(writerSettings);

      Event event = objectMapper.readValue(json, Event.class);
      events.add(event);
    }
    return events;
  }

}
