/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 VoiceConnect.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public
class VoiceConnect extends ListenerAdapter
{
    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        //initialize a NationMember to access the users document in DB
        NationMember nMember = new NationMember(event.getMember(), event.getMember().getId());
        //query for voice creator
        if (event.getChannelJoined().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.createID"))) {
            if (nMember.existsinDB()) {
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

            if (nMember.existsinDB()) {
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
