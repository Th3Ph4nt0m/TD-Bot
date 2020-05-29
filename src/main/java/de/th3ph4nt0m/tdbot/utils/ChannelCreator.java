/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * ChannelCreator.java is part of the TD-Bot
 * Last edit: 2020.5.29
 */

package de.th3ph4nt0m.tdbot.utils;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public
class ChannelCreator
{
    public
    void createOWChannel(VoiceChannel channelJoined, Guild guild, Member member)
    {
        VoiceChannel joined = channelJoined;
        Guild g = guild;

        ChannelAction <VoiceChannel> temp = g.createVoiceChannel("»OW Comp").setParent(channelJoined.getManager().getChannel().getParent()).setPosition(joined.getPosition()).setUserlimit(6);
        VoiceChannel channel = temp.complete();
        Bot.getInstance().getOwChannels().add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();
        //1588111200000
    }
}
