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

package de.th3ph4nt0m.tdbot.commands.info;

import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@CommandInfo(
        name = "UserInfo",
        invokes = {"Info", "Userinfo"},
        accessRank = DiscordRank.TEAM,
        description =  "With UserInfo you can get the currently stored Information about the tagged member.\nA normal tag in the format @exampleUserName works just fine.",
        args = {"@username"}
)
public class CMD_userinfo implements ICommand {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        //initialising a NationMember to access the DB
        Member m = event.getMessage().getMentionedMembers().get(0);
        NationMember nationMember = new NationMember(m);
        //sending information to the channel
        event.getChannel().sendMessage("No Database").queue(); //TODO: send info about user instead of "No Database" as soon as db is implemented
    }

}
