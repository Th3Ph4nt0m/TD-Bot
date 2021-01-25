/*******************************************************************************
 * DiscordRank.java is part of the TD-Bot project
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

package de.th3ph4nt0m.tdbot.permission;

public enum DiscordRank {
  OP("Admin role", 645019781053022228L),
  BOT("Bot role",695789952839057490L),
  TEAM("Team role",747185518827012156L),
  VIP("VIP member",721076804210786366L),
  THE_NATION("verified member",721076810120429610L),
  UNVERIFIED("default member",432905467753136128L);

  private final String description;
  private final long ID;


  DiscordRank(String description, long ID) {
    this.description = description;
    this.ID=ID;
  }

  /**
   * find a rank by name
   *
   * @param name the rank to search for
   * @return rank || null
   */
  public static DiscordRank findRank(String name) {
    for (DiscordRank discordRank : values()) {
      if (discordRank.name().equalsIgnoreCase(name)) return discordRank;
    }
    return null;
  }

  /**
   * find a rank by id
   *
   * @param id the rank to search for
   * @return rank || null
   */
  public static DiscordRank findRank(long id) {
    for (DiscordRank discordRank : values()) {
      if (discordRank.ID==id) return discordRank;
    }
    return null;
  }

  public boolean isAtLeast(DiscordRank rank) {
    return this.ordinal() <= rank.ordinal();
  }

  public String getDescription() {
    return description;
  }
}
