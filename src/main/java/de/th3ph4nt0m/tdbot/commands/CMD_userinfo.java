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

 Last edit: 2020/11/1
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_userinfo implements ICommand {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        //initialising a NationMember to access the DB
        Member m = event.getMessage().getMentionedMembers().get(0);
        NationMember nationMember = new NationMember(m, m.getId());
        //check if user is allowed to access the information
        if (event.getMember().getRoles().contains(Bot.getInstance().getJda().getRoleById(Bot.getInstance().getProperty().get("bot", "bot.highestRole"))) && event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.adminChannelID"))) {
            event.getChannel().sendMessage(nationMember.getInfo()).queue();
        } else {
            MessageCenter.getInstance().sendNoAccess(event.getChannel().getId());
        }
    }

    @Override
    public void excecuted(boolean success, MessageReceivedEvent event) {

    }
}
