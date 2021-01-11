package de.th3ph4nt0m.tdbot.core;
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


import de.th3ph4nt0m.tdbot.commands.CMD_help;
import de.th3ph4nt0m.tdbot.commands.administration.CMD_clear;
import de.th3ph4nt0m.tdbot.commands.info.CMD_serverinfo;
import de.th3ph4nt0m.tdbot.commands.info.bot.CMD_repo;
import de.th3ph4nt0m.tdbot.commands.info.bot.CMD_version;
import de.th3ph4nt0m.tdbot.commands.utils.CMD_flipcoin;
import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;

import java.util.ArrayList;
import java.util.Arrays;

public
class CommandHandler {

	public ArrayList<ICommand> commands = new ArrayList<>();

	public CommandHandler() {
		addCommand(new CMD_help());
		addCommand(new CMD_flipcoin());
		addCommand(new CMD_repo());
		addCommand(new CMD_clear());
		addCommand(new CMD_serverinfo());
		//addCommand(new CMD_userinfo()); //TODO: register as soon as DB is implemented
		addCommand(new CMD_version());
	}

	/**
	 * Handels a command
	 *
	 * @param cmd CommandContainer from CommandParser
	 */
	public void handleCommand(CommandParser.CommandContainer cmd) {
		for (ICommand command : commands) {
			if (Arrays.stream(command.getInfo().invokes()).anyMatch(i -> i.equalsIgnoreCase(cmd.invoke))) {
				boolean unsafe = command.unsafe(cmd.args, cmd.event);

				if (!unsafe) {
					command.action(cmd.args, cmd.event);
				}
			}
		}
	}

	/**
	 * Adds a command to the current command list
	 *
	 * @param command ICommand to be added
	 */
	public void addCommand(ICommand command) {
		if (commands.contains(command)) return;
		commands.add(command);
	}

	/**
	 * Lists the CommandInfo of the current Command liSt
	 *
	 * @return List of CommandInfos
	 */
	public ArrayList<CommandInfo> listCommands() {
		ArrayList<CommandInfo> list = new ArrayList<>();
		commands.forEach(iCommand -> list.add(iCommand.getInfo()));
		return list;
	}

}
