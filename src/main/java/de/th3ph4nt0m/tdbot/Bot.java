/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * Bot.java is part of the TD-Bot
 * Last edit: 2020.4.20
 */

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.listener.MessageReceive;
import de.th3ph4nt0m.tdbot.listener.VoiceConnect;
import de.th3ph4nt0m.tdbot.listener.VoiceLeave;
import de.th3ph4nt0m.tdbot.listener.VoiceMove;
import de.th3ph4nt0m.tdbot.utils.ChannelCreator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.VoiceChannel;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public
class Bot implements EventListener
{

    private JDA jda;
    private static Bot instance;
    private List <VoiceChannel> owChannels = new ArrayList <>();
    private ChannelCreator channelCreator;

    public
    Bot()
    {
        instance = this;
        try {
            this.jda = JDABuilder.createDefault("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpTosg.GGLbEsUH6YmwpSW676z6FEUVFxU").setAutoReconnect(true).setStatus(OnlineStatus.ONLINE).setActivity(Activity.watching("over TD-Nation")).build();
            this.channelCreator = new ChannelCreator();
            jda.addEventListener(new MessageReceive());
            jda.addEventListener(new VoiceConnect());
            jda.addEventListener(new VoiceLeave());
            jda.addEventListener(new VoiceMove());
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
    Bot getInstance()
    {
        return instance;
    }

    public
    List <VoiceChannel> getOwChannels()
    {
        return owChannels;
    }

    public
    ChannelCreator getChannelCreator()
    {
        return channelCreator;
    }
}
