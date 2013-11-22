package com.lifeix.d20130727;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class LJApp {


	private static boolean isOver;	//是否完毕
	
	private static String basePath;	//初始目录
	
	private static int NUM_FILE;	//文件数
		
	private static int NUM_PATH;	//文件数
	
	private static int NUM_HIDDEN;	//隐藏文件数
	
	private static int NUM_READ;	//只读文件数
	
	private static List<String[]> NAME;	//文件类型
	
	
	public static void main(String[] args){
		init();
		System.out.println("begin:");
		Read(basePath);
		System.out.println("show:");
		System.out.println("文件数："+NUM_FILE);
		System.out.println("文件夹："+NUM_PATH);
		System.out.println("隐藏数："+NUM_HIDDEN);
		System.out.println("只读数："+NUM_READ);
		if(NAME != null && NAME.size() > 0){
			StringBuilder names = new StringBuilder();
			int j = 0;
			for(int i=0;i<NAME.size();i++){
				names.append("、");
				names.append(NAME.get(i)[0]+"("+NAME.get(i)[1]+")");
				j++;
				if(j%300 == 0)names.append("\n");
			}
			System.out.println(names.toString());
		}else{
			System.out.println("文件内型读取错误！");
		}
		
		System.out.println("end!");
	}
	
	private static void Read(String path){
		File[] fileBase = go(path);
		if(fileBase == null){					//为空直接返回
			setOver(true);
			return;		
		}
		int i = 0;
		while(!isOver){
			File f = fileBase[i];
			if(f.isHidden()) addHidden();		//++隐藏
			if(!f.canWrite()) addRead();		//++只读
			if(f.isDirectory()){				//文件夹
				addPath();
				String newPath = path+"\\"+f.getName();
				//System.out.println(newPath);
				Read(newPath);
			}else{
				addFile();
				addName(f.getName());	//++添加文件类型
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
	/**
	 * 添加文件类型
	 * @param names
	 */
	private static void addName(String name){
		String prefix = name.substring(name.lastIndexOf(".")+1);
		if(NAME != null && NAME.size() > 0){
			boolean isHere = false;
			for(int i=0;i<NAME.size();i++){
				if(NAME.get(i)[0].equals(prefix)){
					isHere = true;
					NAME.get(i)[1] = (Integer.parseInt(NAME.get(i)[1]) + 1)+"";
					break;
				}
			}
			if(!isHere){
				String prefixs[] = {prefix,"1"};
				NAME.add(prefixs);
			}
		}else{
			String prefixs[] = {prefix,"1"};
			NAME.add(prefixs);
		}
	}
	/**
	 * 添加文件夹
	 */
	private static void addPath(){
		NUM_PATH++;
	}
	/**
	 * 添加文件
	 */
	private static void addFile(){
		NUM_FILE++;
	}
	/**
	 * 添加只读文件
	 */
	private static void addRead(){
		NUM_READ++;
	}
	/**
	 * 添加隐藏文件数
	 */
	private static void addHidden(){
		NUM_HIDDEN++;
	}
	/**
	 * 初始化
	 */
	private static void init(){
		setOver(false);
		setBasePath("c:\\");
		NAME = new ArrayList<String[]>();
	}
	public static boolean isOver() {
		return isOver;
	}
	public static void setOver(boolean isOver) {
		LJApp.isOver = isOver;
	}
	public static int getNUM_READ() {
		return NUM_READ;
	}
	public static void setNUM_READ(int nUM_READ) {
		NUM_READ = nUM_READ;
	}
	public static int getNUM_HIDDEN() {
		return NUM_HIDDEN;
	}
	public static void setNUM_HIDDEN(int nUM_HIDDEN) {
		NUM_HIDDEN = nUM_HIDDEN;
	}
	public static int getNUM_PATH() {
		return NUM_PATH;
	}
	public static void setNUM_PATH(int nUM_PATH) {
		NUM_PATH = nUM_PATH;
	}
	public static int getNUM_FILE() {
		return NUM_FILE;
	}
	public static void setNUM_FILE(int nUM_FILE) {
		NUM_FILE = nUM_FILE;
	}
	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		LJApp.basePath = basePath;
	}
}
