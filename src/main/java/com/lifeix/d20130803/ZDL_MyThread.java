package com.lifeix.d20130803;

import java.io.File;

public class ZDL_MyThread extends Thread{

	File file;
	File[] files;
	public ZDL_MyThread(File file,String name) {
		this.file=file;
		super.setName(name+"");
	}

	@Override
	public void run() {++ZDL_FindFile.m;
	listFile(file);
	}

	public void listFile(File file) {
		files = file.listFiles();
		if(files!=null){
			for(File filex:files){
				if(filex.isDirectory()){
					ZDL_FindFile.dirCount++;
					listFile(filex);
				}else{
						ZDL_FindFile.fileCount++;
						ZDL_FindFile.filelength+=filex.length();
				}
			}
		}
	}
}
