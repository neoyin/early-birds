package com.lifeix.d20131218annotation.jsh;

/**
 * use custom annotation MyTime
 *
 */
public class UseTime {
	
	@MyTime("useTime")
	public void useAnnotation() {
		System.out.println("this method use annotation MyTime");
	}
	
	public void needNotAnnotation() {
		System.out.println("this method do not use annotation MyTime");
	}
}
