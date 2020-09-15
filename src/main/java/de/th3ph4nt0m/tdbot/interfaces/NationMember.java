/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * NationMember.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.interfaces;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

@SuppressWarnings({"SpellCheckingInspection", "ConstantConditions"})
public class NationMember {
    private final Member member;
    private final String id;


    public NationMember(Member member, String id) {
        this.member = member;
        this.id = id;
    }

    private MongoCollection<Document> users() {
        return Bot.getInstance().getMongoHandler().users();
    }

    public Document getDocument() {
        return users().find(Filters.eq("_id", id)).first();
    }


    public boolean existsinDB() {
        return getDocument() != null;
    }

    public void createInDB() {
        long l = 0;
        Document append = new Document("_id", member.getId()).append("nick", getNickname()).append("participationTime",(long)0);
        users().insertOne(append);
    }

    public String getInfo() {

        return getDocument().toJson();
    }

    public String getGame() {
        if (member.getActivities().size() >= 1) {
            for (int i = 0; i < member.getActivities().size(); i++) {
                if (!member.getActivities().get(i).getType().equals(Activity.ActivityType.CUSTOM_STATUS)) {
                    return member.getActivities().get(i).getName();
                }
            }
        }
        return null;
    }

    public void addParticipationTime(long deltaTime) {
      long current = getDocument().getLong("participationTime");
      getDocument().put("participationTime",current + deltaTime);
    }

    public long getParticipationTime(){return getDocument().getLong("participationTime");}

    public void removeFromDB() {
        users().deleteOne(Filters.eq("_id", id));
    }

    public String getNickname() {
        return member.getEffectiveName();
    }

    public String asTag() {
        return member.getAsMention();
    }
}
