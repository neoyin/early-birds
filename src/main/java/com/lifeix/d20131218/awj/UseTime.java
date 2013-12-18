package com.lifeix.d20131218.awj;

public class UseTime {
	
	@MyTime
	public void method1(){
		System.out.println("我被注解了");
	}
	
	public void method2(){
		System.out.println("我没被注解");
	}

}
