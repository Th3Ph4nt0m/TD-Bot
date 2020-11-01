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
