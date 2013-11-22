package com.lifeix.d20131019;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Zhch {

	public static void main(String[] args) {
		Thread cooker = new Thread(new Cooker());
		Thread customer = new Thread(new Customerzh());
		cooker.start();
		customer.start();
	}
}

class Cookie {

}

class Shelf {
	
	/**
	 * 最大数量
	 */
	private static int cap = 19;
	private static List<Cookie> cookies = new ArrayList<Cookie>();

	/**
	 * 放入一个蛋糕
	 * 
	 * @param c
	 */
	public static void addCookie(Cookie c) {
		cookies.add(c);
	}

	/**
	 * 卖出一个蛋糕
	 * 
	 * @return
	 */
	public static Cookie saleCookie() {
		if (cookies.isEmpty()) {
			return null;
		}
		Cookie cookie = cookies.remove(cookies.size() - 1);
		return cookie;
	}

	public static boolean isEmpty() {
		return cookies.isEmpty();
	}

	public static boolean isFull() {
		return cookies.size() >= cap;
	}

}

class Cooker implements Runnable {
	private static Random r = new Random();
	@Override
	public void run() {
		while (true) {
			Cookie c = new Cookie();
			Shelf.addCookie(c);
			System.out.println("做了一个蛋糕++++++++++++++++++++" + new Date());
			try {
				if (Shelf.isFull()) {
					System.out.println("架子满了,休息会再做...");
					Thread.sleep(2000);
				} else {
					Thread.sleep(200 + r.nextInt(200));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Customerzh implements Runnable {
	private static Random r = new Random();
	@Override
	public void run() {
		while (true) {
			Cookie c = Shelf.saleCookie();
			try {
				if (c == null) {
					System.out.println("没蛋糕了,休息会再吃");
					Thread.sleep(2000);
				} else {
					System.out.println("吃了一个蛋糕--------------------" + new Date());
					Thread.sleep(200 + r.nextInt(200));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}