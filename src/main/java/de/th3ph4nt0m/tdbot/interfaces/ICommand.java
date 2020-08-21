/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * ICommand.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
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
