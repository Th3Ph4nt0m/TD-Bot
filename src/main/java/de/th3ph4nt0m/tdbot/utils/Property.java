/*******************************************************************************
 * Property.java is part of the TD-Bot project
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

package de.th3ph4nt0m.tdbot.utils;

import java.io.*;
import java.util.Properties;

public class Property {

  /**
   * @param file name of the file
   * @param key the key to look for in the config
   * @return value of the given key
   */
  public String get(String file, String key) {
    try (InputStream input = new FileInputStream("cfg/" + file + ".properties")) {

      Properties prop = new Properties();

      // load a properties file from InputStream
      prop.load(input);

      return prop.getProperty(key);

      // Java 8 , print key and values
      //            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " +
      // value));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  /** create the default properties-file */
  public void setDefaultProps() {
    // create the file if not exists
    File dir = new File("cfg");
    if (!dir.exists()) {
      dir.mkdirs();
      /*try (OutputStream output = new FileOutputStream("cfg/database.properties")) {

          Properties prop = new Properties();

          // set the properties value
          prop.setProperty("db.port", "27017");
          prop.setProperty("db.host", "localhost");
          prop.setProperty("db.username", "root");
          prop.setProperty("db.password", "root");
          prop.setProperty("db.authDB", "admin");
          prop.setProperty("db.useDB", "root");

          // save properties to project folder
          prop.store(output, null);
      } catch (IOException e) {
          e.printStackTrace();
      }*/

      try (OutputStream output = new FileOutputStream("cfg/bot.properties")) {

        Properties prop = new Properties();

        // set the properties value
        prop.setProperty("bot.token", "token");
        prop.setProperty("bot.autoprint", "0");
        prop.setProperty("bot.rulesID", "RuleChannelID");
        prop.setProperty("bot.groovyChannelID", "GroovyChannelID");
        prop.setProperty("bot.afkID", "AFKChannelID");
        prop.setProperty("bot.createID", "CreateChannelID");
        prop.setProperty("bot.compID", "CompCreateChannelID");
        prop.setProperty("bot.adminChannelID", "AdminChannelID");

        // save properties to project folder
        prop.store(output, null);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
