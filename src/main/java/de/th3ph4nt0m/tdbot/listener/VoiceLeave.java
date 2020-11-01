/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 VoiceLeave.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceLeave extends ListenerAdapter
{

    @Override public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        //delete custom channel as soon as empty
        if (Bot.getInstance().getVoiceSystem().voiceChannels.contains(event.getChannelLeft())) {
            if (event.getChannelLeft().getMembers().size() <= 0) {
                event.getChannelLeft().delete().queue();
                Bot.getInstance().getVoiceSystem().voiceChannels.remove(event.getChannelLeft());
            }
        }

    }
}
