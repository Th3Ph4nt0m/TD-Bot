/*******************************************************************************
 Copyright (c) 2020 lostname.eu*

 CMD_userinfo.java is a part of the TD-Bot project.
 You are not allowed to copy, change or reproduce without the permission of lostname.eu.

 * lostname.eu is a project of Henrik Steffens. He owns all rights to "LostNameEU systems".

 Last edit: 2020/10/31
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_userinfo implements ICommand {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Role admin = event.getGuild().getRoles().stream().filter(role-> role.getPosition() == 1).findAny().orElse(null);
        Member m = event.getMessage().getMentionedMembers().get(0);
        NationMember nationMember = new NationMember(m, m.getId());
        if (event.getMember().getRoles().contains(admin) && event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.adminChannelID"))) {
            event.getChannel().sendMessage(nationMember.getInfo()).queue();

            MessageCenter.getInstance().sendNoAccess(event.getChannel().getId());
        }
    }

    @Override
    public void excecuted(boolean success, MessageReceivedEvent event) {

    }
}
