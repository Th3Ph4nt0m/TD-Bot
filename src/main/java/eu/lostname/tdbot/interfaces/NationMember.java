/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * NationMember.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package eu.lostname.tdbot.interfaces;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import eu.lostname.tdbot.Bot;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

@SuppressWarnings ({"SpellCheckingInspection", "ConstantConditions"}) public class NationMember
{
    private final Member member;
    private final String id;


    public NationMember(Member member, String id)
    {
        this.member = member;
        this.id = id;
    }

    private MongoCollection<Document> users()
    {
        return Bot.getInstance().getMongoHandler().users();
    }

    public Document getDocument()
    {
        return users().find(Filters.eq("_id", id)).first();
    }


    public boolean existsinDB()
    {
        return getDocument() != null;
    }

    public void createInDB()
    {
        long l = 0;
        Document append = new Document("_id", member.getId()).append("nick", member.getEffectiveName()).append("timeConnected", l);
        users().insertOne(append);
    }

    public void updateOnlineTime(long toAdd)
    {
        Document doc = new Document();
        long saved = getDocument().getLong("timeConnected");
        doc.append("timeConnected", saved + toAdd);
    }

    public String getGame()
    {
        if (member.getActivities().size() >= 1) {
            return member.getActivities().get(0).getName();
        } else {
            return null;
        }
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
