package com.lifeix.d20130727;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 统计指定盘符下的文件及文件夹
 * @author Lifeix
 *
 */
public class JFQ_StatisticsFolers {

	private int folders = 0;  //文件夹数 
	private int files = 0;     //文件数
	private int hiddenFolders = 0;   //隐藏文件夹数
	private int hiddenFiles = 0;    //隐藏文件数
	private int readOnlyFiles = 0;   //只读文件数
	private Map<String, Integer> fileTypes = new HashMap<String, Integer>();  //文件类型数
	
	/**
	 * 统计指定文件夹下的文件
	 * @param path
	 */
	private void loopDist(String path) {
		if(null == path || "".equals(path)) {
			System.out.println("指定的文件路径不对");
		} else {
			File rootFile = new File(path);
			if(!rootFile.exists()) {
				System.out.println("指定的文件路径不存在");
			} else {
				File[] rootFiles = rootFile.listFiles();
				try {
					if(rootFiles.length > 0) {
						for(File file : rootFiles) {
							boolean isFile = file.isFile();
							boolean isHidden = file.isHidden();
							boolean isDirectory = file.isDirectory();
							
							if(isHidden && isDirectory) {
								++hiddenFolders;
							} else if(isHidden && isFile) {
								++hiddenFiles;
							}
							
							if(!file.canWrite()) {
								++readOnlyFiles;
							}
							
							if(isDirectory) {
								++folders;
								loopDist(file.getAbsolutePath());
							} else if(isFile) {
								++files;
							}
							
							if(isFile) {
								String name = file.getName();
								String suffix = name.substring(name.lastIndexOf(".") + 1);
								if(!fileTypes.containsKey(suffix)) {
									fileTypes.put(suffix, 1);
								} else {
									fileTypes.put(suffix, fileTypes.get(suffix) + 1);
								}
							}
						}
					}
				} catch(NullPointerException npe) {
					System.out.println(rootFile.getAbsolutePath() + "没有访问权限,已跳过");
				}
			}
		}
	}
	public JFQ_StatisticsFolers() {
		
		System.out.println("正在遍历磁盘....");
		long start = System.currentTimeMillis();
		loopDist("c:/");
		long end = System.currentTimeMillis();
		
		System.out.println("总共花费时间：" + (end - start)/1000 + "s");
		System.out.println("=========================磁盘遍历结束========================\n");
		
		System.out.println("文件夹数：" + folders + ", 文件数:" + files + ", 隐藏文件夹数：" + 
				hiddenFolders + ", 隐藏文件数：" + hiddenFiles + ",只读文件数有：" + readOnlyFiles + "\n");
		
//		System.out.println("=========================文件类型与数量========================");
//		for(Entry<String, Integer> entry : fileTypes.entrySet()) {
//			System.out.println("文件类型" + entry.getKey() + "有" + entry.getValue() + "个");
//		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JFQ_StatisticsFolers();
	}

}
