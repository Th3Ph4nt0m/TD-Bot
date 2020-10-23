/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * VoiceLeave.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceLeave extends ListenerAdapter
{

    @Override public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        if (Bot.getInstance().getVoiceSystem().voiceChannels.contains(event.getChannelLeft())) {
            if (event.getChannelLeft().getMembers().size() <= 0) {
                event.getChannelLeft().delete().queue();
                Bot.getInstance().getVoiceSystem().voiceChannels.remove(event.getChannelLeft());
            }
        }

    }
}
