package com.lifeix.d20131109;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 蛋糕架
 * @author ANWJ
 *
 */
public class AWJ_CakeShelf {

	//private AWJ_Cake [] cakes = new AWJ_Cake[19];
	static BlockingQueue<AWJ_Cake> basket = new ArrayBlockingQueue<AWJ_Cake>(19);
	
	static{
		for (int i = 0; i < 19; i++) {
			try {
				basket.put(new AWJ_Cake(i, "蛋糕"+i, new Random().nextInt(100)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//private int count = 0;

	

	/*public synchronized void startConsumer(AWJ_Cake product) {
		
		while (basket.size() != 0) {
			notify();
			try {
				System.out.println("蛋糕架满了，现在开始消费......");
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cakes[count] = product;
		count++;
	}

	public synchronized AWJ_Cake startProduce() throws InterruptedException {
		AWJ_Cake cake = null;
		while (basket.size() == 0) {
			//notify();
			try {
				System.out.println("蛋糕架空了，正等待生产......");
			//	wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return basket.take();
		count--;
		cake = cakes[count];
		cakes[count] = null;
		return cake;
	}
*/


}
