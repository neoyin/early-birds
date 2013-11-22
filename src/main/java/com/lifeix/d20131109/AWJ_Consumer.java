package com.lifeix.d20131109;

/**
 * 消费者
 * @author ANWJ
 *
 */
public class AWJ_Consumer implements Runnable{

	private String consumerName;

	private AWJ_CakeShelf cakeShelf;

	int time = 0;

	int maxTime = 60000;

	public AWJ_Consumer(String consumerName, AWJ_CakeShelf cakeShelf){
		this.consumerName = consumerName;
		this.cakeShelf = cakeShelf;
	}

	private void consumeCake() throws InterruptedException{
		while(true){
			if(time >= maxTime){return;}
			if(cakeShelf.basket.size() > 0){
				//AWJ_Cake cake = cakeShelf.startProduce();
				System.out.println(consumerName+"买走了"+cakeShelf.basket.take());
			}else{
				System.out.println("蛋糕架空了，请等待生产......");
			}
			try {
				Thread.sleep(2000);
				time+=2000;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			consumeCake();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
