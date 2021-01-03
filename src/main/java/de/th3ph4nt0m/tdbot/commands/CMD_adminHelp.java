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
import de.th3ph4nt0m.tdbot.core.CommandHandler.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_adminHelp implements ICommand
{
    CommandInfo commandInfo = new CommandInfo(
            "Help",
            "Help,BotInfo,CommandInfo,Command",
            true,
            "Help show you all available commands for your rank"
    );

    @Override
    public boolean unsafe(String[] args, MessageReceivedEvent event) {
        Member author = event.getMember();
        assert author != null;
        NationMember authorMember = new NationMember(author, author.getId());
        return !authorMember.getRank().isAtLeast(DiscordRank.OP);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MessageCenter.getInstance().printHelp(event.getChannel().getId(),Bot.getInstance().getCommandHandler().listCommands());
    }

    @Override
    public CommandInfo getInfo() { return commandInfo; }
}


