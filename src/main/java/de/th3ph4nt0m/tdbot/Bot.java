/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 Bot.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/31
 ******************************************************************************/

package de.th3ph4nt0m.tdbot;

import de.th3ph4nt0m.tdbot.commands.CMD_userinfo;
import de.th3ph4nt0m.tdbot.core.CommandHandler;
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
class Bot implements EventListener
{

    private JDA jda;
    private static Bot instance;
    private MongoHandler mongoHandler;
    private VoiceSystem voiceSystem;
    private Property property;


    public Bot()
    {
        instance = this;
        try {
            //JDA configuration
            this.property = new Property();
            property.setDefaultProps();
            this.jda = JDABuilder.createDefault(property.get("bot", "bot.token"))
                    .setAutoReconnect(true)
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.watching("over TD-Nation"))
                    .enableIntents(Arrays.stream(GatewayIntent.values()).collect(Collectors.toList()))
                    .enableCache(EnumSet.of(CacheFlag.ACTIVITY))
                    .build();
            this.mongoHandler = new MongoHandler();
            jda.addEventListener(new VoiceConnect());
            jda.addEventListener(new VoiceLeave());
            jda.addEventListener(new VoiceMove());
            jda.addEventListener(new CommandListener());
            jda.addEventListener(new ReactionListener());
            this.voiceSystem = new VoiceSystem();
            jda.awaitReady();
            new MessageCenter(Boolean.parseBoolean(property.get("bot", "bot.autoprint")));
            CommandHandler.commands.put("info", new CMD_userinfo());
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Bot getInstance()
    {
        return instance;
    }

    public JDA getJda()
    {
        return jda;
    }

    public MongoHandler getMongoHandler()
    {
        return mongoHandler;
    }

    public VoiceSystem getVoiceSystem()
    {
        return voiceSystem;
    }

    public Property getProperty()
    {
        return property;
    }


}
