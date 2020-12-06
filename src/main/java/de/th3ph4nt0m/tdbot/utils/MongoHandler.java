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
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.th3ph4nt0m.tdbot.Bot;
import org.bson.Document;

import java.util.Arrays;

public
class MongoHandler {

    private final com.mongodb.client.MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    public MongoHandler() {
       // this.mongoDatabase = mongoClient.getDatabase(Bot.getInstance().getProperty().get("database", "db.useDB"));
        MongoCredential credential = MongoCredential.createCredential(Bot.getInstance().getProperty().get("database", "db.username"), Bot.getInstance().getProperty().get("database", "db.authDB"), Bot.getInstance().getProperty().get("database", "db.password").toCharArray());

      this.mongoClient =  MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress(Bot.getInstance().getProperty().get("database", "db.host"), Integer.parseInt(Bot.getInstance().getProperty().get("database", "db.port"))))))
                        .credential(credential)
                        .build()
        );
        this.mongoDatabase = mongoClient.getDatabase(Bot.getInstance().getProperty().get("database", "db.useDB"));
    }

    public MongoCollection<Document> users() {
        return mongoDatabase.getCollection("users");
    }

    public  com.mongodb.client.MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
