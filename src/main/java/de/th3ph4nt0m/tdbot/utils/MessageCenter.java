/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * MessageCenter.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/22
 */

package de.th3ph4nt0m.tdbot.utils;

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
                "**Rules**\n https://lostname.eu/public/partner/td/rules.html\n" +
                "\n" +
                "**Privacy Policy**\n https://lostname.eu/public/partner/td/privacy.html\n ")
                .setFooter("TD-Bot ©2020 LostNameEU");
        assert channel != null;
        channel.sendMessage(builder.build()).queue(message -> message.addReaction("✅").queue());
    }

    private void autoPrint()
    {
        printRulesAndPrivacy(Bot.getInstance().getProperty().get("bot", "bot.rulesID"));
    }

    public void sendPrivacyNotAccepted(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Authentication Error!")
                .setColor(Color.RED)
                .setDescription("you need to accept our Privacy Policy to use this service\n")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.complete().sendMessage(builder.build()).queue();
    }

    public void sendNoGame(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE)
                .setTitle("No game detected")
                .setDescription("I could not detect a game in your rich presence\n\nFor creating a ``»Talk``, please join the ``Voice × Creator``\nFor creating a ``competitive channel``, please enable rich presence in ``User settings --> Game Activity --> Display currently running game as a status message.`` and join ''Comp × Creator`` again.")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.complete().sendMessage(builder.build()).queue();
    }

    public void sendWrongGroovyChannel(String channelID) {
        TextChannel channel = Bot.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE)
                .setTitle("Wrong Groovy Channel")
                .setDescription("Please use the appropriate channel for Groovy commands \n\nTo keep our text channels clean we´d like you to use the ``\uD83C\uDFB5-groovy``Channel.\nAlso note that wer´e currently only able to provide one music bot, so if its in use it cannot be moved in your Channel.")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.sendMessage(builder.build()).queue();
    }

    public void sendNoAccess(String channelID) {
        TextChannel channel = Bot.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED)
                .setTitle("Limited Access")
                .setDescription("You need to own the OP role to execute this command. \n\nPlease note that this command can only be executed in admin channels")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.sendMessage(builder.build()).queue();

    }
}
