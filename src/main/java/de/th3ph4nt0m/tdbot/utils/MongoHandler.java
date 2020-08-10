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
import de.th3ph4nt0m.tdbot.Bot;
import org.bson.Document;

public
class MongoHandler {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public MongoHandler() {
        this.mongoClient = new MongoClient(new ServerAddress(Bot.getInstance().getProperty().get("database", "db.host"), Integer.parseInt(Bot.getInstance().getProperty().get("database", "db.port"))), MongoCredential.createCredential(Bot.getInstance().getProperty().get("database", "db.username"), Bot.getInstance().getProperty().get("database", "db.authDB"), Bot.getInstance().getProperty().get("database", "db.password").toCharArray()), MongoClientOptions.builder().build());
        this.mongoDatabase = mongoClient.getDatabase(Bot.getInstance().getProperty().get("database", "db.useDB"));
    }

    public MongoCollection<Document> users() {
        return mongoDatabase.getCollection("users");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
