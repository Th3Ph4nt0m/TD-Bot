/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceMove.java is part of the TD-Bot
 * Last edit: 2020.4.20
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceMove extends ListenerAdapter
{
    @Override public
    void onGuildVoiceMove(GuildVoiceMoveEvent event)
    {
        if (event.getChannelJoined().getId().equals("658042216937422868")) {
            Bot.getInstance().getChannelCreator().createOWChannel(event.getChannelJoined(), event.getGuild(), event.getMember());
        }
        if (Bot.getInstance().getOwChannels().contains(event.getChannelLeft())) {
            if (event.getChannelLeft().getMembers().size() <= 0) {
                event.getChannelLeft().delete().queue();
            }
        }
    }

}
