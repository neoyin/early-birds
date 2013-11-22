package com.lifeix.d20131019;

import java.util.concurrent.BlockingQueue;

/**
 * @DESCRIPTION:	
 * @DATE:2013-10-19 下午02:07:32
 **/
public class JFQ_Customer implements Runnable{
	private Object lock;
	private BlockingQueue<Integer> block;
	public JFQ_Customer(Object lock, BlockingQueue<Integer> block) {
		this.lock = lock;
		this.block = block;
	}
	
	public void run() {
		synchronized(lock) {
			while(true) {
				if(block.isEmpty()) {
					System.out.println("\n....................顾客正在排队，等待买做好的蛋糕...........\n");
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("顾客买走了第" + block.poll() + "个蛋糕.......");
					if(block.isEmpty()) {
						System.out.println("\n...................蛋糕已经卖完，等待厨师做蛋糕..........\n");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock.notifyAll();
				}
			}
		}
	}
}

