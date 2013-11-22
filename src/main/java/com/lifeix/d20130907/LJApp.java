package com.lifeix.d20130907;

public class LJApp {

	public boolean isA;
	public boolean isB;
	public boolean isC;
	
	
	public Object [] LOCK;
	public int count;
	public int maxCount;

	public static void main(String[] args) {
		LJApp app =new LJApp();
		app.isA = true;
		app.isB = false;
		app.isC = false;
		app.LOCK = new Object[0];
		app.count = 1;
		app.maxCount = 75;
		new Thread(app.new ThreadA()).start();
		new Thread(app.new ThreadB()).start();
		new Thread(app.new ThreadC()).start();
	}

	public class ThreadA implements Runnable {
		@Override
		public void run() {
			while (count < maxCount)
			{
				synchronized (LOCK) {
					if (isA)// 轮到A线程打印了
					{
						for(int i=0;i<5;i++){
							System.out.println("线程1："+count);
							count ++;
						}
						System.out.println();
						isA = false;
						isB = true;
						isC = false;
						LOCK.notifyAll();// 唤醒所有线程
					} else {
						try {
							LOCK.wait();// 不是轮到我
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public class ThreadB implements Runnable {
		@Override
		public void run() {
			while (count < maxCount)
			{
				synchronized (LOCK) {
					if (isB)
					{
						for(int i=0;i<5;i++){
							System.out.println("线程2："+count);
							count ++;
						}
						System.out.println();
						isA = false;
						isB = false;
						isC = true;
						LOCK.notifyAll();
					} else {
						try {
							LOCK.wait();
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	public class ThreadC implements Runnable {
		@Override
		public void run() {
			while (count < maxCount)
			{
				synchronized (LOCK) {
					if (isC)
					{
						for(int i=0;i<5;i++){
							System.out.println("线程3："+count);
							count ++;
						}
						System.out.println();
						isA = true;
						isB = false;
						isC = false;
						LOCK.notifyAll();
					} else {
						try {
							LOCK.wait();
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

}
