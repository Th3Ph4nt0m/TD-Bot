package de.th3ph4nt0m.tdbot.core;

import java.util.Timer;
import java.util.TimerTask;

public class LevelSystem
{
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
		System.out.println("minute passed");
	}
}
