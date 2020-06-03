/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * MessageCenter.java is part of the TD-Bot
 * Last edit: 2020.6.3
 */

package de.th3ph4nt0m.tdbot.utils;

import de.th3ph4nt0m.tdbot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;

public class MessageCenter
{

    private static MessageCenter instance;

    public MessageCenter(boolean autoPrint)
    {
        instance = this;
        if (autoPrint) {
            autoPrint();
        }
    }

    public static MessageCenter getInstance()
    {
        return instance;
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
                "**Privacy Policy**\n [link following]\n ")
                .setFooter("TD-Bot ©Th3Ph4nt0m");
        assert channel != null;
        channel.sendMessage(builder.build()).queue(message -> message.addReaction("✅").queue());
    }

    private void autoPrint()
    {
        printRulesAndPrivacy("713698879283003462");
    }

    public void sendPrivacyNotAccepted(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Authentication Error!")
                .setColor(Color.RED)
                .setDescription("you need to accept our Privacy Policy to use this service\n")
                .setFooter("TD-Bot ©Th3Ph4nt0m");
        channel.complete().sendMessage(builder.build()).queue();
    }

    public void sendNoGame(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE)
                .setTitle("No game detected")
                .setDescription("I could not detect a game in your rich presence\n\nFor creating a ``»Talk``, please join the ``Voice × Creator``\nFor creating a ``competitive channel``, please enable rich presence in ``User settings --> Game Activity --> Display currently running game as a status message.`` and join ''Comp × Creator`` again.")
                .setFooter("TD-Bot ©Th3Ph4nt0m");
        channel.complete().sendMessage(builder.build()).queue();
    }
}
