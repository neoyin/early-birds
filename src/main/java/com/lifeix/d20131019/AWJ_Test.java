package com.lifeix.d20131019;

/**
 * 生产者消费者模式测试
 * @author ANWJ
 *
 */
public class AWJ_Test {

	public static void main(String[] args) {
		AWJ_CakeShelf cakeShelf = new AWJ_CakeShelf();
		AWJ_Producer producer = new AWJ_Producer("生产者", cakeShelf);
		AWJ_Consumer comsumer = new AWJ_Consumer("消费者", cakeShelf);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(comsumer);
		t1.start();
		t2.start();
	}

}
