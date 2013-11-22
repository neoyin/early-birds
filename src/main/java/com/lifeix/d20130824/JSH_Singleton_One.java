package com.lifeix.d20130824;

public class JSH_Singleton_One {
	/** 用于计数 */
	private static int count = 0;
	
	/**
	 * 私有的构造方法
	 */
	private JSH_Singleton_One() {
		
	}
	/**
	 * 私有的静态实例引用
	 */
	private static JSH_Singleton_One singleton = null;
	/**
	 * 公有的静态方法得到唯一实例
	 * @return
	 */
	public synchronized static JSH_Singleton_One getSingleton() {
		if(singleton == null) {
			singleton = new JSH_Singleton_One();
			count++;
		}
		return singleton;
	}
	public void say(){
		System.out.println("Hello,singleton one 实例数： "+count);
	}
}
