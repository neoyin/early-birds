package com.lifeix.d20131120;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test jsh = new Test();
		jsh.startServer();
	}
	
	private void startServer() {
		Runtime.getRuntime().addShutdownHook(new End());
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			signalCallback();
		}
	}
	
	public void signalCallback() {  
		SimpleDateFormat sdataFormat = new SimpleDateFormat("hh:mm:ss");
		String date = sdataFormat.format(new Date());
		System.out.println(date);
    } 
}
class End extends Thread {
	public void run() {
		Test jsh = new Test();
		System.out.println("kill readying...");
		jsh.signalCallback();
	}
}
