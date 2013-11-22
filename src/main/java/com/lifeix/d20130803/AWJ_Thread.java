package com.lifeix.d20130803;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AWJ_Thread extends Thread {

	private static List filesList = new ArrayList();
	private static int fileCount, folderCount = 0;
	private static long fileSize = 0;
	String path = "";

	public AWJ_Thread(String path){
		this.path = path;
	}

	public AWJ_Thread(){

	}

	// 重写run方法
	public void run() {
		super.run();
		readFile(path);
	}

	private void readFile(String strPath) {
		synchronized (this){
			File dirFile = new File(strPath);
			File[] files = dirFile.listFiles();
			if(files == null) return;
			for(int i=0;i<files.length;i++){  
				File file = files[i];
				if(file != null){
					if(file.isDirectory())   { 
						folderCount++;
						readFile(file.getAbsolutePath());
					}
					else {
						fileCount++;
						long size = file.length();
						fileSize+=size;
					}
					filesList.add(files[i].getAbsolutePath());
				}
			}
		}
	}

	public static void main(String[] args) {

		AWJ_Thread readFile = new AWJ_Thread();
		String filePath = "C://";
		readFile.init(filePath);
	}

	public void init(String filePath){
		File  file = new File(filePath);
		File files [] = file.listFiles();
		AWJ_Thread  readFile = null;
		long startTime = System.currentTimeMillis();
		for(int i=0;i<files.length;i++){
			File f = files[i];
			if(f.isDirectory()){
				folderCount++;
				readFile = new AWJ_Thread(f.getAbsolutePath());
				System.out.println(readFile.getName());
				readFile.start();
				try {
					readFile.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				fileCount++;
				fileSize+=f.length();
			}
		}
		System.out.println("共计消耗："+(System.currentTimeMillis()-startTime)/1000+"秒");
		System.out.println(path+"该目录总数量为："+filesList.size());
		System.out.println("文件夹总数为："+folderCount);
		System.out.println("文件总数为："+fileCount);
		System.out.println("文件总大小为："+fileSize/1024.0/1024.0/1024.0+"G");
	}
}