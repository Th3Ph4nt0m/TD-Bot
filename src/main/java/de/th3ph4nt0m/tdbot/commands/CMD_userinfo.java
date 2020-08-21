package de.th3ph4nt0m.tdbot.commands;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.ICommand;
import de.th3ph4nt0m.tdbot.utils.MessageCenter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CMD_userinfo implements ICommand {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().getRoles().contains(Bot.getInstance().getJda().getRoleById(Bot.getInstance().getProperty().get("bot", "bot.highestRole"))) && event.getChannel().getId().equals(Bot.getInstance().getProperty().get("bot", "bot.adminChannelID"))) {
            //TODO return all stored information from DB via NationMember#getInfo()
        } else {
            MessageCenter.getInstance().sendNoAccess(event.getChannel().getId());
        }
    }

    @Override
    public void excecuted(boolean success, MessageReceivedEvent event) {

    }
}
