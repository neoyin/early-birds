package com.lifeix.d2014.m0219instrumentation;

public class Serv {
	
	public void init() throws InterruptedException{
		Thread.sleep(100);
	}
	public void work() throws InterruptedException{
		Thread.sleep(200);
	}
	public void clearup() throws InterruptedException{
		Thread.sleep(300);
	}
	public static void main(String[] args) throws InterruptedException {
		Serv serv = new Serv();
		serv.init();
		serv.work();
		serv.clearup();
	}
}
