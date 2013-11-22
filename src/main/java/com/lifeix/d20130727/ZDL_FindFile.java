package com.lifeix.d20130727;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ZDL_FindFile {
	static int directoryCount=0;
	static int fileCount=0;
	static int hidefileCount=0;
	static int readCount=0;
	static File file=null;
	static HashMap<String,List<String>> fileMap=new HashMap<String,List<String>>();
	static List<String> nameList=null;
    public static void main( String[] args )
    {
    	long start = System.currentTimeMillis();
    	
    	
//        System.out.println( "Hello World!" );
    	ZDL_FindFile fileSearch=new ZDL_FindFile();
        System.out.println("搜索文件中.....");
        fileSearch.findFileCount("c://");
        
        long end = System.currentTimeMillis();
        System.out.println("spend times "+(end - start ));
        
        Set<String> fileSet = fileMap.keySet();
        Iterator<String> fileIterator = fileSet.iterator();
        while(fileIterator.hasNext()){
        	String suffix =(String) fileIterator.next();
        	System.out.print("以"+suffix);
        	nameList = fileMap.get(suffix);
        	System.out.print("结尾的文件"+nameList.size()+"个\n");
        }
        System.out.println("目录总数"+directoryCount);
        System.out.println("隐藏总数"+hidefileCount);
        System.out.println("只读总数"+readCount);
        System.out.println("文件总数"+fileCount);
    }
    public void findFileCount(String path){
    	file =new File(path);
    	if(file.isDirectory()){
    		directoryCount++;
	   	File listFile [] = file.listFiles();
    	try {
			for(File file:listFile){
				if(file.isDirectory()){
					findFileCount(file.getPath());
				}else{
					subFileName(file.getName());
					if(file.isHidden()){
						hidefileCount++;
					}
					if(file.canRead()){
						readCount++;
					}
					fileCount++;
				}				
			}
		} catch (Exception e) {
		}	
    	}
    }
    public void subFileName(String fileName){
    	//得到后缀
    	String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
    	if(fileName.lastIndexOf(".")==-1){
    		return;
    	}
    	if(fileMap.get(suffix)==null||fileMap.get(suffix).equals("")){
    		nameList = new ArrayList<String>();
    		fileMap.put(suffix, nameList);
    	}//放入list
    	nameList = fileMap.get(suffix);
    	nameList.add(fileName.substring(0,fileName.lastIndexOf(".")));    	
    }
}
