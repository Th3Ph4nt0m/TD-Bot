/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 MessageReceive.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/24
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;


public
class MessageReceive extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
       /*
       if (!msg.getAuthor().isBot()) {
            if (event.getChannel().getId().equals("721076834099265568")) {
                if (!event.getAuthor().getId().equals("699378436908777503")) {
                    msg.delete().queue();
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setColor(Color.RED).setTitle("Message deleted");
                    builder.setDescription("Your message was deleted because this channel is only for system messages!");
                    event.getChannel().sendMessage(builder.build()).queue();
                } else {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            msg.delete().queue();
                        }
                    }, 5000);
                }
            }
        }
        */

        if (!event.getMessage().getContentRaw().startsWith("+") && !event.getAuthor().getId().equals(Bot.getInstance().getJda().getSelfUser().getId())) {
            if (event.getMessage().getContentRaw().startsWith("-")) {
                if (!event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.groovyChannelID"))) {
                    MessageCenter.getInstance().sendWrongGroovyChannel(event.getChannel().getId());
                    event.getMessage().delete().queue();
                    if (event.getMessage().getContentRaw().contains("play") || event.getMessage().getContentRaw().contains("join")) {
                        event.getGuild().getMemberById(Bot.getInstance().getProperty().get("bot", "bot.groovyID"));
                    }
                }
            }
        }

 /*       if (msg.getContentRaw().startsWith("-") || msg.getAuthor().getId().equals("234395307759108106")) {
            Message message = event.getMessage();
            MessageChannel channel = event.getChannel();
            if (!channel.getId().equals("656220001123958802")) {
                if (!event.getAuthor().getId().equals("234395307759108106")) {
                    message.delete().queue();
                    MessageChannel groovy = Bot.getInstance().getJda().getTextChannelById("656220001123958802");
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setColor(Color.RED).setTitle("Wrong Channel").setDescription("For groovy commands, please use this channel the next time!");
                    groovy.sendMessage(event.getAuthor().getAsMention()).queue();
                    groovy.sendMessage(builder.build()).queue();
                } else {
                    message.delete().queue();
                }
            }
        }*/
    }
}