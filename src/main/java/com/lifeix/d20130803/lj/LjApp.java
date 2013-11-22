package com.lifeix.d20130803.lj;

import java.io.File;


public class LjApp {

	private static int NUM_PATH;	//目录
	
	private static int NUM_FILE;	//文件
	
	private static long NUM_SIZE;	//大小
	
	public static void main(String[] args){
		System.out.println("start...................");
		Long startTime = System.currentTimeMillis();
		LjThread app = new LjThread();
		app.setPath("c:\\");
		Thread t = new Thread(app);
		t.start();
		File pathFile = new File("c:\\");	
		try{
			t.join();
		}catch (InterruptedException e) {  
        }  
		long size = pathFile.getTotalSpace() - pathFile.getFreeSpace();
		System.out.println("文件数:"+LjApp.getNUM_FILE());
		System.out.println("目录数:"+LjApp.getNUM_PATH());
		System.out.println("文件总大小:"+LjApp.getNUM_SIZE()/1024.0/1024.0/1024.0+" G");
		System.out.println("已用空间:"+(double)(size/1024.0/1024.0/1024.0)+" G");
		Long endTime = System.currentTimeMillis();
		System.out.println("Totle time is " + (endTime - startTime)/1000 + "seconds");
	}
	public synchronized static void addPath(int num){
		NUM_PATH+=num;
	}
	public synchronized static void addFile(int num){
		NUM_FILE+=num;
	}
	
	public synchronized static void addSize(long num){
		NUM_SIZE+=num;
	}
	public static int getNUM_PATH() {
		return NUM_PATH;
	}
	public static void setNUM_PATH(int nUM_PATH) {
		NUM_PATH = nUM_PATH;
	}
	public static int getNUM_FILE() {
		return NUM_FILE;
	}
	public static void setNUM_FILE(int nUM_FILE) {
		NUM_FILE = nUM_FILE;
	}
	public static long getNUM_SIZE() {
		return NUM_SIZE;
	}
	public static void setNUM_SIZE(long nUM_SIZE) {
		NUM_SIZE = nUM_SIZE;
	}
}
