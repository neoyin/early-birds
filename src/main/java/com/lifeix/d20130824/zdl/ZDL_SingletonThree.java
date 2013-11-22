package com.lifeix.d20130824.zdl;

public class ZDL_SingletonThree {
	private static ZDL_SingletonThree singleton=new ZDL_SingletonThree();
	private ZDL_SingletonThree(){     

	}     
	public static ZDL_SingletonThree getInstance(){     
		return singleton;     
	}     
}