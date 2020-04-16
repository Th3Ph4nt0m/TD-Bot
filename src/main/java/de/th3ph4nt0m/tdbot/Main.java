/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * $file.filename is part of the TD-Bot
 * Last edit: 2020.4.16
 */

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.listener.MessageReceive;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.EventListener;

public
class Main implements EventListener
{

    private JDA jda;
    private static Main instance;
    Object obj = null;

    public
    Main()
    {
        instance = this;
        try {
            this.jda = JDABuilder.createDefault("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpTosg.GGLbEsUH6YmwpSW676z6FEUVFxU").setAutoReconnect(true).setStatus(OnlineStatus.ONLINE).setActivity(Activity.watching("over TD-Nation")).build();
            jda.addEventListener(new MessageReceive());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


    public
    JDA getJda()
    {
        return jda;
    }

    public static
    Main getInstance()
    {
        return instance;
    }
}
