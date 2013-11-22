package com.lifeix.d20130727;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JSH {
	private File rootFile = null;
	private String path = null;
	private Map<String,Integer> typeMap = null;
	private int dircount;
	private int filecount;
	private int hiddencount;
	private int onlyReadcount;
	/**
	 * 
	 * @param path
	 */
	public JSH(String path){
		rootFile = new File(path);
		this.path = path;
		typeMap = new HashMap<String,Integer>();
		dircount = -1;
		filecount = 0;
		hiddencount = 0;
		onlyReadcount = 0;
	}
	/**
	 * 
	 * @param file
	 */
	protected void ergFile(File file){
		File[] f = file.listFiles();
		if(f != null){
			for(int i=0; i<f.length; i++){
				if(f[i].isDirectory()){
					ergFile(f[i]);
					dircount++;
				}
				if(f[i].isHidden()){
					hiddencount++;
				}
				if(f[i].isFile()){
					filecount++;
					if(f[i].canRead() && !f[i].canWrite()){
						onlyReadcount++;
					}
					writeTypeMap(f[i]);
				}
			}
		}
	}
	/**
	 * 记录文件类型和个数
	 * @param file
	 */
	protected void writeTypeMap(File file){
		String fileName = file.getName();
		String type = "no extensions";
		if(fileName.indexOf(".") >= 0){
			type = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		}
		if(isExistType(type)){
			int num = typeMap.get(type)+1;
			typeMap.put(type, num);
		}else{
			typeMap.put(type, 1);
		}
	}
	/**
	 * 判断文件类型是否已经存在map中的key了
	 * @param type
	 * @return  true->exist,false->not exist
	 */
	protected boolean isExistType(String type){
		boolean isExist = false;
		for(String existed : typeMap.keySet()){
			if(existed.equals(type)){
				isExist = true;
			}
		}
		return isExist;
	}
	/**
	 * 统计显示
	 */
	public void num(){
		ergFile(rootFile);
		System.out.println(path + "路径下文件夹的个数：" + dircount);
		System.out.println(path + "路径下文件的个数：" + filecount);
		System.out.println(path + "路径下隐藏文件的个数：" + hiddencount);
		System.out.println(path + "路径下只读文件数：" + onlyReadcount);
		System.out.println("show type and number,Start...");
		for(String type : typeMap.keySet()){
			System.out.println("type-->" + type + ";num-->" + typeMap.get(type));
		}
		System.out.println("show type and number,End...");
	}
	
	public static void main(String[] args) {
		new JSH("c:/").num();
	}
	
}
