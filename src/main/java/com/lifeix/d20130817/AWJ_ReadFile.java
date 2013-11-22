package com.lifeix.d20130817;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class AWJ_ReadFile extends Thread{

	private static int number = 0;
	private File file;

	public AWJ_ReadFile(File file){
		this.file = file;
	}
	
	public AWJ_ReadFile(){
		
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		String filePath = "Z:\\lifeix-growth-road.txt";
		cutFile(filePath);
		
		Thread.sleep(3000);
		System.out.println("开始读取切分文件.........");
		File dirFile = new File("E://temp");
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		
		long readStartTime = System.currentTimeMillis();
		File[] files = dirFile.listFiles();
		if(files == null) return;
		for(int i=0;i<files.length;i++){  
			System.out.println(files[i]+"-----------------"+(i+1));
			try {
				AWJ_ReadFile test = new AWJ_ReadFile(files[i]);
				test.start();
				test.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("文件行数总计约为："+number);
		System.out.println("读取行数消耗："+(System.currentTimeMillis()-readStartTime)/1000/60+"分");
	}

	
	public void run() {
		super.run();
		readFile(file);
	}
	
	/**
	 * 切分文件
	 * @param filePath
	 * @throws IOException
	 */
	private static void cutFile(String filePath) throws IOException{
		FileInputStream fin = new FileInputStream(filePath);
		FileChannel fcin = fin.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 10);
		int count = 0;
		//开切时间
		long cutStartTime  = System.currentTimeMillis();
		System.out.println("开始切分文件........");
		while(true)
		{
			buffer.clear();
			int flag = fcin.read(buffer);
			if(flag == -1)
			{
				break;
			}
			buffer.flip();
			String newFile = "E:\\temp1\\" + Math.random() + ".log";
			FileOutputStream fout = new FileOutputStream(newFile);
			FileChannel fcout = fout.getChannel();
			fcout.write(buffer);
			System.out.println("第"+ ++count +"个文件切分完成："+newFile);
		}
		System.out.println("切分文件消耗："+(System.currentTimeMillis()-cutStartTime)/1000/60+"分");
	}

	/**
	 * 读取文件
	 * @param file
	 */
	private void readFile(File file) {
		synchronized (this){
			String encoding="utf-8";
			if(file != null){
				try {
					InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
					BufferedReader reader = new BufferedReader(read);// 
					while(reader.readLine() != null){
						number++;
						System.out.println(number);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
