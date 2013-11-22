package com.lifeix.d20130803.lj;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LjThread implements Runnable{
	
	private  int NUM_PATH;	//目录
	
	private  int NUM_FILE;	//文件
	
	private  long NUM_SIZE;	//大小
	
	private  boolean isOver;	//是否完毕
	
	private  String path;
	
	List<Thread> tNum = new ArrayList<Thread>();
	
	private void read(){
		File[] fileBase = go(path);
		if(fileBase == null){					//为空直接返回
			isOver = true;
			return;		
		}
		int i = 0;
		while(!isOver){
			File f = fileBase[i];
			if(f.isDirectory()){				//文件夹
				NUM_PATH++;
				String newPath = path+"\\"+f.getName();
				LjThread app = new LjThread();
				app.setPath(newPath);
				Thread t = new Thread(app);
				t.start();
				tNum.add(t);
			}else{
				NUM_FILE++;
				NUM_SIZE = NUM_SIZE + f.length();
			}
			i++;
			if(i<fileBase.length){
				setOver(false);
			}else{
				setOver(true);
			}
		}
	}
	/**
	 * 读取一层文件及文件夹
	 * @param path
	 */
	private static File[] go(String path){
		File pathFile = new File(path.toString());					
		File[] fileArray = pathFile.listFiles();							//获取当前目录下文件及文件夹名
		if(fileArray == null || fileArray.length <= 0)return null;			//目录为空，返回
		return fileArray;
	}
	public void run(){
		read();
		LjApp.addFile(NUM_FILE);
		LjApp.addPath(NUM_PATH);
		LjApp.addSize(NUM_SIZE);
		for(int i=0;i<tNum.size();i++){
			try{
				tNum.get(i).join();
			}catch (InterruptedException e) {  
	        }  
		}
	}

	public int getNUM_PATH() {
		return NUM_PATH;
	}

	public void setNUM_PATH(int nUM_PATH) {
		NUM_PATH = nUM_PATH;
	}

	public int getNUM_FILE() {
		return NUM_FILE;
	}

	public void setNUM_FILE(int nUM_FILE) {
		NUM_FILE = nUM_FILE;
	}



	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
