package com.lifeix.d20130824.zdl;

public class ZDL_SingletonTwo {

	private ZDL_SingletonTwo(){     

	}     

	public static class SingletonHoledr{     
		private static ZDL_SingletonTwo instance = new ZDL_SingletonTwo();     
	}     
	public static ZDL_SingletonTwo getInstance(){     
		return SingletonHoledr.instance;     
	}     
}