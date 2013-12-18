package com.lifeix.d20131218lijiang;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTime {
	
	String value(); 
}
