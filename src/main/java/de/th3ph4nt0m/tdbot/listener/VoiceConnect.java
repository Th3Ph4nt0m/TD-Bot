/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceConnect.java is part of the TD-Bot
 * Last edit: 2020.4.20
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceConnect extends ListenerAdapter
{
    @Override public
    void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        if (event.getChannelJoined().getId().equals("658042216937422868")) {
            //Overwatch creator
            Bot.getInstance().getChannelCreator().createOWChannel(event.getChannelJoined(), event.getGuild(), event.getMember());
        }
    }
}
