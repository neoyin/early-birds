package com.lifeix.d20131109;


/**
 * 生产者消费者模式测试
 * @author ANWJ
 *
 */
public class AWJ_Test {

	public static void main(String[] args) {
		AWJ_CakeShelf cakeShelf = new AWJ_CakeShelf();
		System.out.println(cakeShelf.basket.size());
		AWJ_Producer producer = new AWJ_Producer("生产者", cakeShelf);
		AWJ_Consumer comsumer1 = new AWJ_Consumer("消费者1", cakeShelf);
		AWJ_Consumer comsumer2 = new AWJ_Consumer("消费者2", cakeShelf);
		Thread t1 = new Thread(producer);
		Thread c1 = new Thread(comsumer1);
		Thread c2 = new Thread(comsumer2);
		t1.start();
		c1.start();
		c2.start();
	}

}
