package com.lifeix.d20131109;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Zhch {
	static int shelfSize = 19;
	public static int unit = 10;
	public static boolean worldBreak;
	public static long startTime = 0;
	public static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(shelfSize);

	public static String time(){
		return " time:" + ((System.currentTimeMillis() - startTime)/unit);
	}
	
	public static String getCake() {
		return queue.poll();
	}
	
	public static void setCake(String cake) {
		try {
			queue.put(cake);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startTheWorld() {
		for (int i = 0; i < shelfSize; i++) {
			setCake("cake" + new Date());
		}
		startTime = System.currentTimeMillis();
		Thread cooker = new Thread(new Cooker());
		cooker.start();
		int minutes = 0;
		while (true) {
			try {
				Thread.sleep(60 * unit);
				minutes++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Thread(new Customer()).start();
			new Thread(new Customer()).start();
			System.out.println("第" + minutes + "分钟");
			if(minutes == 100){
				try {
					Thread.sleep(1 * unit);
					worldBreak = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("世界完蛋了, 还剩" + queue.size() + "个蛋糕");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Zhch t = new Zhch();
		t.startTheWorld();
	}
}

/**
 * 厨师
 *
 */
class Cooker implements Runnable {

	@Override
	public void run() {
		while (true) {
			if(Zhch.worldBreak){
				break;
			}
			try {
				Thread.sleep(30 * Zhch.unit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Zhch.setCake("cake" + new Date());
			System.out.println("做了一个蛋糕   ^_^  " + Zhch.time());
		}
	}

}

/**
 * 顾客
 *
 */
class Customer implements Runnable {
	String name;
	static int num = 0;

	public Customer() {
		this.name = "customer " + (++num);
	}

	public Customer(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("进店..  " + Zhch.time());
		String cake = null;
		do {
			if(Zhch.worldBreak){
				return;
			}
			try {
				Thread.sleep(20 * Zhch.unit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cake = Zhch.getCake();
			if (cake != null) {
				System.out.println(name + ": 吃了个蛋糕, 886  " + Zhch.time());
			} else {
				System.out.println(name + ": shit!!  " + Zhch.time());
			}
		} while (cake == null);

	}
}
