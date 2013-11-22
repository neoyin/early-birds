package com.lifeix.d20130824.zdl;

public class ZDL_SingletonOne {

	private static ZDL_SingletonOne singleton;
	private ZDL_SingletonOne(){
		
	}
	public static ZDL_SingletonOne getInstance(){
		if(singleton==null){
			singleton = new ZDL_SingletonOne();
		}		
		return singleton;
	}
	
}
