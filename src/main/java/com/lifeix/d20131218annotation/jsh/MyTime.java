package com.lifeix.d20131218annotation.jsh;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Definitions annotation MyTime
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTime {
	String value() default "MyTime";
}
