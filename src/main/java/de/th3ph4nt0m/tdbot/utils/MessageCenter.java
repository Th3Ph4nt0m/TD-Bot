/*******************************************************************************
 MessageCenter.java is part of the TD-Bot project

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

 Last edit: 2020/11/6
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.utils;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.RoleInfo;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MessageCenter {

	private static MessageCenter instance;

	/**
	 * @param autoPrint variable to decide if messages are printed at startup
	 */
	public MessageCenter(boolean autoPrint) {
		instance = this;
		if (autoPrint) {
			autoPrint();
		}
	}

	public static MessageCenter getInstance() {
		return instance;
	}

	/**
	 * create and send an embed message
	 *
	 * @param channelID ID of the channel to send the message to
	 */
	public void printRulesAndPrivacy(String channelID) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(channelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.YELLOW);
		builder.setTitle(":scroll: Rules - :lock: privacy");
		builder.setDescription("To use the services of the TD-Nation Discord server, you need to accept the rules and agree with our privacy policy.\n" +
				"\n" +
				"[RULES](https://www.lostname.eu/public/partner/td/rules.html/)" +
				"\n\n" +
				"[Privacy Policies](https://www.lostname.eu/public/partner/td/privacy.html/)")
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		assert channel != null;
		channel.sendMessage(builder.build()).queue(message -> message.editMessage(builder.build()).queue());
	}

	/**
	 * Shows the outcome of the coin toss command
	 *
	 * @param pChannelID ID of the channel to send the message to
	 * @param head       Outcome of the coin toss
	 */
	public void printCoinToss(String pChannelID, boolean head) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue);
		Emote emote;
		if (head) {
			emote = Bot.getInstance().getJda().getEmotesByName("CoinHeads", false).get(0);
			builder.setTitle("Head");
			builder.setDescription("You got Head.");
		} else {
			emote = Bot.getInstance().getJda().getEmotesByName("CoinTails", false).get(0);
			builder.setTitle("Tails");
			builder.setDescription("You got Tails.");
		}
		builder.setImage(emote.getImageUrl());
		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

	/**
	 * Prints the given commands as help
	 *
	 * @param pChannelID ID of the channel to send the message to
	 * @param commands   Commands to be listed in help
	 */
	public void printHelp(String pChannelID, ArrayList<CommandInfo> commands) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.pink);
		builder.setTitle("Help");
		builder.setDescription("Following commands are accessible to you.");
		for (CommandInfo info : commands) {
			builder.addField(info.name(), info.description(), false);
		}
		builder.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		assert channel != null;
		channel.sendMessage(builder.build()).queue();

	}

	/**
	 * create and send an embed message
	 *
	 * @param pErrorName  name of the error | embed title
	 * @param pEmbedColor color of the embed
	 * @param pChannelID  ID of the channel to send the message to
	 */
	public void printError(String pErrorName, Color pEmbedColor, String pChannelID) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(pEmbedColor);
		builder.setTitle(pErrorName);
		builder.setDescription("Your query could not be completed.\n\nPlease try again or [report a bug][https://github.com/Th3Ph4nt0m/TD-Bot/issues].")
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

	/**
	 * Prints the latest release from the github repo
	 *
	 * @param pChannelID ID of the channel to send the message to
	 */
	public void printVersion(String pChannelID) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue);
		builder.setDescription("My current version is ``" + Bot.getInstance().getGhLoader().getLatestTagName() + "``.");
		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

	/**
	 * automatically send messages on startup
	 */
	private void autoPrint() {
		printRulesAndPrivacy(Bot.getInstance().getProperty().get("bot", "bot.rulesID"));
	}

	/**
	 * create and send an embed message
	 *
	 * @param channel private message Channel
	 */
	public void sendPrivacyNotAccepted(RestAction<PrivateChannel> channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Authentication Error!")
				.setColor(Color.RED)
				.setDescription("Xou need to accept our Privacy Policy to use this service\n")
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		channel.complete().sendMessage(builder.build()).queue();
	}

	/**
	 * create and send an embed message
	 *
	 * @param channel private message channel
	 */
	public void sendNoGame(RestAction<PrivateChannel> channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.ORANGE)
				.setTitle("No game detected")
				.setDescription("I could not detect a game in your rich presence\n\nFor creating a ``»Talk``, please join the ``Voice × Creator``\nFor creating a ``competitive channel``, please enable rich presence in ``User settings --> Game Activity --> Display currently running game as a status message.`` and join ''Comp × Creator`` again.")
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		channel.complete().sendMessage(builder.build()).queue();
	}

	/**
	 * create and send an embed message
	 *
	 * @param pChannelID ID of the channel to send the message to
	 */
	public void sendNoAccess(String pChannelID, DiscordRank minRank) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.RED)
				.setTitle("Limited Access")
				.setDescription("You need to own the " + minRank.name() + " role to execute this command.")
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

	/**
	 * self deleting message after clear command
	 *
	 * @param pChannelID ID of the channel to send the message to
	 * @param messages   amount of deleted messages
	 */
	public void printClear(String pChannelID, int messages) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue);
		builder.setDescription("Deleted ``" + messages + "`` messages.");
		assert channel != null;
		channel.sendMessage(builder.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
	}

	/**
	 * prints the server information
	 *
	 * @param userCount  users on Server
	 * @param roleInfos  RoleInfo List with the current roles on the server
	 * @param pChannelID ID of the channel to send the message to
	 */
	public void printServerInfo(int userCount, ArrayList<RoleInfo> roleInfos, String pChannelID) {
		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue);

		//TODO: print serverinfo in a appealing design

		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

	/**
	 * prints error when command is missing arguments
	 *
	 * @param args       arguments that are missing
	 * @param pChannelID ID of the channel to send the message to
	 */
	public void printMissingArgument(String[] args, String pChannelID) {
		String arguments = "argument";
		if (args.length > 1) arguments = "arguments";

		TextChannel channel = Bot.getInstance().getJda().getTextChannelById(pChannelID);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.RED)
				.setTitle("Limited Access")
				.setDescription("This command needs the following " + arguments + ": " + Arrays.toString(args))
				.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
		assert channel != null;
		channel.sendMessage(builder.build()).queue();
	}

}