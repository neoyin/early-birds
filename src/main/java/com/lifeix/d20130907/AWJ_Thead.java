package com.lifeix.d20130907;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AWJ_Thead{
	private static final int MAX_NUM = 75;
	private List<PrintTasks> tasks = new ArrayList<PrintTasks>();
	//建立线程池
	private ExecutorService esService = Executors.newCachedThreadPool();
	//计数器
	private CyclicBarrier barrier;
	public AWJ_Thead(int threadNum) {
		barrier = new CyclicBarrier(threadNum,new Runnable() {

			@Override
			public void run() {
				System.out.println("---------");
				for(PrintTasks task : tasks)
					if(task.getNum() >= MAX_NUM) {
						esService.shutdownNow();
						return;
					}else {
						task.print();
					}
			}
		});
		for(int i = 0; i < threadNum; i++) {
			PrintTasks task = new PrintTasks(barrier);
			tasks.add(task);
			esService.execute(task);
		}
	}
	
	public static void main(String[] args) {
		new AWJ_Thead(3);
	}
}

class PrintTasks implements Runnable{
	private static int counter = 1;
	private final int id = counter++;
	public static int num = 0;

	private CyclicBarrier barrier;//计数器
	public PrintTasks(CyclicBarrier b) {
		barrier = b;
	}

	@Override
	public void run() {
		try {
			//线程被中断
			while(!Thread.interrupted()) {
				//通知barrier已经完成   
				barrier.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public void print() {
		for(int i = 1; i <= 5; i++) {
			System.out.println("线程" + id + ":"+ ++num);
		}
		System.out.println();
	}
	
	public int getNum() {
		return num;
	}
}