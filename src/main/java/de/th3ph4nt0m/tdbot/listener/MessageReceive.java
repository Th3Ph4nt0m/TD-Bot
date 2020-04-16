/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * $file.filename is part of the TD-Bot
 * Last edit: 2020.4.16
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Main;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public
class MessageReceive extends ListenerAdapter
{
    @Override public
    void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (msg.getContentRaw().startsWith("-") || msg.getAuthor().getId().equals("234395307759108106")) {
            {
                MessageChannel channel = event.getChannel();
                if (!channel.getId().equals("656220001123958802")) {
                    Message message = event.getMessage();
                    message.delete().queue();
                    MessageChannel groovy = Main.getInstance().getJda().getTextChannelById("656220001123958802");
                    groovy.sendMessage(event.getAuthor().getAsMention() + " For groovy commands, please use this channel the next time!").queue();
                }
            }
        }
    }
}