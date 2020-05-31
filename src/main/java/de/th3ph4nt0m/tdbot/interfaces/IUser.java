/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * IUser.java is part of the TD-Bot
 * Last edit: 2020.5.31
 */

package de.th3ph4nt0m.tdbot.interfaces;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

@SuppressWarnings ("SpellCheckingInspection") public class IUser
{
    private Member member;
    private String id;

    public IUser(Member member)
    {
        this.member = member;
    }

    public IUser(String id)
    {
        this.id = id;
    }

    private MongoCollection<Document> users()
    {
        return Bot.getInstance().getMongoHandler().users();
    }

    public Document getDocument()
    {
        return users().find(Filters.eq("_id", member.getId())).first();
    }


    public boolean existsinDB()
    {
        return getDocument() != null;
    }

    public void createInDB()
    {
        Document append = new Document("_id", member.getId()).append("nick", member.getEffectiveName());
        users().insertOne(append);
    }

    public void removeFromDB()
    {
        users().deleteOne(Filters.eq("_id", id));
    }

    public String getNickname()
    {
        return member.getEffectiveName();
    }

    public String asTag()
    {
        return member.getAsMention();
    }
}
