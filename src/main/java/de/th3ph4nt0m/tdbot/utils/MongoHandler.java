/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * MongoHandler.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package de.th3ph4nt0m.tdbot.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public
class MongoHandler
{

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public MongoHandler()
    {
        this.mongoClient = new MongoClient(new ServerAddress("lostname.eu", 27017), MongoCredential.createCredential("henrik", "admin", "3Q062&vIXAoX5ljm7DGE99dz^1jraaW0$e0nk#1RhI7qLy0ctG4NM#pf7rjpShLp".toCharArray()), MongoClientOptions.builder().build());
        this.mongoDatabase = mongoClient.getDatabase("Th3Ph4nt0m");
    }

    public MongoCollection<Document> users()
    {
        return mongoDatabase.getCollection("users");
    }

    public MongoClient getMongoClient()
    {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase()
    {
        return mongoDatabase;
    }
}
