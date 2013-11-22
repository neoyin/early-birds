package com.lifeix.d20131120;

import java.util.Date;

public class JFQ_Kill {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Thread(new JFQThread()).start();
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run() {
				System.out.println("killed at time " + new Date());
			}
		});
	}

}

class JFQThread implements Runnable {

	@Override
	public void run() {
		boolean flag = false;
		while(!flag) {
			System.out.println(new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}