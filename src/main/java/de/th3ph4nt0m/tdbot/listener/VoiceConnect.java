/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * VoiceConnect.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings ({"DuplicatedCode", "unchecked"}) public
class VoiceConnect extends ListenerAdapter
{
    @Override public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        NationMember nMember = new NationMember(event.getMember(), event.getMember().getId());
        if (event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.createID"))) {
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
        } else if (event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.compID"))) {
            if (nMember.getGame() != null) {
                Bot.getInstance().getVoiceSystem().createCompChannel(nMember.getGame(), event.getGuild(), event.getMember(), event.getChannelJoined());
            } else {
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendNoGame(event.getMember().getUser().openPrivateChannel());
            }
        } else if (!event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.afkID"))) {
            long current = System.currentTimeMillis();
            Bot.getInstance().getVoiceSystem().joinTime.put(event.getMember().getId(), current);
        }
    }
}
