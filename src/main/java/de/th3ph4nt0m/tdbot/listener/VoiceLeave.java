/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceLeave.java is part of the TD-Bot
 * Last edit: 2020.4.20
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceLeave extends ListenerAdapter
{

    @Override public
    void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (Bot.getInstance().getOwChannels().contains(event.getChannelLeft())) {
            if (event.getChannelLeft().getMembers().size() <= 0) {
                event.getChannelLeft().delete().queue();
                Bot.getInstance().getOwChannels().remove(event.getChannelLeft());
            }
        }
    }
}
