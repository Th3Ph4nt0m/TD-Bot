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

import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class CMD_repo implements ICommand
{
    String description = "description";
    boolean adminCommand = false;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event)
    {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.BLUE);
        builder.setTitle("Repository Information");
        builder.setDescription("The TD-Bot is an opensource-project!\n\n**License:** GNU AFFERO GENERAL PUBLIC License v3\n\n\nFeel free to contribute!\n\n[TD-Bot on github](https://github.com/Th3Ph4nt0m/TD-Bot/)");
        builder.setFooter("TD-Bot ©2020 Th3Ph4nt0m");
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public boolean adminCommandOnly() {
        return this.adminCommand;
    }

    @Override
    public String description() {
        return this.description;
    }
}
