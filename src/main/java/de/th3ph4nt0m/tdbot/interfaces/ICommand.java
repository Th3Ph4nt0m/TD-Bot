/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 ICommand.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/24
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.interfaces;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public
interface ICommand
{
    boolean called(String[] args, MessageReceivedEvent event);

    void action(String[] args, MessageReceivedEvent event);

    void excecuted(boolean success, MessageReceivedEvent event);
}
