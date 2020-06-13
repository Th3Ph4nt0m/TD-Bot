/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * Dominik_HGW.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package eu.lostname.tdbot.commands;

import eu.lostname.tdbot.Bot;
import eu.lostname.tdbot.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public
class Dominik_HGW implements ICommand
{
    @Override public boolean called(String[] args, MessageReceivedEvent event)
    {
        return false;
    }

    @Override public void action(String[] args, MessageReceivedEvent event)
    {
        TextChannel channel = Bot.getInstance().getJda().getTextChannelById("654376758249914387");
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.PINK);
        builder.setTitle("Alles gute zum Geburtstag!");
        builder.setDescription("Hey Dominik,\n\n" +
                "alles gute zum Geburtstag!\n\n" +
                "Noch viele viele Jahre mit:\n" +
                "1. Dem Bus Problem\n" + "2. Dem Bett Problem\n" + "3. Dem Ich-seh-nicht-was-ich-koche-Problem\n" + "4. Dem Warum-ist-alles-so-eng-im-Bad-Problem\n" + "5. Dem Flugzeugsitz-Problem\n" + "6. Dem Kleine-Menschen-mit-Regenschirmen-deren-Metallspitzen-immer-gefährlich-nah-an-den-Augen-vorbeisausen-Problem\n" + "Und natürlich dem Umarmen-fühlt-sich-komisch-an-Problem!");
        channel.sendMessage(builder.build()).queue();
    }

    @Override public void excecuted(boolean success, MessageReceivedEvent event)
    {

    }
}
