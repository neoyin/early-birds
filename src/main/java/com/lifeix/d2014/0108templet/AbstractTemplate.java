package com.lifeix.d2014.0108templet;

public abstract class AbstractTemplate {
	
	private void boil() {
		System.out.println("开始烧开水...");
	}
	
	private void pushCup() {
		System.out.println("倒入被子.....");
	}
	
	private void mixture(String mertial) {
		System.out.println("混合【" + mertial + "】和开水.....");
	}
	
	protected void coffeOrTea(String mertial) {
		boil();
		mixture(mertial);
		pushCup();
		System.out.println("请您慢用......总共100元...");
		System.out.println("--__--.............");
	}
	
}
