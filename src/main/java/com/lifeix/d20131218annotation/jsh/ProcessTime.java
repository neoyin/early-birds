package com.lifeix.d20131218annotation.jsh;

import java.lang.reflect.Method;

/**
 * parse custom annotation
 *
 */
public class ProcessTime {

	public static void main(String[] args) {
		UseTime useTime = new UseTime();
		System.out.println(useTime.getClass().getSimpleName() + " method info list:");
		for(Method method : useTime.getClass().getMethods()) {
			System.out.print(method.getName());
			if(method.isAnnotationPresent(MyTime.class)) {
				System.out.println(" is use annotation.");
				continue;
			}
			System.out.println();
		}
	}
}
