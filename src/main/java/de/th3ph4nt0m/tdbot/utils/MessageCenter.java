/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 MessageCenter.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/11/1
 ******************************************************************************/

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

    /**
     * @param autoPrint variable to decide if messages are printed at startup
     */
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

    /**
     * create and send an embed message
     *
     * @param channelID ID of the channel to send the message to
     */
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

    /**
     * automatically send messages on startup
     */
    private void autoPrint()
    {
        printRulesAndPrivacy(Bot.getInstance().getProperty().get("bot", "bot.rulesID"));
    }

    /**
     * create and send an embed message
     *
     * @param channel private message Channel
     */
    public void sendPrivacyNotAccepted(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Authentication Error!")
                .setColor(Color.RED)
                .setDescription("you need to accept our Privacy Policy to use this service\n")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.complete().sendMessage(builder.build()).queue();
    }

    /**
     * create and send an embed message
     *
     * @param channel private message channel
     */
    public void sendNoGame(RestAction<PrivateChannel> channel)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE)
                .setTitle("No game detected")
                .setDescription("I could not detect a game in your rich presence\n\nFor creating a ``»Talk``, please join the ``Voice × Creator``\nFor creating a ``competitive channel``, please enable rich presence in ``User settings --> Game Activity --> Display currently running game as a status message.`` and join ''Comp × Creator`` again.")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.complete().sendMessage(builder.build()).queue();
    }

    /**
     * create and send an embed message
     *
     * @param channelID ID of the channel to send the message to
     */
    public void sendNoAccess(String channelID)
    {
        TextChannel channel = Bot.getInstance().getJda().getTextChannelById(channelID);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.RED)
                .setTitle("Limited Access")
                .setDescription("You need to own the OP role to execute this command. \n\nPlease note that this command can only be executed in admin channels")
                .setFooter("TD-Bot ©2020 LostNameEU");
        channel.sendMessage(builder.build()).queue();

    }
}
