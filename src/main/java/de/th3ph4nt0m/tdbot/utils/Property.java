package de.th3ph4nt0m.tdbot.utils;

import java.io.*;
import java.util.Properties;

public class Property {

    public String get(String key) {
        try (InputStream input = new FileInputStream("database.properties")) {

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
        try (OutputStream output = new FileOutputStream("database.properties")) {

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
//            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
