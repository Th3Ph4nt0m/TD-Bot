/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * ICommand.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package de.th3ph4nt0m.tdbot.interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public
interface ICommand
{
    boolean called(String[] args, MessageReceivedEvent event);

    void action(String[] args, MessageReceivedEvent event);

    void excecuted(boolean success, MessageReceivedEvent event);
}
