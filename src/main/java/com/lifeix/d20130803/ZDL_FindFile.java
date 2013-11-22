package com.lifeix.d20130803;

import java.io.File;


public class ZDL_FindFile {
	static int dirCount=0;
	static int fileCount=0;
	static long filelength=0;
	//		File file=new File("E://zhangdonglei/tmp");
	File file=new File("c://");
	static int m=0;
	public static void main(String[] args) {
		ZDL_FindFile zdl_FindFile=new ZDL_FindFile();
		System.out.println("开始...");
		zdl_FindFile.findFile();

	}
	public void findFile(){
		File [] files=file.listFiles();
		if(files!=null){
			for(File filex:files){
				if(filex.isDirectory()){
					dirCount++;
					file=new File(filex.getPath());
					if(file!=null){
						ZDL_MyThread myThread = new ZDL_MyThread(file,file.getName());
						myThread.start();
						try {
							myThread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}else{
					fileCount++;
					filelength+=filex.length();
				}
			}
		}
		System.out.println("文件夹"+dirCount);
		System.out.println("文件"+fileCount);
		System.out.println("文件长度"+filelength/1024.0/1024.0/1024.0);
	}
}
