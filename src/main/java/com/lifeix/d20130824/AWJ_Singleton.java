package com.lifeix.d20130824;


public class AWJ_Singleton {

	public static void main(String[] args) {

		for(int i=0; i<10; i++){
			AWJ_Singleton1 singleton1 = AWJ_Singleton1.getInstance();
			System.out.println(singleton1);
			AWJ_Singleton2 singleton2 = AWJ_Singleton2.getInstance();
			System.out.println(singleton2);
			AWJ_Singleton3 singleton3 = AWJ_Singleton3.getInstance();
			System.out.println(singleton3);
			AWJ_Singleton4 singleton4 = AWJ_Singleton4.getInstance();
			System.out.println(singleton4);
		}
	}


}
/**
 * 懒汉式  当对象第一次被引用时创建，比饿汉实例化晚一些因此节省资源，
 * 但是懒汉会引起线程不安全，需要枷锁，降低执行效率
 * @author ANWJ
 *
 */
class AWJ_Singleton1 {

	private static AWJ_Singleton1 singleton = null;
	//记录实例化次数
	private static int count = 0;
	private AWJ_Singleton1(){
		System.out.println("AWJ_Singleton1构造函数执行："+ ++count+"次");
	}
	public static AWJ_Singleton1 getInstance(){
		if(singleton == null){
			singleton = new AWJ_Singleton1();
		}
		return singleton;
	}

}



/**
 * 饿汉式  这种方式基于classloder机制避免了多线程的同步问题，饿汉单例类在类一加载时就会创建单例对象，
 * 并一直存在于内存当中占用了很多存储空间，浪费内存，优点是没有枷锁，执行效率会高一些
 * @author ANWJ
 *
 */
class AWJ_Singleton2{

	private static AWJ_Singleton2 instance = null;
	//记录实例化次数
	private static int count = 0;
	static {  
		instance = new AWJ_Singleton2();  
	}  

	private AWJ_Singleton2 (){
		System.out.println("AWJ_Singleton2构造函数执行："+ ++count+"次");
	}
	public static AWJ_Singleton2 getInstance() {
		return instance;
	}
}

/**
 * 静态内部类  这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，
 * Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，
 * 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
 * @author ANWJ
 *
 */
class AWJ_Singleton3{

	private static class SingletonHolder {

		private static final AWJ_Singleton3 INSTANCE = new AWJ_Singleton3();
	}
	//记录实例化次数
	private static int count = 0;

	private AWJ_Singleton3 (){
		System.out.println("AWJ_Singleton3构造函数执行："+ ++count+"次");
	}

	public static final AWJ_Singleton3 getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

/**
 * 
 * @author ANWJ
 *
 */
class AWJ_Singleton4 {
	private volatile static AWJ_Singleton4 singleton;
	//记录实例化次数
	private static int count = 0;

	private AWJ_Singleton4 (){
		System.out.println("AWJ_Singleton4构造函数执行："+ ++count+"次");
	}
	public static AWJ_Singleton4 getInstance() {
		if (singleton == null) {
			synchronized (AWJ_Singleton4.class) {
				if (singleton == null) {
					singleton = new AWJ_Singleton4();
				}
			}
		}
		return singleton;
	}
}


