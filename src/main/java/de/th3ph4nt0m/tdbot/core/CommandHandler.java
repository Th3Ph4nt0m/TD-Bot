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
import de.th3ph4nt0m.tdbot.interfaces.ICommand;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public
class CommandHandler {
    public CommandHandler() {
        addCommands();
    }

    public HashMap<String, ICommand> commands = new HashMap<>();

    public void handleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            boolean unsafe = commands.get(cmd.invoke).unsafe(cmd.args, cmd.event);

            if (!unsafe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
            }
        }
    }


    public ArrayList<CommandInfo> listCommands() {
        ArrayList<CommandInfo> list = new ArrayList<>();
        for (ICommand command : commands.values()) {
            list.add(command.getInfo());
        }
        return list;
    }

    public void addCommand(ICommand command) {
        Bot.getInstance().getCommandHandler().commands.put(command.getInfo().name, command);
    }


    public static class CommandInfo {
        public final String name;
        public final boolean adminCommand;
        public final String description;

        public CommandInfo(String name, boolean adminCommand, String description) {
            this.name = name;
            this.adminCommand = adminCommand;
            this.description = description;
        }
    }


    public void addCommands() {
        String commandFolderName = "commands";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            ArrayList<File> list = new ArrayList<>();
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                    .getResources(commandFolderName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                File dir = new File(url.getFile());
                Collections.addAll(list, Objects.requireNonNull(dir.listFiles()));
            }
            for (File file : list) {
                if (!file.isFile() || !file.canExecute()) continue;

                String path = commandFolderName + "." + file.getName();
                path = path.replaceAll(".class", "");
                Class<?> command = classLoader.loadClass(path);
                if (!Arrays.stream(command.getInterfaces()).findFirst().isPresent()) continue;
                if (!Arrays.stream(command.getInterfaces()).findFirst().get().equals(ICommand.class)) continue;

                ICommand cmd = (ICommand) command.getDeclaredConstructor().newInstance();
                addCommand(cmd);
            }


        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
