/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 VoiceSystem.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/24
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings ("DuplicatedCode")
public class VoiceSystem
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
