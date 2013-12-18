package com.lifeix.d20131218annotation.jfq;

public class UseTime {
 
	@MyTime(time = "jiang")
	public void time() {
		System.out.println("time");
	}
	
	public void noTime() {
		System.out.println("notime");
	}
	
}
