/*
 * Copyright (c) 2020 Henrik Steffens aka Th3Ph4nt0m
 *
 * CommandParser.java is part of the TD-Bot
 * Last edit: 2020.6.13
 */

package de.th3ph4nt0m.tdbot.core;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public
class CommandParser
{

    public static
    CommandContainer parser(String raw, MessageReceivedEvent event)
    {
        String beheaded = raw.replaceFirst("\\+", "");
        String[] splitBeheaded = beheaded.split(" ");
        String invoke = splitBeheaded[0];
        ArrayList <String> split = new ArrayList <>();
        for (String s : splitBeheaded) {
            split.add(s);
        }
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, event);
    }

    public static
    class CommandContainer
    {
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;


        public
        CommandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent event)
        {
            this.raw = raw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = event;
        }
    }
}
