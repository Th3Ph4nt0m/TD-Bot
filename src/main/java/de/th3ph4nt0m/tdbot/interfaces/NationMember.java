/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 NationMember.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.interfaces;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import org.bson.Document;

@SuppressWarnings({"SpellCheckingInspection", "ConstantConditions"})
public class NationMember
{
    private final Member member;
    private final String id;


    /**
     * @param member Discord Member to create a NationMember from
     * @param id     Discord Member/user ID to create a NationMember from because of NullPointerExceptions at Member#getID
     */
    public NationMember(Member member, String id)
    {
        this.member = member;
        this.id = id;
    }

    private MongoCollection<Document> users()
    {
        return Bot.getInstance().getMongoHandler().users();
    }

    /**
     * @return user specific document from DB
     */
    public Document getDocument()
    {
        return users().find(Filters.eq("_id", id)).first();
    }


    /**
     * @return if user exists in DB
     */
    public boolean existsinDB()
    {
        return getDocument() != null;
    }

    /**
     * insert user into DB
     */
    public void createInDB()
    {
        Document append = new Document("_id", member.getId()).append("nick", member.getEffectiveName());
        users().insertOne(append);
    }

    /**
     * @return all information stored about a user
     */
    public String getInfo()
    {

        return getDocument().toJson();
    }

    /**
     * @return name the user's current game
     */
    public String getGame()
    {
        if (member.getActivities().size() >= 1) {
            for (int i = 0; i < member.getActivities().size(); i++) {
                if (!member.getActivities().get(i).getType().equals(Activity.ActivityType.CUSTOM_STATUS)) {
                    return member.getActivities().get(i).getName();
                }
            }
        }
        return null;
    }

    /**
     * delete the user specific document
     */
    public void removeFromDB()
    {
        users().deleteOne(Filters.eq("_id", id));
    }

    /**
     * @return user's nickname
     */
    public String getNickname()
    {
        return member.getEffectiveName();
    }

    /**
     * @return user as mention
     */
    public String asTag()
    {
        return member.getAsMention();
    }
}
