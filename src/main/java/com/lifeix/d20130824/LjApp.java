package com.lifeix.d20130824;

public class LjApp {
	
	public static void main(String[] args){
		for(int i = 0;i<10;i++){
			Thread t = new Thread(new TestRun());
			t.start();
		}
	}
}
class TestRun implements Runnable{
	public void run(){
		GlanceA glanceA = GlanceA.getPool();	//线程不安全
		GlanceAB glanceAB = GlanceAB.getPool();	//线程安全
		GlanceAC glanceAC = GlanceAC.getPool();	//线程安全
	}
}
class GlanceA {
	private static GlanceA pool;
	private GlanceA(){}
	static int i = 0;
	public static GlanceA getPool(){
		if(pool == null){
			try {
				Thread.currentThread();
				Thread.sleep(1 * 1000);
			} 
			catch(InterruptedException e) {}
			pool = new GlanceA();
			i++;
		}
		System.out.println("glanceA实例数："+i);
		return pool;
		
	}
}

class GlanceAB {
	private static GlanceAB pool;
	private GlanceAB(){}
	static int i = 0;
	public static synchronized GlanceAB getPool(){
		if(pool == null){
			try {
				  Thread.currentThread();
				  Thread.sleep(1 * 1000);
			} 
			catch(InterruptedException e) {}
			pool = new GlanceAB();
			i++;
		}
		System.out.println("glanceAB实例数："+i);
		return pool;
	}
}

class GlanceAC {
	private volatile static GlanceAC pool;
	private GlanceAC(){};
	static int i = 0;
	public static GlanceAC getPool(){
		if(pool == null){
			try {
				  Thread.currentThread();
				  Thread.sleep(1 * 1000);
			} 
			catch(InterruptedException e) {}
			 synchronized (GlanceAC.class){
				 if(pool == null){
					 pool = new GlanceAC();
					 i++;
				 }
			 }
		}
		System.out.println("glanceAC实例数："+i);
		return pool;
	}
}

class GlanceB {
	private static GlanceB pool = new GlanceB();
	private GlanceB(){}
	public static GlanceB getPool(){
		return pool;
	}
}

class GlanceC{
	static int i = 0;
	private static class POOL{
		private static final GlanceC pool = new GlanceC();
	}
	private GlanceC(){}
	public static final GlanceC getPool(){
		return POOL.pool;
	}
}

enum GlanceD {
	POOL;
}

