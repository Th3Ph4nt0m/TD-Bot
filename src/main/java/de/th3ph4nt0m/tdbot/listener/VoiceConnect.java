/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceConnect.java is part of the TD-Bot
 * Last edit: 2020.6.2
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class VoiceConnect extends ListenerAdapter
{
    @Override public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        NationMember nMember = new NationMember(event.getMember());
        NationMember nMemberByID = new NationMember(event.getMember().getId());
        if (event.getChannelJoined().getId().equals("713791200490160209")) {
            if (nMemberByID.existsinDB()) {
                Bot.getInstance().getVoiceSystem().createVoiceChannel(nMember.getGame(), event.getGuild(), event.getMember());

            } else {
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendPrivacyNotAccepted(event.getMember().getUser().openPrivateChannel());
            }
        }
    }
}
