package com.lifeix.d20131218lijiang;

import java.lang.reflect.Method;


public class ProcessTime {

	public static void main(String[] args) throws SecurityException, ClassNotFoundException{
		UserTime time = new UserTime();
		for(Method m : time.getClass().getMethods()){
	        if (m.isAnnotationPresent(MyTime.class)) {
	        	System.out.println(m +" 标注");
	        }else{
//	        	System.out.println(m +" 未标注");
	        }
		}
	}
}
