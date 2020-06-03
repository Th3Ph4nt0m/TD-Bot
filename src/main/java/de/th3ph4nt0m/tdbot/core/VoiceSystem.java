/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceSystem.java is part of the TD-Bot
 * Last edit: 2020.6.3
 */

package de.th3ph4nt0m.tdbot.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings ("DuplicatedCode") public class VoiceSystem
{
    public final List<VoiceChannel> voiceChannels;

    public VoiceSystem()
    {
        this.voiceChannels = new ArrayList<>();
    }

    public void createVoiceChannel(String game, Guild guild, Member member, VoiceChannel joined)
    {
        ChannelAction<VoiceChannel> temp = guild.createVoiceChannel("» " + game).setParent(joined.getParent()).setPosition(voiceChannels.size() + 1);
        VoiceChannel channel = temp.complete();
        voiceChannels.add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();
    }

    public void createCompChannel(String game, Guild guild, Member member, VoiceChannel joined)
    {
        ChannelAction<VoiceChannel> temp = guild.createVoiceChannel("» Comp × " + game).setParent(joined.getParent()).setPosition(voiceChannels.size() + 1);
        VoiceChannel channel = temp.complete();
        voiceChannels.add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();
    }
}
