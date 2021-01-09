/*******************************************************************************
 CMD_version.java is part of the TD-Bot project

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

 Last edit: 2020/11/3
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.core.CommandHandler.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_version implements ICommand {
    CommandInfo commandInfo = new CommandInfo(
            "Version",
            new String[]{"Version", "BotVersion"},
            DiscordRank.THE_NATION,
            "Version gets you the current version of our bot.\nFeel free to checkout our repo as well."
    );

    @Override
    public boolean unsafe(String[] args, MessageReceivedEvent event) {
        return !new NationMember(event.getMember()).getRank().isAtLeast(commandInfo.accessRank);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MessageCenter.getInstance().printVersion(event.getChannel().getId());
    }

    @Override
    public CommandInfo getInfo() {
        return commandInfo;
    }

}
