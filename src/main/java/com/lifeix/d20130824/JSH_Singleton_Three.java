package com.lifeix.d20130824;

public class JSH_Singleton_Three {
	/** 用于计数 */
	private static int count = 0;
	
	/**
	 * 私有的构造方法
	 */
	private JSH_Singleton_Three() {
		
	}
	/**
	 * 私有的静态实例引用
	 * 完成初始化
	 */
	private static JSH_Singleton_Three singleton = new JSH_Singleton_Three();
	static {
		count++;
	}
	/**
	 * 公有的静态方法得到唯一实例
	 * @return
	 */
	public static JSH_Singleton_Three getSingleton() {
		return singleton;
	}
	public void say(){
		System.out.println("Hello,singleton three 实例数： "+count);
	}
}
