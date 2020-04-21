/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * CommandHandler.java is part of the TD-Bot
 * Last edit: 2020.4.22
 */

package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.interfaces.ICommand;

import java.util.HashMap;

public
class CommandHandler
{

    public static final CommandParser parse = new CommandParser();
    public static HashMap <String, ICommand> commands = new HashMap <>();

    public static
    void handleCommand(CommandParser.CommandContainer cmd)
    {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).excecuted(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).excecuted(safe, cmd.event);
            }
        }
    }
}
