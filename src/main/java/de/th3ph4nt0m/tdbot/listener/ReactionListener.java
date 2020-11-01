/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 ReactionListener.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.listener;

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
                    && event.getReactionEmote().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.ruleReactionID"))) {
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