/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * Bot.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.core.VoiceSystem;
import de.th3ph4nt0m.tdbot.listener.*;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import de.th3ph4nt0m.tdbot.utils.MongoHandler;
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

public
class Bot implements EventListener {

    private JDA jda;
    private static Bot instance;
    private MongoHandler mongoHandler;
    private VoiceSystem voiceSystem;
    private Property property;

    public Bot() {
        instance = this;
        try {
            this.jda = JDABuilder.createDefault("NzM5NTQ4MTc4MTY0MDg4ODgz.XycD6Q.-F3V9SSornVzfTDm_aOx_UL_UOo")
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.watching("over TD-Nation"))
                    .enableIntents(Arrays.stream(GatewayIntent.values()).collect(Collectors.toList()))
                    .enableCache(EnumSet.of(CacheFlag.ACTIVITY))
                    .build();
            this.property = new Property();
            this.mongoHandler = new MongoHandler();
            jda.addEventListener(new MessageReceive());
            jda.addEventListener(new VoiceConnect());
            jda.addEventListener(new VoiceLeave());
            jda.addEventListener(new VoiceMove());
            jda.addEventListener(new CommandListener());
            jda.addEventListener(new TextListener());
            jda.addEventListener(new ReactionListener());
            this.voiceSystem = new VoiceSystem();
            jda.awaitReady();
            property.setDefaultProps();
            new MessageCenter(true);
//            CommandHandler.commands.put("hgw", new Dominik_HGW());
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

    public MongoHandler getMongoHandler() {
        return mongoHandler;
    }

    public VoiceSystem getVoiceSystem() {
        return voiceSystem;
    }

    public Property getProperty() {
        return property;
    }


}
