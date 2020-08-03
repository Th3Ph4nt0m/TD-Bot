/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * CommandListener.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package de.th3ph4nt0m.tdbot.listener;

import de.th3ph4nt0m.tdbot.core.CommandHandler;
import de.th3ph4nt0m.tdbot.core.CommandParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public
class CommandListener extends ListenerAdapter
{
    public
    void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getContentRaw().startsWith("+") && !event.getMessage().getId().equals(event.getJDA().getSelfUser().getId())) {
            CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
        }
    }
}
