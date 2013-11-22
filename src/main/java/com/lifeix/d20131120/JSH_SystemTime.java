package com.lifeix.d20131120;

import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class JSH_SystemTime implements SignalHandler{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JSH_SystemTime jsh = new JSH_SystemTime();
		Signal.handle(new Signal("TERM"), jsh);
		while(true) {
			Thread.sleep(1000);
			jsh.signalCallback();
		}
	}

	@Override
	public void handle(Signal arg0) {
		// TODO Auto-generated method stub
		System.out.println("kill readying...");
		signalCallback();
		System.exit(-1);
	}

	private void signalCallback() {  
		SimpleDateFormat sdataFormat = new SimpleDateFormat("hh:mm:ss");
		String date = sdataFormat.format(new Date());
		System.out.println(date);
    } 
}
