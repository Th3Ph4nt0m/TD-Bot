/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * MessageReceive.java is part of the TD-Bot
 * Last edit: 2020.4.16
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public
class MessageReceive extends ListenerAdapter
{
    @Override public
    void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (event.getChannel().getId().equals("654374520248991764")) {
            if (!event.getAuthor().getId().equals("699378436908777503")) {
                msg.delete().queue();
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(Color.RED).setTitle("Message deleted");
                builder.setDescription("Your message was deleted because this channel is only for system messages!");
                event.getChannel().sendMessage(builder.build()).queue();
            } else {
                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    @Override public
                    void run()
                    {
                        msg.delete().queue();
                    }
                }, 5000);
            }
        }
        if (msg.getContentRaw().startsWith("-") || msg.getAuthor().getId().equals("234395307759108106")) {
            {
                MessageChannel channel = event.getChannel();
                if (!channel.getId().equals("656220001123958802")) {
                    Message message = event.getMessage();
                    message.delete().queue();
                    MessageChannel groovy = Main.getInstance().getJda().getTextChannelById("656220001123958802");
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setColor(Color.RED).setTitle("Wrong Channel").setDescription("For groovy commands, please use this channel the next time!");
                    groovy.sendMessage(event.getAuthor().getAsMention()).queue();
                    groovy.sendMessage(builder.build()).queue();
//                        groovy.sendMessage(event.getAuthor().getAsMention() + " For groovy commands, please use this channel the next time!").queue();
                }
            }
        }
    }
}