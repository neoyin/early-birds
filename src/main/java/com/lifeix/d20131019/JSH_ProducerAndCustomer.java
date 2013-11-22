package com.lifeix.d20131019;

import java.util.ArrayList;
import java.util.List;

public class JSH_ProducerAndCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IFrame iframe = new IFrame();
		for(int i=0; i<4; i++) {
			new Producer("ProducerThread-"+i, iframe).start();
		}
		for(int i=0; i<4; i++) {
			new Customer("CustomerThread-"+i, iframe).start();
		}
	}

}

class Producer extends Thread {
	private IFrame iframe;
	private static int id = 0;
	public Producer(String name, IFrame iframe) {
		super(name);
		this.iframe = iframe;
	}
	public void run() {
		try {
			while(true) {
				String cake=" [Cake No."+nextId()+" by "+Thread.currentThread().getName()+"]";
				iframe.put(cake);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static int nextId() {
		return id++;
	}
}

class Customer extends Thread {
	private IFrame iframe;
	public Customer(String name, IFrame iframe) {
		super(name);
		this.iframe = iframe;
	}
	public void run() {
		try {
			while(true) {
				iframe.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class IFrame {
	private final List<String> cakes;
	private int head;
    private int tail;
    private int count;
    private static final int MAX_LANGTH = 19;
    public IFrame() {
    	cakes = new ArrayList<String>();
    	head = 0;
    	tail = 0;
    	count = 0;
    }
    public synchronized void put(String cake) {
    	try {
			System.out.println(Thread.currentThread().getName()+" puts "+cake);
			while(count >= MAX_LANGTH)
			{
			    System.out.println(Thread.currentThread().getName()+" Begin wait....");
			    wait();
			    System.out.println(Thread.currentThread().getName()+" End wait....");
			}
			cakes.add(cake);
			tail = (tail+1) % MAX_LANGTH;
			count++;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public synchronized String get() {
    	String cake = "";
		try {
			while(count<=0)
			{
			    System.out.println(Thread.currentThread().getName()+" Begin wait....");
			    wait();
			    System.out.println(Thread.currentThread().getName()+" End wait....");
			}
			cake = cakes.get(head);
			head = (head+1) % MAX_LANGTH;
			count--;
			notifyAll();
			System.out.println(Thread.currentThread().getName()+" gets "+cake);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return cake;
    }
}
