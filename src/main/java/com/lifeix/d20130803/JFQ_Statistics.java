
package com.lifeix.d20130803;

import java.io.File;

public class JFQ_Statistics {

	private Long count = 0L;
	private int folderCount = 0;
	private int fileCount = 0;
	private String path = "C:/";

	public static void main(String[] args) throws InterruptedException {
		JFQ_Statistics jfq = new JFQ_Statistics();
		File file = new File(jfq.getPath());
		long start = System.currentTimeMillis();
		for(File f : file.listFiles()) {
			if(f.isFile()) {
				jfq.setFileCount(jfq.getFileCount() + 1);
				jfq.setCount(jfq.getCount() + f.length());
			} else {
				jfq.setPath(f.getAbsolutePath());
				jfq.setFolderCount(jfq.getFolderCount() + 1);
				StatisticsFolderThread sft = new StatisticsFolderThread(jfq);
				sft.start();
				sft.join();
				System.out.println(sft.getName());
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("fileCount=" + jfq.getFileCount()+ ", count=" + jfq.getCount()/(1024*1024*1014.0) + "G, folder=" + jfq.getFolderCount() + ", " + (end - start)/1000);
		
	}
	
	public Long getCount() {
		return count;
	}


	public void setCount(Long count) {
		this.count = count;
	}


	public int getFolderCount() {
		return folderCount;
	}

	public void setFolderCount(int folderCount) {
		this.folderCount = folderCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

class StatisticsFolderThread extends Thread {
	private JFQ_Statistics jfq = null;
	public StatisticsFolderThread(JFQ_Statistics jfq) {
		this.jfq = jfq;
	}
	
	public void run() {
		synchronized(jfq) {
			loopDist(jfq);
		}
		
	}
	private void loopDist(JFQ_Statistics jfq) {
		File rootFile = new File(jfq.getPath());
		File[] rootFiles = rootFile.listFiles();
		try {
			if(rootFiles.length > 0) {
				for(File file : rootFiles) {
					if(file.isDirectory()) {
						jfq.setPath(file.getAbsolutePath());
						jfq.setFolderCount(jfq.getFolderCount() + 1);
						loopDist(jfq);
					} else if(file.isFile()){
						jfq.setFileCount(jfq.getFileCount() + 1);
						jfq.setCount(jfq.getCount() + file.length());
					}
				}
			}
		} catch(NullPointerException npe) {
			
		}
	}
}

