package de.th3ph4nt0m.tdbot.listener;

/*
 * Copyright (c) 2020 Dominik Lenz aka TDDominik
 *
 * TextListener.java is part of the TD-Bot
 * Last edit: 2020.08.09
 */

import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public
class TextListener extends ListenerAdapter
{
    public
    void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getContentRaw().startsWith("+")||event.getMessage().getId().equals(event.getJDA().getSelfUser().getId())) return;

        String raw = event.getMessage().getContentRaw() ;
        String beheaded = raw.replaceFirst("\\+", "");
        String[] splitBeheaded = beheaded.split(" ");
        String invoke = splitBeheaded[0];
        ArrayList<String> split = new ArrayList <>();
        for (String s : splitBeheaded) {
            split.add(s);
        }
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        // usable: raw, beheaded, splitBeheaded, invoke, args

        if (event.getMessage().getContentRaw().startsWith("!") /*&& invoke.equals("play")*/ && !event.getChannel().getId().equals("721076843771330662")) {
            MessageCenter.getInstance().sendWrongGroovyChannel(event.getMember().getUser().openPrivateChannel());
            event.getMessage().delete();
        }
    }
}
