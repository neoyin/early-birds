package com.lifeix.d20131120;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class LJApp {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();  
		Thread thread = new Thread(new ShutDownListener()); 
		runtime.addShutdownHook(thread);  
        process();  
		
	}
	
	private static void process(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Date date = new Date();
				System.out.println(format.format(date));
			}
		}, 0, 1000);
	}
}

class ShutDownListener implements Runnable  
{
	@Override
	public void run() {
		Date date = new Date();
		System.out.println("关闭时间："+LJApp.format.format(date));
	}  
}  
