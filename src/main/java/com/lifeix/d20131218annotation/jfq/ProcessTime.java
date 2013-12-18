package com.lifeix.d20131218annotation.jfq;

import java.lang.reflect.Method;

public class ProcessTime {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, ClassNotFoundException {
		UseTime ut = new UseTime();
		Method [] methods=Class.forName(ut.getClass().getName()).getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method.getName());
			MyTime mt=method.getAnnotation(MyTime.class);
			if(mt!=null){
				System.out.println("anation is " + method.getName()+" 方法......");
			}

		}
	}

}
