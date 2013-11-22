package com.lifeix.d20130824;

public class Zhch {
	// --|||  copyed from http://cantellow.iteye.com/blog/838473#comments
}
// 1. 懒汉
class Single1 {
	private static Single1 instance;

	private Single1() {
	}

	public static synchronized Single1 getInstance() {
		if (instance == null) {
			instance = new Single1();
		}
		return instance;
	}
}

// 2. 饿汉
class Single2 {
	private static Single2 instance = new Single2();

	private Single2() {
	}

	public static Single2 getInstance() {
		return instance;
	}
}

// 3. holder
class Single3 {
	private static class Holder {
		public static Single3 instance = new Single3();
	}

	private Single3() {
	}

	public static Single3 getInstance() {
		return Holder.instance;
	}
}

// 4. 枚举
enum Single4 {
	instance;
	public void other() {
	}
}

// 5. 双重加锁检验
class Single5 {
	private static Single5 instance;

	private Single5() {
	}

	public static Single5 getInstance() {
		if (instance == null) {
			synchronized (Single5.class) {
				if (instance == null) {
					instance = new Single5();
				}
			}
		}
		return instance;
	}
}