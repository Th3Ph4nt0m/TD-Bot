/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * VoiceSystem.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings ("DuplicatedCode")
public class VoiceSystem
{
    public final List<VoiceChannel> voiceChannels;
    public HashMap joinTime;

    public VoiceSystem()
    {
        this.voiceChannels = new ArrayList<>();
        joinTime = new HashMap<String, Long>();
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
        int limit = 0;
        //set user limit for specific games
        if (game.equalsIgnoreCase("Overwatch")) {
            limit = 6;
        } else if (game.equalsIgnoreCase("Valorant") || game.equalsIgnoreCase("Rainbow Six Siege") || game.equalsIgnoreCase("Counter-Strike: Global Offensive")) {
            limit = 5;
        }
        //create channel and register to the VoiceSystem
        VoiceChannel channel = guild.createVoiceChannel("» Comp × " + game).setParent(joined.getParent()).setPosition(voiceChannels.size() + 1).setUserlimit(limit).complete();
        voiceChannels.add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();

    }
}
