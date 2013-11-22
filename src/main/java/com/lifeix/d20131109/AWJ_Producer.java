package com.lifeix.d20131109;

import java.util.Random;

/**
 * 生产者
 * @author ANWJ
 *
 */
public class AWJ_Producer implements Runnable{

	private String producerName = null;

	private AWJ_CakeShelf cakeShelf = null;
	
	int time = 0;
	
	int maxTime = 60000;

	public AWJ_Producer(String producerName, AWJ_CakeShelf cakeShelf){
		this.producerName = producerName;
		this.cakeShelf = cakeShelf;
	}

	private void produceCake() throws InterruptedException{
		int i = 0;
		while (true) {
			if(time >= maxTime){
				System.out.println("*************剩余蛋糕数量为："+cakeShelf.basket.size()+"***************");
				return;
			}
			if(cakeShelf.basket.size() < 19){
				i++;
				AWJ_Cake cake = new AWJ_Cake(i,"蛋糕"+i , new Random().nextInt(100));
				//cakeShelf.startConsumer(cake);
				System.out.println(producerName+"生产了蛋糕"+cake.getCakeId());
				cakeShelf.basket.put(cake);
			}else{
				System.out.println("蛋糕架满了，请稍事休息....");
			}
			try {
				Thread.sleep(3000);
				time+=3000;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			produceCake();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
