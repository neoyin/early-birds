package com.lifeix.d20130803;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Zhch {
	private static int corePoolSize = 3;
	private static int maxPoolSize = 50;
	private static int keepAliveTime = 60;
	private static int start = 0;
	private static int end = 0;

	private static ThreadPoolExecutor threadPool;
	private static CompletionService<long[]> service;

	private static void init(){
			threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
		service = new ExecutorCompletionService<long[]>(threadPool);
	}
	
	
	public void count(String path) {
		long[] result = count(new File(path));
		System.out.println("目录数:" + result[0] + " 文件数:" + result[1] + " 大小:" + formatSize(result[2]));
	}
	
	public String formatSize(long size){
		return size + "B " + (size/1024) + "k " + (size/(1024*1024)) + "M " + (size/(1024*1024*1024)) + "G";
	}
	
	public static long[] count(File file){
		if(!file.exists()){
			return new long[3];
		}
		if(file.isFile()){
			long[] result = new long[]{0, 1, file.length()};
//			printArr(result);
//			System.out.println(file.getName());
			return result;
		}
		
		
		
		
		long[] result = new long[]{1,0,0};
		File[] files = file.listFiles();
		for(File subFile : files){
			System.out.println(subFile.getName());
			
			System.out.println(" activeCount:" + threadPool.getActiveCount());
				System.out.println(" start:" + (start++));
				service.submit(new CountThread(subFile));
		}
		
		for(File subFile : files){
			long[] subCount = null;
			try {
				System.out.println(" end:" + (end++));
				subCount = service.take().get();
			} catch (Exception e) {
				e.printStackTrace();
				subCount =  new long[3];
			}
			for(int i=0;i<3;i++){
				result[i] += subCount[i];
			}
		}
		return result;
	}
	
	public static void printArr(long[] arr){
		for(long num : arr){
			System.out.print(num + "  ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		init();
		Zhch t = new Zhch();
		t.count("/home/lifeix/temp/d4/ab");
//		t.count("/home/lifeix/workspace_indigo/lifeix-college/src/main/");
//		t.count("/home/lifeix/workspace_indigo/");
		threadPool.shutdown();
	}
}
class CountThread implements Callable<long[]>{
	File file;
	public CountThread(File file){
		this.file = file;
	}
	@Override
	public long[] call() throws Exception {
		return Zhch.count(file);
	}
}
