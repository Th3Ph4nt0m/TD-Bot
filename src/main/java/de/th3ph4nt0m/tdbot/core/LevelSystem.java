/*******************************************************************************
 LevelSystem.java is part of the TD-Bot project

 TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 Copyright (C) 2020 Henrik Steffens

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 Last edit: 2020/12/25
 ******************************************************************************/

package de.th3ph4nt0m.tdbot.core;

import de.th3ph4nt0m.tdbot.Bot;
import de.th3ph4nt0m.tdbot.interfaces.NationMember;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class LevelSystem
{
	public final float pointLimit = 69420;
	public final float upperBorderDiff = 2000;
	public final float lowerBorderDiff = -2000;

	public LevelSystem(){startTimer();}

	public void startTimer()
	{
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				minuteCall();
			}
		};
		timer.schedule(task,60000,60000);
	}

	public void minuteCall()
	{
		Bot.getInstance().getJda().getGuilds().get(0).getVoiceStates().forEach(guildVoiceState -> {
			VoiceChannel channel = Objects.requireNonNull(guildVoiceState.getChannel());
			if(channel.getMembers().size()==1){
				//(subtractRelPoints(guildVoiceState.getMember(),1);
			}
		});
		//TODO: Add System for ParticipationPoint addition and subtraction.
		System.out.println("minute passed");
	}

	public void addRelPoints(NationMember member,float points) {
		if (!member.existsinDB()) return;

		float pps =  Float.parseFloat(member.getDocument().getString("PPs"));
		pps += Math.abs(points) * (1 - (pps / (pointLimit + upperBorderDiff)));

		if (pps > pointLimit) pps = pointLimit;
		member.getDocument().put("PPs",pps);
	}

	public void subtractRelPoints(NationMember member,float points)
	{
		if (!member.existsinDB()) return;

		float pps =  Float.parseFloat(member.getDocument().getString("PPs"));
		pps -= Math.abs(points) * (-(1/(lowerBorderDiff-pointLimit))*pps+(pointLimit/(lowerBorderDiff-pointLimit))+1);
		if (pps < 0) pps = 0;

		member.getDocument().put("PPs",pps);
	}

	public void addPoints(NationMember member,float points)
	{
		if (!member.existsinDB()) return;

		float pps =  Float.parseFloat(member.getDocument().getString("PPs"));
		pps += Math.abs(points);
		if (pps > pointLimit) pps = pointLimit;

		member.getDocument().put("PPs",pps);
	}

	public void subtractPoints(NationMember member,float points)
	{
		if (!member.existsinDB()) return;

		float pps = Float.parseFloat(member.getDocument().getString("PPs"));
		pps -= Math.abs(points);
		if (pps < 0) pps = 0;

		member.getDocument().put("PPs",pps);
	}
}
