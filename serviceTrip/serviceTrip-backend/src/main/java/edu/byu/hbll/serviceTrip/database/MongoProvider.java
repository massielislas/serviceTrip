package edu.byu.hbll.serviceTrip.database;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClientURI;

import org.bson.Document;

public class MongoProvider {

    private MongoClient client;
    private MongoDatabase database;


    public MongoProvider() {
        client = new MongoClient(new MongoClientURI("mongodb://localhost"));
        database = client.getDatabase("serviceTrip");

    }

    public MongoCollection<Document> getEventInformationCollection() {
        return database.getCollection("Events");
    }
}