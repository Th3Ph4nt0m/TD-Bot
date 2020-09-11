/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * MongoHandler.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
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
