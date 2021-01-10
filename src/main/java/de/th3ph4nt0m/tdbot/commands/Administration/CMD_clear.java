/*******************************************************************************
 CMD_clear.java is part of the TD-Bot project

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

package de.th3ph4nt0m.tdbot.commands.Administration;

import de.th3ph4nt0m.tdbot.interfaces.CommandInfo;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.permission.DiscordRank;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.ArrayList;

@CommandInfo(
        name = "Clear",
        invokes = {"clear", "clearMessage", "clearMessages"},
        accessRank = DiscordRank.TEAM,
        description = "With Clear you can bulk delete messages in a channel.\nAdd a number after the command to specify the amount that has to be cleared."
)
public class CMD_clear implements ICommand {

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        List<Message> messages = new ArrayList<>();
        int i = Integer.parseInt(args[0]);
        for(Message message : event.getChannel().getIterableHistory().cache(false)){
            if(!message.isPinned()){
                messages.add(message);
            }
            if(--i <=0)break;
        }
        event.getChannel().purgeMessages(messages);
        MessageCenter.getInstance().printClear(event.getChannel().getId(),i);
    }

}
