package com.lifeix.d20130824;

public class JSH_Singleton_Five {
	/** 用于计数 */
	private static int count = 0;
	
	/**
	 * 私有的构造方法
	 */
	private JSH_Singleton_Five() {
		
	}
	/**
	 * 私有的静态实例引用
	 */
	private volatile static JSH_Singleton_Five singleton = null;
	/**
	 * 公有的静态方法得到唯一实例
	 * @return
	 */
	public static JSH_Singleton_Five getSingleton() {
		if(singleton == null) {
			synchronized (JSH_Singleton_Five.class) {
				if(singleton == null) {
					singleton = new JSH_Singleton_Five();
					count++;
				}
			}
		}
		return singleton;
	}
	public void say(){
		System.out.println("Hello,singleton five 实例数： "+count);
	}
}
