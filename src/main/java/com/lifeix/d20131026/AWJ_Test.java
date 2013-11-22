package com.lifeix.d20131026;

import java.util.Timer;

public class AWJ_Test {
	public static void main(String[] args) {
		AWJ_StaticPage staticPage =new AWJ_StaticPage();
		Timer timer = new Timer();
		timer.schedule(staticPage, 1000, 5*60*1000);
	}
}
