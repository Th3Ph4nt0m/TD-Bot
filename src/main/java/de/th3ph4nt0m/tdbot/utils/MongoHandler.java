/*******************************************************************************
 MongoHandler.java is part of the TD-Bot project

 TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 Copyright (C) 2020 Henrik Steffens

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.utils;

import com.mongodb.*;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.utils.Subscribers.ObservableSubscriber;
import de.th3ph4nt0m.tdbot.utils.Subscribers.OperationSubscriber;
import org.bson.Document;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public
class MongoHandler
{
    private static MongoDatabase database;
    private static MongoHandler instance;

    public MongoHandler() {
        final String username = Bot.getInstance().getProperty().get("database", "db.username");
        final String pwd = URLEncoder.encode(Bot.getInstance().getProperty().get("database", "db.password"), StandardCharsets.UTF_8);
        final String host = Bot.getInstance().getProperty().get("database", "db.host");
        final String port = Bot.getInstance().getProperty().get("database", "db.port");
        final String auth = Bot.getInstance().getProperty().get("database", "db.authDB");

        ConnectionString connectionString = new ConnectionString("mongodb://"+username+":"+pwd+"@"+host+":"+port+"/?authSource="+auth+"&readPreference=primary&ssl=false");
        MongoClient mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(Bot.getInstance().getProperty().get("database", "db.useDB"));

        instance = this;
    }

    public static MongoHandler getInstance()
    {
        return instance;
    }

    public Document getDocumentFromUsersCollection(String fieldName, String value)
    {
        MongoCollection<Document> userCollection = database.getCollection("users");
        ObservableSubscriber<Document> subscriber;
        subscriber = new ObservableSubscriber<>();
        userCollection.find(eq(fieldName, value)).first().subscribe(subscriber);
        try {
            subscriber.await();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        List<Document> received = subscriber.getReceived();
        return received.get(0);
    }

    public void deleteDocumentFromUsersCollection(String fieldName, String value)
    {
        MongoCollection<Document> userCollection = database.getCollection("users");
        userCollection.deleteOne(eq(fieldName, value)).subscribe(new OperationSubscriber<>());
    }

    public void addDocumentToUsersCollection(Document doc)
    {
        MongoCollection<Document> userCollection = database.getCollection("users");
        userCollection.insertOne(doc).subscribe(new OperationSubscriber<>());
    }
}
