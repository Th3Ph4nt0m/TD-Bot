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
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import org.bson.Document;

@SuppressWarnings ("SpellCheckingInspection") public class IUser
{
    private User user;
    private String id;
    private Member member;

    public IUser(String name, String discriminator)
    {
        this.user = Bot.getInstance().getJda().getUserByTag(name, discriminator);
        assert user != null;
        this.id = user.getId();
    }

    public IUser(Member member)
    {
        this.member = member;
    }

    private MongoCollection<Document> users()
    {
        return Bot.getInstance().getMongoHandler().users();
    }

    public Document getDocument()
    {
        return users().find(Filters.eq("_id", user.getId())).first();
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

    public String getNickname()
    {
        return user.getName();
    }

    public String asTag()
    {
        return user.getAsTag();
    }

    public boolean isBot()
    {
        return user.isBot();
    }

    public RestAction<PrivateChannel> openPrivateChannel()
    {
        return user.openPrivateChannel();
    }

    public User asUser()
    {
        return user;
    }
}
