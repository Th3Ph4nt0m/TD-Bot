/*******************************************************************************
 * ICommand.java is part of the TD-Bot project
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
 * Last edit: 2020/11/2
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.interfaces;

import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
  /**
   * The unsafe-Method is called before the action-Method The default checks if all required
   * arguments are given and if the userrank is high enough
   *
   * @param args args from CommandParser
   * @param event MessageReceivedEvent form CommandParser
   * @return true when unsafe under current conditions
   */
  default boolean unsafe(String[] args, MessageReceivedEvent event) {
    if (args.length < getInfo().args().length) {
      MessageCenter.getInstance()
          .printMissingArgument(getInfo().args(), event.getChannel().getId());
      return true;
    }
    return !new NationMember(event.getMember()).getRank().isAtLeast(getInfo().accessRank());
  }

  /**
   * The action-Method is called after unsafe returned false
   *
   * @param args from CommandParser
   * @param event MessageReceivedEvent form CommandParser
   */
  void action(String[] args, MessageReceivedEvent event) throws InterruptedException;

  /**
   * Returns information about the command
   *
   * @return command information
   */
  default CommandInfo getInfo() {
    return getClass().getAnnotation(CommandInfo.class);
  }
}
