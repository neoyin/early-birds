package com.lifeix.d20130824;

public class JSH_Singleton_Four {
	/** 用于计数 */
	private static int count = 0;
	
	/**
	 * 私有的构造方法
	 */
	private JSH_Singleton_Four() {
		
	}
	/**
	 * 内部类中静态实例
	 * 只有显示调用getSingleton()方法时才会加载Internal类得到实例
	 */
	private static class Internal {
		private static final JSH_Singleton_Four singleton = new JSH_Singleton_Four();
		static{
			count++;
		}
	}
	/**
	 * 公有的静态方法得到唯一实例
	 * @return
	 */
	public static JSH_Singleton_Four getSingleton() {
		return Internal.singleton;
	}
	public void say(){
		System.out.println("Hello,singleton four 实例数： "+count);
	}
}
