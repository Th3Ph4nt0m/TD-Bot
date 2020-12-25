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

import java.util.Timer;
import java.util.TimerTask;

public class LevelSystem
{
	public LevelSystem()
	{
		startTimer();
	}

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
		//TODO: Add System for ParticipationPoint addition and subtraction.
		System.out.println("minute passed");
	}
}
