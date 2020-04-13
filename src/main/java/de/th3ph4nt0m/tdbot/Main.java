package de.th3ph4nt0m.tdbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public
class Main
{

    public static JDA jda;

    public
    Main()
    {
        try {
            this.jda = jda = JDABuilder.createDefault("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpTosg.GGLbEsUH6YmwpSW676z6FEUVFxU").setAutoReconnect(true).setStatus(OnlineStatus.ONLINE).setActivity(Activity.watching("over TD-Nation")).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static
    void main(String[] args) throws LoginException, InterruptedException
    {
//        DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
//        builder.setToken("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpT4xg.hnWxGyfeACipKs-0D2bxJkDE4Fo");
//        builder.build();
//        JDABuilder builder = new JDABuilder(AccountType.BOT);
//        builder.setToken("Njk5Mzc4NDM2OTA4Nzc3NTAz.XpT4xg.hnWxGyfeACipKs-0D2bxJkDE4Fo");
//        builder.setActivity(Activity.watching("over TD-Nation"));
//        builder.setStatus(OnlineStatus.ONLINE);
//        builder.setAutoReconnect(true);
//        try {
//            builder.build();
//        } catch (LoginException e) {
//            e.printStackTrace();
//        }
    }


    public
    JDA getJda()
    {
        return jda;
    }
}
