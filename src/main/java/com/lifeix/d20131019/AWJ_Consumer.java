package com.lifeix.d20131019;

/**
 * 消费者
 * @author ANWJ
 *
 */
public class AWJ_Consumer implements Runnable{

	private String consumerName;

	private AWJ_CakeShelf cakeShelf;

	public AWJ_Consumer(String consumerName, AWJ_CakeShelf cakeShelf){
		this.consumerName = consumerName;
		this.cakeShelf = cakeShelf;
	}

	private void consumeCake(){
		while(true){
			AWJ_Cake cake = cakeShelf.startProduce();
			System.out.println(consumerName+"买走了"+cake.getCakeId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		consumeCake();

	}

}
