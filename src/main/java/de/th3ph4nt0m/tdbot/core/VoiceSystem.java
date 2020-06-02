/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * VoiceSystem.java is part of the TD-Bot
 * Last edit: 2020.6.2
 */

package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.List;

public class VoiceSystem
{
    public final List<VoiceChannel> voiceChannels;
    private final Category voice;

    public VoiceSystem()
    {
        this.voice = Bot.getInstance().getJda().getCategoryById("714207948427231253");
        this.voiceChannels = new ArrayList<>();
    }

    public void createVoiceChannel(String game, Guild guild, Member member)
    {
        ChannelAction<VoiceChannel> temp = guild.createVoiceChannel("»" + game).setParent(voice);
        VoiceChannel channel = temp.complete();
        voiceChannels.add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();
        try {
            System.out.println(channel.getId());
        } catch (Exception e) {
            System.out.println(channel.getId());
        }
    }
}
