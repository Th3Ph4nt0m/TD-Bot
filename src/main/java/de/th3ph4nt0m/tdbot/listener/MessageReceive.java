/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 MessageReceive.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public
class MessageReceive extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        //delete commands for @Groovy in unsupported channels
        if (event.getMessage().getContentRaw().startsWith("-") && !event.getAuthor().getId().equals(Bot.getInstance().getJda().getSelfUser().getId())) {
            if (!event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.groovyChannelID"))) {
                msg.delete().queue();
            }
        }

    }
}