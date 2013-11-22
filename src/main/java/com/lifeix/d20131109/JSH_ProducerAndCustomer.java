package com.lifeix.d20131109;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JSH_ProducerAndCustomer {

	private static BlockingQueue<String> iframe = new ArrayBlockingQueue<String>(19);
	static boolean isOver = false;
	static {
		for(int i = 0; i < 19; i++) {
			iframe.add("蛋糕：" + (i+1));
		}
		System.out.println("初始化放入：" + iframe.size() + "个蛋糕");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ExecutorService eService = Executors.newCachedThreadPool();
		Producer p = new Producer(iframe);
		Customer3 c = new Customer3(iframe);
//		eService.submit(p);
//		eService.submit(c);
		new Thread(p).start();
		new Thread(c).start();
		try {
			Thread.sleep(6000);
			isOver = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		eService.shutdown();
		System.out.println(iframe.size());
		for(String s : iframe) {
			System.out.println(s);
		}
		return;
	}

}

class Producer implements Runnable {

	private BlockingQueue<String> queue;
	private static int count = 20;
	
	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!JSH_ProducerAndCustomer.isOver) {
			try {
				Thread.sleep(300);
				System.out.println("准备放入：" + count);
				queue.put("蛋糕： " + count++);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class Customer3 implements Runnable {
	
	private BlockingQueue<String> queue;
	
	public Customer3(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!JSH_ProducerAndCustomer.isOver) {
			try {
				System.out.println("取出" + queue.take());
				System.out.println("取出" + queue.take());
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}