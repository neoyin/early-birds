package com.lifeix.d20131109;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JFQ_CP {

	private static BlockingQueue<Integer> datas = new LinkedBlockingQueue<Integer>(19);
	static {
		for(int i = 1; i <= 19; i++) {
			try {
				datas.put(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int productTime = 30*1000;   //制作蛋糕时间
		final int customerTime = 20*1000;   //顾客蛋糕时间
		final int whichTime = 601;   //在哪一个时间点停止线程,以秒为单位
		final int customerNumTime = 1000*60;   //多长时间来顾客
		final int customerNum = 2;   //多长时间来几个顾客
		
		final Operate opt = new Operate(datas);
		
		new Thread(new Product(opt, productTime)).start();
		
		new Thread(){
			private boolean flag = false;
			public void run() {
				while(!flag && !opt.isFlag()) {
					for(int i = 0; i < customerNum; i++) {
						new Thread(new Customer2(opt, customerTime)).start();
					}
					try {
						Thread.sleep(customerNumTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		//计时器
		new Thread() {
			public void run() {
				int count = 0;
				while(count++ < whichTime) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(count == whichTime) {
						opt.setFlag(true);
					}
				}
				System.out.println(" 在"+whichTime+"妙后还剩:" + datas.size() + "个蛋糕");
			}
		}.start();
	}

}

class Product implements Runnable {
	boolean flag = false;
	private Operate opt;
	private static int count = 1;
	private int time;
	public Product(Operate opt, int time) {
		this.opt = opt;
		this.time = time;
	}
	
	public void run() {
		while(!flag && !opt.isFlag()) {
			opt.push(count++);
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Customer2 implements Runnable {
	private Operate opt;
	private int time;
	public Customer2(Operate opt, int time) {
		this.opt = opt;
		this.time =time;
	}
	
	public void run() {
		opt.pop();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Operate {
	
	private BlockingQueue<Integer> datas;
	public boolean flag = false;  //在指定时间设置为true，结束product和customer线程
	
	public boolean isFlag() {
		return flag;
	}

	public synchronized void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Operate(BlockingQueue<Integer> datas) {
		this.datas = datas;
	}
	
	public void push(int n) {
		try {
			datas.put(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("+++++cooker cooked a cake..... free " + datas.size() + " cakes");
	}
	
	public void pop() {
		if(datas.size() < 1) {
			System.out.println("there is not cake...... waiting for cooking ");
			return;
		}
		datas.poll();
		System.out.println("customer take one cake... free " + datas.size() + " cakes");
	}
}
