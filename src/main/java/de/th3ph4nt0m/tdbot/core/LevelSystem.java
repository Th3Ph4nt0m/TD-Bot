package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import net.dv8tion.jda.api.entities.Member;

import java.util.HashMap;

public class LevelSystem {

    private HashMap joinTime;

    public LevelSystem()
    {
        joinTime = new HashMap<String, Long>();
    }
    public void join(Member member, Long time)
    {
        joinTime.put(member.getId(),time);
    }
    public void leave(Member member, Long time)
    {
        if(joinTime.containsKey(member.getId()))
        {
            long deltaTime;
            deltaTime = time - (Long)joinTime.get(member.getId());
            NationMember nationMember = new NationMember(member, member.getId());
            if(nationMember.existsinDB())
            {
                nationMember.addParticipationTime(deltaTime);
            }
        }
    }
}
