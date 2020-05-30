/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * MessageCenter.java is part of the TD-Bot
 * Last edit: 2020.5.30
 */

package de.th3ph4nt0m.tdbot.utils;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class MessageCenter
{
    public MessageCenter(boolean autoPrint)
    {
        if (autoPrint) {
//            autoPrint();
        }
    }

    public void printRulesAndPrivacy(String channelID)
    {
        TextChannel channel = Bot.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.YELLOW);
        builder.setTitle(":scroll: Rules - :lock: privacy");
        builder.setDescription("To use the services of the TD-Nation Discord server, you need to accept the rules and agree with our privacy policy.\n" +
                "\n" +
                "**Rules**\n[link following]\n" +
                "\n" +
                "**Privacy Policy**\n [link following]");
        channel.sendMessage(builder.build()).queue(message -> message.addReaction("✅").queue());
//        builder.build().addReaction("✅").queue();
    }

    private void autoPrint()
    {
        printRulesAndPrivacy("713698879283003462");
    }

}
