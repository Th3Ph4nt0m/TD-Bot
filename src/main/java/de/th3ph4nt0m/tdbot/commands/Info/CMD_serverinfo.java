/*******************************************************************************
 CMD_serverinfo.java is part of the TD-Bot project

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
package de.th3ph4nt0m.tdbot.commands.Info;

import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import de.th3ph4nt0m.tdbot.utils.MessageCenter.RoleInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

@CommandInfo(
		name="ServerInfo",
		invokes={"Info", "Serverinfo", "Server"},
		accessRank = DiscordRank.THE_NATION,
		description = "With ServerInfo you can get a some information about this discord server."
)
public class CMD_serverinfo implements ICommand {

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Guild guild = event.getGuild();

		int members = guild.getMemberCount();
		ArrayList<RoleInfo> roles = new ArrayList<>();

		for(Role role: guild.getRoles()) {
			roles.add(new RoleInfo(role.getName(),role.getColor(), guild.getMembersWithRoles(role).size()));
		}

		MessageCenter.getInstance().printServerInfo(members,roles,event.getChannel().getId());
	}

}
