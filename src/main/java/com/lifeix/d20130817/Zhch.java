package com.lifeix.d20130817;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Zhch {

	static long total = 0;
	static int threadCount=0;

	public static synchronized void addLine(long lines) {
		total += lines;
		threadCount--;
	}

	public void read(String filePath) throws IOException, InterruptedException {

		long timeStart = System.currentTimeMillis();
		File file = new File(filePath);
		if(!file.exists()){
			System.out.println("404.");
			return;
		}
		long block_size = file.length()/30;
		for(long i=0;i<file.length();i+=block_size){
			
			Thread thread = new ReadThread(file, i, block_size);
			threadCount++;
			thread.start();
		}
		
		while(threadCount>0){
			System.out.println("threadCount:" + threadCount);
			System.out.println("time:" + (System.currentTimeMillis()- timeStart));
			
			Thread.sleep(500);
		}
		
		System.out.println("total lines:" + total);
		long timeEnd = System.currentTimeMillis();
		System.out.println("time:" + (timeEnd - timeStart));
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Zhch t = new Zhch();
		for (int i = 0; i < 1; i++) {
//			t.read("/home/lifeix/temp/d3/aa.json");
			t.read("/nfsdata/filedata/lifeix-growth-road.txt");
		}
	}

}

class ReadThread extends Thread{
	File file;
	long start; // 开始位置
	long size; // 数据大小

	int batch = 2048;
	static int threadCount;

	public ReadThread(File file, long start, long size) {
		this.file = file;
		this.start = start;
		this.size = size;
	}

	@Override
	public void run() {
		System.out.println("start a thread:" + (threadCount ++ ));
		try {
			if (!file.exists()) {
				return;
			}
			long total = 0;
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.skip(start);
			char[] buffer = new char[batch];
			int readSize = br.read(buffer);
			int count = 0;
			while (readSize > 0 && total < size) {
				total += readSize;
				for (int i = 0; i < readSize; i++) {
					if (buffer[i] == '\n') {
						count++;
					}
				}
				readSize = br.read(buffer);
			}
			Zhch.addLine(count);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
