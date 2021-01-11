/*******************************************************************************
 * Bot.java is part of the TD-Bot project
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

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.core.CommandHandler;
import de.th3ph4nt0m.tdbot.core.VoiceSystem;
import de.th3ph4nt0m.tdbot.event.*;
import de.th3ph4nt0m.tdbot.loader.GitHubLoader;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import de.th3ph4nt0m.tdbot.utils.Property;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.stream.Collectors;

public class Bot implements EventListener {

  private static Bot instance;
  private JDA jda;
  private VoiceSystem voiceSystem;
  private Property property;
  private transient GitHubLoader ghLoader;
  private transient CommandHandler commandHandler;

  public Bot() {
    instance = this;
    try {
      // JDA configuration
      this.property = new Property();
      property.setDefaultProps();
      this.jda =
          JDABuilder.createDefault(property.get("bot", "bot.token"))
              .setAutoReconnect(true)
              .setStatus(OnlineStatus.ONLINE)
              .setActivity(Activity.watching("over TD-Nation"))
              .enableIntents(Arrays.stream(GatewayIntent.values()).collect(Collectors.toList()))
              .enableCache(EnumSet.of(CacheFlag.ACTIVITY))
              .build();
      this.ghLoader = new GitHubLoader();
      this.voiceSystem = new VoiceSystem();
      this.commandHandler = new CommandHandler();
      jda.addEventListener(new VoiceConnect());
      jda.addEventListener(new VoiceLeave());
      jda.addEventListener(new VoiceMove());
      jda.addEventListener(new CommandListener());
      jda.addEventListener(new MessageReceive());
      jda.awaitReady();
      new MessageCenter(Boolean.parseBoolean(property.get("bot", "bot.autoprint")));
    } catch (LoginException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static Bot getInstance() {
    return instance;
  }

  public JDA getJda() {
    return jda;
  }

  public CommandHandler getCommandHandler() {
    return commandHandler;
  }

  public VoiceSystem getVoiceSystem() {
    return voiceSystem;
  }

  public GitHubLoader getGhLoader() {
    return ghLoader;
  }

  public Property getProperty() {
    return property;
  }
}
