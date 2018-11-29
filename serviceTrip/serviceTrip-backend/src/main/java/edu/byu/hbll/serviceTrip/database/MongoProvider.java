package edu.byu.hbll.serviceTrip.database;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;

import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;

public class MongoProvider {

    private MongoClient client;
    private MongoDatabase database;

    private TableFiller tableFiller;


    public MongoProvider() {
        client = new MongoClient(new MongoClientURI("mongodb://localhost"));
        database = client.getDatabase("serviceTrip");
        database.createCollection("Events", new CreateCollectionOptions().capped(true));
        tableFiller = new TableFiller();
        tableFiller.fillTables();

    }

    public MongoCollection<Document> getEventInformationCollection() {
        return database.getCollection("Events");
    }

}