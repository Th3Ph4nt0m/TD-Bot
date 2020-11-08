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

import de.th3ph4nt0m.tdbot.interfaces.ICommand;

import java.util.HashMap;

public
class CommandHandler
{

    public HashMap <String, ICommand> commands = new HashMap <>();

    public void handleCommand(CommandParser.CommandContainer cmd)
    {
        if (commands.containsKey(cmd.invoke)) {
            boolean unsafe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!unsafe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
            }
        }
    }
}
