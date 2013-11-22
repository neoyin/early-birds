package com.lifeix.d20131109;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LJApp {
	/**
	 * 停止运行
	 */
	public static boolean Closing = false;
	public static int time = 0;
	// 创建一个容量为19的队列
	public static Timer timer = new Timer();
	public static Timer timerChef = new Timer();
	public static BlockingQueue<String> shelf = new ArrayBlockingQueue<String>(19);
	public static void main(String[] args) {
		for(int i=0;i<19;i++){
			try {
				shelf.put("cake");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("初始蛋糕数："+shelf.size()+" cakes"); 
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				int i = LJApp.time;
				System.out.println(i);
				if(i%60 == 0){
					// 启动二个消费者线程
					new Consumer(shelf).start();
					new Consumer(shelf).start();
				}
				if(i%30 == 0){
					// 启动一个生产者线程
					new Chef(shelf).start();
				}
				if(i == 601){
					// 10分1秒后
					LJApp.Closing = true;
					System.out.println("剩余："+shelf.size()+" cakes");
					timerChef.cancel();
					timer.cancel();
				}
				LJApp.time++;
			}
			
		}, 0, 1000);
	}
}
/**
 * 消费者
 * @author abc
 *
 */
class Consumer extends Thread {
	//利用队列存储蛋糕
	private BlockingQueue<String> shelf;

	public Consumer(BlockingQueue<String> shelf) {
		super();
		this.shelf = shelf;
	}

	@Override
	public void run() {
			try {
				// 尝试取出元素，如果队列为空，则被线程阻塞
				shelf.take();
				int surplus = shelf.size();
				System.out.println("消费完成:" + surplus +" cakes");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
/**
 * 生产者
 * @author abc
 *
 */
class Chef  extends Thread{  
	//利用队列存储蛋糕 
    private BlockingQueue<String> shelf;
    
    public Chef(BlockingQueue<String> shelf) {  
        this.shelf = shelf;  
    }  
    @Override  
    public void run() {  
    	LJApp.timerChef.schedule(new TimerTask() {
			@Override
			public void run() {
				try{
					//尝试放入元素，如果对列已满，则线程被阻塞  
					shelf.put("cake");  
					int surplus = shelf.size();
					System.out.println("生产完成:"+ surplus +" cakes");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}, 30000);
    }  
}  
