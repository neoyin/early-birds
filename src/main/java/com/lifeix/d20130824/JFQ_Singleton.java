package com.lifeix.d20130824;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-24 下午01:27:37
 **/
public class JFQ_Singleton {

	/**@description:	
	 * @return:void
	 * @param args
	 */

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			Singleton1 instance1 = Singleton1.getInstance();
			instance1.print();
		}
		for(int i = 0; i < 5; i++) {
			Singleton2 instance2 = Singleton2.getInstance();
			instance2.print();
		}
		
		for(int i = 0; i < 5; i++) {
			new Thread(new TestThread()).start();
		}
	}

}

class Singleton1 {
	private static Singleton1 instance = new Singleton1();
	private static int count = 1;
	private Singleton1() {}
	public static Singleton1 getInstance() {
		if(instance == null) {
			++count;
		}
		return instance;
	}
	public static void print() {
		System.out.println("Singleton1实例数量:" + count);
	}
}

class Singleton2 {
	static int count = 0;
	public static class SingletonHandler {
		static Singleton2 singleton2 = new Singleton2();
		static {
			++count;
		}
	}
	
	private Singleton2() {}
	public static Singleton2 getInstance() {
		return SingletonHandler.singleton2;
	}
	
	public static void print() {
		System.out.println("Singleton2的实例数量：" + count);
	}
}


class Singleton3 {
	private static Singleton3 instance = null;
	private static int count = 0;
	private Singleton3() {}
	public static synchronized Singleton3 getInstance() {
		if(instance == null) {
			++count;
			instance = new Singleton3();
		}
		return instance;
	}
	public static void print() {
		System.out.println(Thread.currentThread().getName() + " singleton3的实例数量：" + count);
	}
}

class Singleton4 {
	private static Singleton4 instance = null;
	private static int count = 0;
	private Singleton4() {}
	public static Singleton4 getInstance() {
		if(instance == null) {
			synchronized(Singleton4.class) {
				if(instance == null) {
					++count;
					instance = new Singleton4();
				}
			}
		}
		return instance;
	}
	public static void print() {
		System.out.println(Thread.currentThread().getName() + " singleton4的实例数量：" + count);
	}
}

class Singleton5 {
	
	private static int count = 0;
	private static Map<String,Singleton5> instances = Collections.synchronizedMap(new HashMap<String, Singleton5>(){{
		put(Singleton5.class.getName(), new Singleton5());
		++count;
	}});
	
	private Singleton5(){}
	
	public static Singleton5 getInstance() {
		if(!instances.containsKey(Singleton5.class.getName())) {
			Singleton5 instance = new Singleton5();
			instances.put(Singleton5.class.getName(), instance);
			++count;
			return instance;
		} else {
			return instances.get(Singleton5.class.getName());
		}
	}
	
	public static void print() {
		System.out.println(Thread.currentThread().getName() + " singleton5的实例数量：" + count);
	}
}

class TestThread implements Runnable {

	public void run() {
		Singleton3 instance3 = Singleton3.getInstance();
		instance3.print();
		Singleton4 instance4 = Singleton4.getInstance();
		instance4.print();
		Singleton5 instance5 = Singleton5.getInstance();
		instance5.print();
	}
	
}