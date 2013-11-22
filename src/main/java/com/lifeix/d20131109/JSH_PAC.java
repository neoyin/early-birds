package com.lifeix.d20131109;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JSH_PAC {

	private static BlockingQueue<String> iframe = new ArrayBlockingQueue<String>(19);
	static boolean isOver = false;
	static {
		for(int i = 0; i < 19; i++) {
			iframe.add("cake : " + (i+1));
		}
		System.out.println("The cake shelf is initialized, now cake number is ： " + iframe.size());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int count = 1;
			Produce p = new Produce(iframe);
			new Thread(p).start();
			do {
				if(count >= 10) {
					isOver = true;
					break;
				}
				System.out.println("The first " + count++ + " minutes");
				Custome c1 = new Custome(iframe);
				new Thread(c1).start();
				Custome c2 = new Custome(iframe);
				new Thread(c2).start();
				Thread.sleep(60);
			} while(true);
			System.out.println("Game is over , now cake number is : " + iframe.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
class Produce implements Runnable {

	private BlockingQueue<String> queue;
	private static int count = 20;
	
	public Produce(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		while(!JSH_PAC.isOver) {
			try {
				queue.put("cake : " + count++);
				System.out.println("Add cake : " + count);
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class Custome implements Runnable {

	private BlockingQueue<String> queue;
	
	public Custome(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("get " + queue.take());
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
