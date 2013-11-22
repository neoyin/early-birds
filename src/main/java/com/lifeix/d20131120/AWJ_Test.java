package com.lifeix.d20131120;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AWJ_Test extends Thread{

	public AWJ_Test() {

	}

	public void run() {

		
		while(true){
			try {
				System.out.println(dateFormat(new Date()));

				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String [] args){
		AWJ_Test test = new AWJ_Test();
		test.setDaemon(true);
		test.start();
		try {
			System.in.read();
			System.out.println("结束时间："+test.dateFormat(new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String dateFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
