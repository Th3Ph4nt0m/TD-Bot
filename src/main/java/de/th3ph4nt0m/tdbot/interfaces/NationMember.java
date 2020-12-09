/*******************************************************************************
 NationMember.java is part of the TD-Bot project

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

 Last edit: 2020/12/9
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.interfaces;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
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

    /**
     * @return user specific document from DB
     */
    public Document getDocument() {
        try {
            return Bot.getInstance().getMongoHandler().getUserData("_id", id);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
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
        Bot.getInstance().getMongoHandler().addUserData(append);
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

    public DiscordRank getRank()
    {
        switch (member.getRoles().get(0).getName()) {
            case "OP":
                return DiscordRank.OP;
            case "Bot":
                return DiscordRank.BOT;
            case "Team":
                return DiscordRank.TEAM;
            case "VIP":
                return DiscordRank.VIP;
            case "The Nation":
                return DiscordRank.THE_NATION;
            default:
                return DiscordRank.UNVERIFIED;
        }
    }

    /**
     * delete the user specific document
     */
    public void removeFromDB()
    {
        Bot.getInstance().getMongoHandler().deleteUserData("_id", id);
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
