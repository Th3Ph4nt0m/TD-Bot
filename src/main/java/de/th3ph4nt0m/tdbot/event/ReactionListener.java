/*******************************************************************************
 ReactionListener.java is part of the TD-Bot project

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

 Last edit: 2020/11/2
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.event;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ReactionListener extends ListenerAdapter
{
    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)
    {
        //listener for accepting rules and privacy by reaction
        if (!event.getUser().getId().equals(Bot.getInstance().getJda().getSelfUser().getId())) {
            if (event.getChannel().equals(Bot.getInstance().getJda().getTextChannelById(Bot.getInstance().getProperty().get("bot", "bot.rulesID")))
                    && event.getReactionEmote().getName().equals(Bot.getInstance().getProperty().get("bot", "bot.reactionEmojiName"))) {
                NationMember nationMember = new NationMember(event.getMember(), event.getMember().getId());
                if (!nationMember.existsinDB()) {
                    nationMember.createInDB();
                }
                event.getMember().getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(Bot.getInstance().getJda().getRoleById("721076810120429610"))).queue();
            }
        }
    }

    @Override
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event)
    {
        //listener for declining rules and privacy by reaction
        if (event.getChannel().equals(Bot.getInstance().getJda().getTextChannelById(Bot.getInstance().getProperty().get("bot", "bot.rulesID")))
                && event.getReactionEmote().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.ruleReactionID"))) {
            NationMember nationMember = new NationMember(event.getMember(), event.getUserId());
            if (nationMember.existsinDB()) {
                nationMember.removeFromDB();
            }
            event.getGuild().removeRoleFromMember(event.getUserId(), Objects.requireNonNull(event.getJDA().getRoleById("721076810120429610"))).queue();
        }
    }
}