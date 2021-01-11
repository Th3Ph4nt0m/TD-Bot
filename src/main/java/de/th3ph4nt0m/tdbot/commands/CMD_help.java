/*******************************************************************************
 CMD_repo.java is part of the TD-Bot project

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

 Last edit: 2020/11/4
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import java.util.ArrayList;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


@CommandInfo(
        name = "Help",
        invokes = {"Help", "BotInfo", "CommandInfo"},
        accessRank = DiscordRank.THE_NATION,
        description = "Help show you all available commands for your rank"
)
public class CMD_help implements ICommand {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        ArrayList<CommandInfo> accessibleCommands = Bot.getInstance().getCommandHandler().listCommands();
        accessibleCommands.removeIf(commandInfo1 -> commandInfo1.name().equals(CMD_help.class.getAnnotation(CommandInfo.class).name()) || !new NationMember(event.getMember()).getRank().isAtLeast(commandInfo1.accessRank()));

        MessageCenter.getInstance().printHelp(event.getChannel().getId(), accessibleCommands);
    }

}


