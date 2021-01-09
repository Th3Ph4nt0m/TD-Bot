/*******************************************************************************
 CMD_userinfo.java is part of the TD-Bot project

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

 Last edit: 2020/12/29
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

public class CMD_userinfo implements ICommand {
    CommandInfo commandInfo = new CommandInfo(
            "Info",
            new String[]{"Info"},
            DiscordRank.TEAM,
            "With UserInfo you can get the currently stored Information about the tagged member.\nA normal tag in the format @exampleUserName works just fine."
    );

    @Override
    public boolean unsafe(String[] args, MessageReceivedEvent event) {
        return !new NationMember(event.getMember()).getRank().isAtLeast(commandInfo.accessRank);
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        //initialising a NationMember to access the DB
        Member m = event.getMessage().getMentionedMembers().get(0);
        NationMember nationMember = new NationMember(m);
        //sending information to the channel
        event.getChannel().sendMessage("No Database").queue(); //TODO: send info about user instead of "No Database" as soon as db is implemented
    }

    @Override
    public CommandInfo getInfo() {
        return commandInfo;
    }

}
