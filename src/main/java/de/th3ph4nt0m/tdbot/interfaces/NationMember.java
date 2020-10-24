/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 NationMember.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/24
 ******************************************************************************/

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
        Document append = new Document("_id", member.getId()).append("nick", member.getEffectiveName());
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
