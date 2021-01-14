/*******************************************************************************
 * CMD_serverinfo.java is part of the TD-Bot project
 *
 * TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 * Copyright (C) 2020 Henrik Steffens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Last edit: 2020/12/29
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.commands.info;

import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.RoleInfo;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(
    name = "ServerInfo",
    invokes = {"Info", "Serverinfo", "Server"},
    accessRank = DiscordRank.THE_NATION,
    description = "With ServerInfo you can get a some information about this discord server.")
public class CMD_serverinfo implements ICommand {

  @Override
  public void action(String[] args, MessageReceivedEvent event) {
    Guild guild = event.getGuild();

    int memberCount = guild.getMemberCount();
    ArrayList<RoleInfo> roles = new ArrayList<>();
    Task<List<Member>> members = guild.loadMembers();
    members.onSuccess(members1 -> {
      for (Role role : guild.getRoles()) {
        if(role.isMentionable()||role.getName().equalsIgnoreCase("bot"))
        {
          roles.add(
                  new RoleInfo(role.getName(), role.getColor(),(int)members1.stream().filter(member -> member.getRoles().contains(role)).count()));
        }
      }
      MessageCenter.getInstance().printServerInfo(memberCount, roles, event.getChannel().getId());
    });
  }
}
