package com.lifeix.d20130803;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSHMyThread extends Thread {

	File file = null;
	static int dirNum;
	static int fileNum;
	static long fileSize;
	private List<Thread> runList = new ArrayList<Thread>();

	public JSHMyThread(File file) {
		this.file = file;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ergodicFiles(file);
			for(Thread tt : runList) {
				tt.join();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void ergodicFiles(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if(f.isDirectory()) {
//					dirNum++;
					setDirNum(1);
					Thread th = new JSHMyThread(f);
					th.start();
					runList.add(th);
				}else if (f.isFile()) {
//					fileNum++;
//					fileSize += f.length();
					setFileNum(1);
					setFileSize(f.length());
				}
			}
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		try {
			Thread t = new JSHMyThread(new File("c:\\"));
			t.start();
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("文件夹数===>" + (getDirNum() - 1));
		System.out.println("文件数====>" + getFileNum());
		System.out.println("总文件大小===>" + (getFileSize()/1024.0/1024.0/1024.0) + "G");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime+"ms");
	}

	public static int getDirNum() {
		return dirNum;
	}

	public synchronized static void setDirNum(int dirNum) {
		JSHMyThread.dirNum = getDirNum() + dirNum;
	}

	public static int getFileNum() {
		return fileNum;
	}

	public synchronized static void setFileNum(int fileNum) {
		JSHMyThread.fileNum = getFileNum() + fileNum;
	}

	public static long getFileSize() {
		return fileSize;
	}

	public synchronized static void setFileSize(long fileSize) {
		JSHMyThread.fileSize = getFileSize() + fileSize;
	}
}
