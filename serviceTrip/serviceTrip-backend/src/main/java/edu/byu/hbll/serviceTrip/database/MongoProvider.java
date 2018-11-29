package edu.byu.hbll.serviceTrip.database;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;

import com.mongodb.client.model.CreateCollectionOptions;
import edu.byu.hbll.serviceTrip.Configuration;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.POST;
import org.bson.Document;

@Singleton
@Startup
public class MongoProvider {

    private MongoClient client;
    private MongoDatabase database;

    @Inject
    Configuration configuration;

    @Inject
    TableFiller tableFiller;


    @PostConstruct
    public void init() {
//        client = new MongoClient(new MongoClientURI("mongodb://localhost"));
//        database = client.getDatabase("serviceTrip");
//        database.createCollection("Events", new CreateCollectionOptions().capped(false));
        database = configuration.getDb();
        tableFiller.fillTables();

    }

    public MongoCollection<Document> getEventInformationCollection() {
        return database.getCollection("Events");
    }

}