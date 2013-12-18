package com.lifeix.d20131218annotation.jfq;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyTime {
	String time();
}
