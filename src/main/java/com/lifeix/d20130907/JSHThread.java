package com.lifeix.d20130907;

public class JSHThread{
	
	private static int Num = 0;
	private static final int MAX_NUM = 75;
	private static int Tag = 1;
	private static Object lock = new Object(); 
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread thread_1 = new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				synchronized (lock) {
					while(Num < MAX_NUM) {
						if(Tag == 1) {
							for(int i = 0 ; i < 5 ; i++) {
								Num++;
								System.out.println("线程1 : " + Num );
							}
							System.out.println();
							Tag = 2;
							lock.notifyAll();
						}else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
		Thread thread_2 = new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				synchronized (lock) {
					while(Num < MAX_NUM) {
						if(Tag == 2) {
							for(int i = 0 ; i < 5 ; i++) {
								Num++;
								System.out.println("线程2 : " + Num );
							}
							System.out.println();
							Tag = 3;
							lock.notifyAll();
						}else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
		Thread thread_3 = new Thread() {
			public void run() {
				// TODO Auto-generated method stub
				synchronized (lock) {
					while(Num < MAX_NUM) {
						if(Tag == 3) {
							for(int i = 0 ; i < 5 ; i++) {
								Num++;
								System.out.println("线程3 : " + Num );
							}
							System.out.println();
							Tag = 1;
							lock.notifyAll();
						}else {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
		thread_1.start();
		thread_2.start();
		thread_3.start();
		
		
//		JSHThread thread = new JSHThread();
//		MoreThread_1 thread_1 = thread.new MoreThread_1();
//		MoreThread_2 thread_2 = thread.new MoreThread_2();
//		MoreThread_3 thread_3 = thread.new MoreThread_3();
//		for(int i = 0 ; i < 5 ;i++) {
//			Thread t = new Thread(thread_1);
//			t.start();
//			t = new Thread(thread_2);
//			t.start();
//			t = new Thread(thread_3);
//			t.start();
//		}
	}
	
	
//	private class MoreThread_1 implements Runnable {
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			for(int i = 0 ; i < 5 ; i++) {
//				synchronized (this) {
//					Num++;
//					System.out.println(Thread.currentThread().getName() + " : " + Num );
//				}
//			}
//			System.out.println();
//		}
//		
//	}
//	private class MoreThread_2 implements Runnable {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			for(int i = 0 ; i < 5 ; i++) {
//				synchronized (this) {
//					Num++;
//					System.out.println(Thread.currentThread().getName() + " : " + Num );
//				}
//			}
//			System.out.println();
//		}
//		
//	}
//	private class MoreThread_3 implements Runnable {
//		
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			for(int i = 0 ; i < 5 ; i++) {
//				synchronized (this) {
//					Num++;
//					System.out.println(Thread.currentThread().getName() + " : " + Num );
//				}
//			}
//			System.out.println();
//		}
//		
//	}
}
