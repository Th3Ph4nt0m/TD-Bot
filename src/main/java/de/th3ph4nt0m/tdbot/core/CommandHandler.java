/*******************************************************************************
 CommandHandler.java is part of the TD-Bot project

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

 Last edit: 2020/11/2
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.commands.*;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;


import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public
class CommandHandler
{
    public CommandHandler()
    {
        addCommand(new CMD_flipcoin());
        addCommand(new CMD_repo());
        addCommand(new CMD_userinfo());
        addCommand(new CMD_version());
        addCommand(new CMD_help());
    }

    public HashMap <String, ICommand> commands = new HashMap <>();

    public void handleCommand(CommandParser.CommandContainer cmd)
    {
        if (commands.containsKey(cmd.invoke)) {
            boolean unsafe = commands.get(cmd.invoke).unsafe(cmd.args, cmd.event);

            if (!unsafe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
            }
        }
    }



    public ArrayList<CommandInfo> listCommands()
    {
        ArrayList<CommandInfo> list = new ArrayList<>();
        for(ICommand command:commands.values())
        {
            list.add(new CommandInfo(command.getInfo().name, command.getInfo().adminCommand, command.getInfo().description));
        }
        return list;
    }

    public void addCommand(ICommand command)
    {
        Bot.getInstance().getCommandHandler().commands.put(command.getInfo().name,command);
    }


    public static class CommandInfo
    {
        public final String name;
        public final boolean adminCommand;
        public final String description;
        public CommandInfo(String name, boolean adminCommand, String description)
        {
            this.name = name;
            this.adminCommand = adminCommand;
            this.description = description;
        }
    }
/*
    public void addCommands(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        File f = new File("/de/th3ph4nt0m/tdbot/commands");
        Path dir = f.toPath();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                // Load the target class using its binary name
                Class<?> loadedClass = classLoader.loadClass(file.toFile().getName());
                Class<?>[] interfaces = loadedClass.getInterfaces();

                for (Class<?> anInterface : interfaces) {
                    if(anInterface.getName().equals(ICommand.class.getName()))
                    {
                        Constructor<?> clsConst = loadedClass.getConstructor();
                        addCommand((ICommand) clsConst.newInstance());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
+/
 */
}
