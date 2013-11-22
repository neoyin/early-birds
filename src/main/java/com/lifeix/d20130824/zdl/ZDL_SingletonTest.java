package com.lifeix.d20130824.zdl;

public class ZDL_SingletonTest {
public static void main(String[] args) {
	System.out.println(ZDL_SingletonOne.getInstance());
	System.out.println(ZDL_SingletonOne.getInstance());
	System.out.println(ZDL_SingletonOne.getInstance());
	System.out.println(ZDL_SingletonTwo.getInstance());
	System.out.println(ZDL_SingletonTwo.getInstance());
	System.out.println(ZDL_SingletonTwo.getInstance());
	System.out.println(ZDL_SingletonFour.getInstance());
	System.out.println(ZDL_SingletonFour.getInstance());
	System.out.println(ZDL_SingletonFour.getInstance());
	System.out.println(ZDL_SingletonThree.getInstance());
	System.out.println(ZDL_SingletonThree.getInstance());
	System.out.println(ZDL_SingletonThree.getInstance());
}
}
