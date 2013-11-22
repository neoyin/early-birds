package com.lifeix.d20130824;

public class JSH_Singleton_Two {
	/** 用于计数 */
	private static int count = 0;
	
	/**
	 * 私有的构造方法
	 */
	private JSH_Singleton_Two() {
		
	}
	/**
	 * 私有的静态实例引用
	 */
	private static JSH_Singleton_Two singleton = null;
	/**
	 * 非线程安全，较常用
	 * 公有的静态方法得到唯一实例
	 * @return
	 */
	public static JSH_Singleton_Two getSingleton() {
		if(singleton == null) {
			singleton = new JSH_Singleton_Two();
			count++;
		}
		return singleton;
	}
	public void say(){
		System.out.println("Hello,singleton two 实例数： "+count);
	}
}
