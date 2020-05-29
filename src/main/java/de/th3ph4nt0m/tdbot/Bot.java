/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * Bot.java is part of the TD-Bot
 * Last edit: 2020.5.29
 */

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.listener.*;
import de.th3ph4nt0m.tdbot.utils.ChannelCreator;
import de.th3ph4nt0m.tdbot.utils.MongoHandler;
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
    private MongoHandler mongoHandler;

    public
    Bot()
    {
        instance = this;
        try {
            this.jda = JDABuilder.createDefault("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpTosg.GGLbEsUH6YmwpSW676z6FEUVFxU").setAutoReconnect(true).setStatus(OnlineStatus.ONLINE).setActivity(Activity.watching("over TD-Nation")).build();
            this.channelCreator = new ChannelCreator();
            this.mongoHandler = new MongoHandler();
            jda.addEventListener(new MessageReceive());
            jda.addEventListener(new VoiceConnect());
            jda.addEventListener(new VoiceLeave());
            jda.addEventListener(new VoiceMove());
            jda.addEventListener(new CommandListener());
//            CommandHandler.commands.put("hgw", new Dominik_HGW());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


    public JDA getJda()
    {
        return jda;
    }

    public static Bot getInstance()
    {
        return instance;
    }

    public MongoHandler getMongoHandler()
    {
        return mongoHandler;
    }

    public List<VoiceChannel> getOwChannels()
    {
        return owChannels;
    }

    public ChannelCreator getChannelCreator()
    {
        return channelCreator;
    }

}
