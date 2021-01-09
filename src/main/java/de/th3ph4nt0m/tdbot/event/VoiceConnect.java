/*******************************************************************************
 VoiceConnect.java is part of the TD-Bot project

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

 Last edit: 2020/12/29
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.event;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public
class VoiceConnect extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        //initialize a NationMember to access the users document in DB
        NationMember nMember = new NationMember(event.getMember());
        //query for voice creator
        if (event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.createID"))) {
            if (nMember.getRank().isAtLeast(DiscordRank.THE_NATION)) {
                //check if user is playing a game
                if (nMember.getGame() != null) {
                    Bot.getInstance().getVoiceSystem().createVoiceChannel(nMember.getGame(), event.getGuild(), event.getMember(), event.getChannelJoined());
                } else {
                    Bot.getInstance().getVoiceSystem().createVoiceChannel("Talk", event.getGuild(), event.getMember(), event.getChannelJoined());
                }
            } else {
                //user is not registered
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendPrivacyNotAccepted(event.getMember().getUser().openPrivateChannel());
            }
            //query for competitive creator
        } else if (event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.compID"))) {

            if (nMember.getRank().isAtLeast(DiscordRank.THE_NATION)) {
                //check if user is playing a game
                if (nMember.getGame() != null) {
                    Bot.getInstance().getVoiceSystem().createCompChannel(nMember.getGame(), event.getGuild(), event.getMember(), event.getChannelJoined());
                } else {
                    //disconnect member because no game was detected
                    event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                    MessageCenter.getInstance().sendNoGame(event.getMember().getUser().openPrivateChannel());
                }
            } else {
                //user is not registered
                event.getMember().getGuild().kickVoiceMember(event.getMember()).queue();
                MessageCenter.getInstance().sendPrivacyNotAccepted(event.getMember().getUser().openPrivateChannel());
            }
        }
    }
}
