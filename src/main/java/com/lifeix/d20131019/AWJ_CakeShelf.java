package com.lifeix.d20131019;

/**
 * 蛋糕架
 * @author ANWJ
 *
 */
public class AWJ_CakeShelf {

	private AWJ_Cake [] cakes = new AWJ_Cake[19];

	private int count = 0;

	public synchronized void product(AWJ_Cake cake){
		while(cakes.length == 0){

		}
	}

	public synchronized void startConsumer(AWJ_Cake product) {
		
		while (count == cakes.length) {
			notify();
			try {
				System.out.println("蛋糕架满了，现在开始消费......");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		cakes[count] = product;
		count++;
	}

	public synchronized AWJ_Cake startProduce() {
		AWJ_Cake cake = null;
		while (count == 0) {
			notify();
			try {
				System.out.println("蛋糕架空了，正等待生产......");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		cake = cakes[count];
		cakes[count] = null;
		return cake;
	}



}
