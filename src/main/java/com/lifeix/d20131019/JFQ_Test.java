package com.lifeix.d20131019;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @DESCRIPTION:	
 * @DATE:2013-10-19 下午02:21:39
 **/
public class JFQ_Test {

	/**@description:	
	 * @return:void
	 * @param args
	 */

	public static void main(String[] args) {
		Object lock = new Object();
		BlockingQueue<Integer> block = new LinkedBlockingQueue<Integer>();
		new Thread(new JFQ_Customer(lock, block)).start();
		new Thread(new JFQ_Factory(lock, block,19)).start();
	}

}

