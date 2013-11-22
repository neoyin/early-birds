package com.lifeix.d20130727;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Zhch {
	private int fileCount = 0;
	private int hiddenCount = 0;
	private int dirCount = 0;
	private int readOnlyCount = 0;
	private Map<String, Integer> typeMap = new HashMap<String, Integer>();
	
	public void countFile(String path){
		countFile(new File(path));

		System.out.println("目录数:" + dirCount);
		System.out.println("文件数:" + fileCount);
		System.out.println("隐藏文件数:" + hiddenCount);
		System.out.println("只读文件数:" + readOnlyCount);
		for(String extend : typeMap.keySet()){
			System.out.println(extend + " count:" + typeMap.get(extend));
		}
	}
	
	public void countFile(File file) {
		if(!file.exists()){
			System.out.println("nani." + file.getAbsolutePath());
			return;
		}
		if(!file.isDirectory()){
			fileCount++;
			String name = file.getName();
			if(name.indexOf(".") > 0){
				String extend = name.substring(name.lastIndexOf(".")+1);
				if(extend != null && extend.length() > 0){
					Integer count = typeMap.get(extend);
					count = count == null ? 0 : count;
					typeMap.put(extend, count + 1);
				}
			}
		}
		if(file.isHidden()){
			hiddenCount++;
		}
		if(file.canRead() && !file.canWrite()){
			readOnlyCount++;
		}
		dirCount ++;
		File[] files = file.listFiles();
		if(files != null){
			for(File subFile : files){
				countFile(subFile);
			}
		}
	}

	public static void main(String[] args) {
		Zhch t = new Zhch();
		t.countFile("c:/");
	}

}
