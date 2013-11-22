package com.lifeix.d20130907;

public class Zhch {
	private static int count;

	public void test() throws InterruptedException {
		CountThread c1 = new CountThread();
		CountThread c2 = new CountThread();
		CountThread c3 = new CountThread();
		Thread t1 = new Thread(c1, "线程1");
		Thread t2 = new Thread(c2, "线程2");
		Thread t3 = new Thread(c3, "线程3");
		c1.setNext(c2);
		c2.setNext(c3);
		c3.setNext(c1);
		t1.start();
		t2.start();
		t3.start();
		Thread.sleep(100);
		synchronized (c1) {
			c1.notify();
		}
	}

	synchronized static void addCount() {
		System.out.println(Thread.currentThread().getName() + ":" + (++count));
	}

	public static void main(String[] args) throws InterruptedException {
		Zhch t = new Zhch();
		t.test();
	}

	public static int getCount() {
		return count;
	}
}

class CountThread implements Runnable {
	private CountThread next;

	public void setNext(CountThread next) {
		this.next = next;
	}

	@Override
	public void run() {
		
		while (Zhch.getCount() < 75) {
			try {
				synchronized (this) {
					
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Zhch.getCount() < 75){
				for (int i = 0; i < 5; i++) {
					Zhch.addCount();
				}
				System.out.println("===================");
			}
			synchronized (next) {
				next.notify();
			}
		}
	}

}
