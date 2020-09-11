/*
 * Copyright (c) 2020 Henrik Steffens
 *
 * CMD_userinfo.java is part of a LostNameEU-System (TD-Bot)
 * You are not allowed to copy, change or reproduce without the permission of the LostNameEU-Management
 *
 * Last edit: 2020/8/21
 */

package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_userinfo implements ICommand {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String[] contentRaw = event.getMessage().getContentRaw().split(" ");
        Member m = event.getMessage().getMentionedMembers().get(0);
        NationMember nationMember = new NationMember(m, m.getId());
        if (event.getMember().getRoles().contains(Bot.getInstance().getJda().getRoleById(Bot.getInstance().getProperty().get("bot", "bot.highestRole"))) && event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.adminChannelID"))) {
            event.getChannel().sendMessage(nationMember.getInfo()).queue();
        } else {
            MessageCenter.getInstance().sendNoAccess(event.getChannel().getId());
        }
    }

    @Override
    public void excecuted(boolean success, MessageReceivedEvent event) {

    }
}
