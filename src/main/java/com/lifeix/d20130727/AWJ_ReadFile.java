package com.lifeix.d20130727;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AWJ_ReadFile {

	private static List filesList = new ArrayList();

	private static Map<String, Integer> fileTypeCount = new HashMap<String, Integer>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String filePath = "C://";
		System.out.println("*************************** 开始查询，请稍后...... ******************************");
		readFile(filePath);
		System.out.println("spend times:"+(System.currentTimeMillis()-start)/1000+"秒");
		Iterator iterator = fileTypeCount.entrySet().iterator();
		System.out.println("************************"+filePath+"该目录总数量为："+filesList.size()+" **********************");
		System.out.println("*****************************具体文件数量如下*****************************");
		while (iterator.hasNext()) {
			Entry  obj = (Entry) iterator.next();
			System.out.println(obj.getKey()+" 数量为："+obj.getValue()+"\n");
		}
		System.out.println("****************"+filePath+"该目录总数量为："+filesList.size()+" ***************");
	}

	private static void readFile(String strPath) {

		File dirFile = new File(strPath);   //创建指定路径的文件
		File[] files = dirFile.listFiles(); //列出指定路径下所有的文件存放在files数组中
		if(files == null) return;
		for(int i=0;i<files.length;i++){  
			File file = files[i];
			if(file != null){
				if(file.isHidden()) fileTypeCount.put("隐藏文件", (fileTypeCount.get("隐藏文件") == null ? 0 : fileTypeCount.get("隐藏文件"))+1);
				if(!file.canWrite()) fileTypeCount.put("只读文件", (fileTypeCount.get("只读文件") == null ? 0 : fileTypeCount.get("只读文件"))+1);
				if(file.isDirectory())   { 
					fileTypeCount.put("文件夹", (fileTypeCount.get("文件夹") == null ? 0 : fileTypeCount.get("文件夹"))+1);
					readFile(file.getAbsolutePath()); //是的话，递归继续查询下级目录
				}
				else {
					fileTypeCount.put("文件", (fileTypeCount.get("文件") == null ? 0 : fileTypeCount.get("文件"))+1);
					String strFileName = file.getName();
					int index = strFileName.lastIndexOf(".");
					if(index != -1){
						String fileType = strFileName.substring(index+1);
						if(fileTypeCount.containsKey(fileType)){
							fileTypeCount.put(fileType, fileTypeCount.get(fileType)+1);
						}else{
							fileTypeCount.put(fileType, 1);
						}
					}
				}
				filesList.add(files[i].getAbsolutePath());
			}
		}
	}

}
