/*******************************************************************************
 VoiceSystem.java is part of the TD-Bot project

 TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 Copyright (C) 2020 Henrik Steffens

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.core;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class VoiceSystem
{
    public final List<VoiceChannel> voiceChannels; //list of existing custom voice channels

    public VoiceSystem()
    {
        this.voiceChannels = new ArrayList<>();
    }

    /**
     * create a custom voice channel
     *
     * @param game   name of the user's current game
     * @param guild  Discord Guild to create channel
     * @param member user that creates the channel
     * @param joined channel the user connected to to get the category
     */
    public void createVoiceChannel(String game, Guild guild, Member member, VoiceChannel joined)
    {
        ChannelAction<VoiceChannel> temp = guild.createVoiceChannel("» " + game).setParent(joined.getParent()).setPosition(voiceChannels.size() + 1);
        VoiceChannel channel = temp.complete();
        voiceChannels.add(channel);
        member.getGuild().moveVoiceMember(member, channel).queue();
    }

    /**
     * create a custom competitive voice channel
     *
     * @param game   name of the user's current game
     * @param guild  Discord Guild to create channel
     * @param member user that creates the channel
     * @param joined channel the user connected to to get the category
     */
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
