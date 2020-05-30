/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * ReactionListener.java is part of the TD-Bot
 * Last edit: 2020.5.31
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.IUser;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ReactionListener extends ListenerAdapter
{
    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event)
    {
        if (!event.getUser().getId().equals(Bot.getInstance().getJda().getSelfUser().getId())) {
            if (event.getChannel().equals(Bot.getInstance().getJda().getTextChannelById("713698879283003462"))) {
                IUser iUser = new IUser(event.getUser().getId());
                event.getMember().getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(Bot.getInstance().getJda().getRoleById("713424766052335678"))).queue();
                iUser.createInDB();
            }
        }
    }
}