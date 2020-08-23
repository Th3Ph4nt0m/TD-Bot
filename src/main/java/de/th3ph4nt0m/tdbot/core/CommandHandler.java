/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * CommandHandler.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.interfaces.ICommand;

import java.util.HashMap;

public
class CommandHandler
{

    //public static final CommandParser parse = new CommandParser();
    public static HashMap <String, ICommand> commands = new HashMap <>();

    public static
    void handleCommand(CommandParser.CommandContainer cmd)
    {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
            }
            commands.get(cmd.invoke).excecuted(safe, cmd.event);
        }
    }
}
