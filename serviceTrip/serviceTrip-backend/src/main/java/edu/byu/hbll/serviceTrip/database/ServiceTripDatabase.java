package edu.byu.hbll.serviceTrip.database;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.byu.hbll.json.ObjectMapperFactory;
import edu.byu.hbll.json.UncheckedObjectMapper;
import edu.byu.hbll.serviceTrip.Configuration;
import edu.byu.hbll.serviceTrip.mockdata.Event;
import javax.ejb.Singleton;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
@SuppressWarnings("CheckStyle")
public class ServiceTripDatabase
{

  @Inject
  Configuration configuration;

  private MongoCollection<Document>eventsCollection;

  private static UncheckedObjectMapper mapper = ObjectMapperFactory.newUnchecked();



  @PostConstruct
  public void init(){
    eventsCollection = configuration.getDb().getCollection("Events");
  }

  public void insertEvent(Event event){
    Document document = Document.parse(mapper.writeValueAsString(event));
    eventsCollection.insertOne(document);
  }


/*
SELECT * FROM EventInformation i JOIN Media m ON (m.EventId = i.EventId) LEFT JOIN Tag t on (t.EventId = i.EventId) LEFT JOIN Specifications s ON (s.EventID = t.EventId)
 */
  public List<JsonNode> selectEvent(String tag, String cost) throws Exception {
    BasicDBObject costFilter = new BasicDBObject("cost", new BasicDBObject("$lt", Integer.valueOf
        (cost)));
    FindIterable<Document> docs = configuration.getDb().getCollection("Events").find(costFilter);
    ObjectMapper objectMapper = new ObjectMapper();
    List<JsonNode> events = new ArrayList<>();
    for(Document doc: docs){
      doc.remove("_id");
      JsonWriterSettings writerSettings = new JsonWriterSettings(JsonMode.SHELL, true);
      String json = doc.toJson(writerSettings);

      JsonNode event = objectMapper.readTree(json);
      events.add(event);
    }
    return events;
  }

}
