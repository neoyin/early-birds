package com.lifeix.d20131019;

import java.util.Random;

/**
 * 生产者
 * @author ANWJ
 *
 */
public class AWJ_Producer implements Runnable{

	private String producerName = null;

	private AWJ_CakeShelf cakeShelf = null;

	public AWJ_Producer(String producerName, AWJ_CakeShelf cakeShelf){
		this.producerName = producerName;
		this.cakeShelf = cakeShelf;
	}

	private void produceCake(){
		int i = 0;
		while (true) {
			i++;
			AWJ_Cake cake = new AWJ_Cake(i,"蛋糕"+i , new Random().nextInt(100));
			cakeShelf.startConsumer(cake);
			System.out.println(producerName+"生产了蛋糕"+cake.getCakeId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		produceCake();

	}

}
