/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceMove.java is part of the TD-Bot
 * Last edit: 2020.6.6
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings ("DuplicatedCode") public
class VoiceMove extends ListenerAdapter
{
    @Override public
    void onGuildVoiceMove(GuildVoiceMoveEvent event)
    {
        NationMember nMember = new NationMember(event.getMember(), event.getMember().getId());
        if (event.getChannelJoined().getId().equals("713791200490160209")) {
            if (nMember.existsinDB()) {
                if (nMember.getGame() != null) {
                    Bot.getInstance().getVoiceSystem().createVoiceChannel(nMember.getGame(), event.getGuild(), event.getMember(), event.getChannelJoined());
                } else {
                    Bot.getInstance().getVoiceSystem().createVoiceChannel("Talk", event.getGuild(), event.getMember(), event.getChannelJoined());
                }
            } else {
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendPrivacyNotAccepted(event.getMember().getUser().openPrivateChannel());
            }
        } else if (event.getChannelJoined().getId().equals("713424779734024233")) {
            if (nMember.getGame() != null) {
                Bot.getInstance().getVoiceSystem().createCompChannel(nMember.getGame(), event.getGuild(), event.getMember(), event.getChannelJoined());
            } else {
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendNoGame(event.getMember().getUser().openPrivateChannel());
            }
        }
    }

}
