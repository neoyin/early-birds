package com.lifeix.d20130824.zdl;

public class ZDL_SingletonFour {
	private static ZDL_SingletonFour singleton;
	private ZDL_SingletonFour(){
		
	}
	public static ZDL_SingletonFour getInstance(){
		synchronized (ZDL_SingletonFour.class) {
			if(singleton==null){
				singleton = new ZDL_SingletonFour();
			}
		}
		return singleton;
	}
}