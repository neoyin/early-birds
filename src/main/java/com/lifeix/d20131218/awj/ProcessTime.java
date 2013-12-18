package com.lifeix.d20131218.awj;

import java.lang.reflect.Method;

public class ProcessTime {
	
	public static void main(String[] args) {
		Class<UseTime> c = UseTime.class;
		try {
			Method [] methods =c.getMethods();
			for(int i=0; i<methods.length; i++){
				Method method = methods[i];
				if(method.isAnnotationPresent(MyTime.class)){
					System.out.println(method.getName()+"()方法使用了注解@MyTime");
				}else{
					System.out.println(method.getName()+"()方法没有使用注解@MyTime");
				}
			}
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
