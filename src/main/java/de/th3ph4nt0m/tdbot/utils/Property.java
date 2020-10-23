/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * Property.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.utils;

import java.io.*;
import java.util.Properties;

public class Property {

    public String get(String file, String key) {
        try (InputStream input = new FileInputStream("cfg/" + file + ".properties")) {

            Properties prop = new Properties();

            // load a properties file from InputStream
            prop.load(input);

            return prop.getProperty(key);

            // Java 8 , print key and values
//            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setDefaultProps() {
        File dir = new File("cfg");
        if (!dir.exists()) {
            dir.mkdirs();
            try (OutputStream output = new FileOutputStream("cfg/database.properties")) {

                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("db.port", "27017");
                prop.setProperty("db.host", "localhost");
                prop.setProperty("db.username", "root");
                prop.setProperty("db.password", "root");
                prop.setProperty("db.authDB", "admin");
                prop.setProperty("db.useDB", "root");

                // save properties to project root folder
                prop.store(output, null);

                // Java 8 , print key and values
                //  prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (OutputStream output = new FileOutputStream("cfg/bot.properties")) {

                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("bot.token", "token");
                prop.setProperty("bot.autoprint", "0");
                prop.setProperty("bot.rulesID", "RuleChannelID");
                prop.setProperty("bot.groovyID","GroovyChannelID");
                prop.setProperty("bot.afkID","AFKChannelID");
                prop.setProperty("bot.createID","CreateChannelID");
                prop.setProperty("bot.compID","CompCreateChannelID");
                prop.setProperty("bot.highestRole","HighestRoleID");
                prop.setProperty("bot.adminChannelID","AdminChannelID");

                // save properties to project root folder
                prop.store(output, null);

                // Java 8 , print key and values
                // prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
