package com.lifeix.d20131019;

import java.util.concurrent.BlockingQueue;

/**
 * @DESCRIPTION:	
 * @DATE:2013-10-19 下午02:07:21
 **/
public class JFQ_Factory implements Runnable{
	private Object lock;
	private BlockingQueue<Integer> block;
	private int count = 19;
	public JFQ_Factory(Object lock, BlockingQueue<Integer> block, int count) {
		this.lock = lock;
		this.block = block;
		this.count = count;
	}
	
	public void run() {
		synchronized(lock) {
			int num = 1;
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(num > 19) {
					num = 1;
				}
				try {
					if(block.size() <= count) {
						System.out.println("\n\n================厨师开始制作蛋糕==========================\n\n");
						block.put(num);
						System.out.println("==============厨师已经做好了第" + num + "个蛋糕");
						num++;
						lock.notifyAll();
						
						//System.out.println("\n\n==============厨师已经做好了"+num+"个蛋糕,等待顾客来买蛋糕======================\n\n");
					} 
					if(block.size() > 0) {
						lock.wait();
					}
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		}
	}
	
}

