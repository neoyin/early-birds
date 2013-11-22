package com.lifeix.d20130925;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SHJ_ErgodicClass {

	private static final String JAR = ".jar";
	private static final String CLASS = ".class";
	private static final String COM = "com";
	private int count;
	
	public SHJ_ErgodicClass() {
		count = 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir");
		SHJ_ErgodicClass es = new SHJ_ErgodicClass();
		es.ergodicDir(path);
		System.out.println("class文件总数 ： " + es.count);
	}
	private void ergodicDir(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		if(files.length <= 0) {
			return;
		}
		for(File f : files) {
			if(f.isDirectory()) {
				if(f.getName().equals("com")) {
					ergodicComDir(f.getAbsolutePath());
				}else {
					ergodicDir(f.getAbsolutePath());
				}
			}else {
				if(f.getName().endsWith(JAR)) {
					ergodicJar(f);
				}
			}
		}
	}
	private void ergodicComDir(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		if(files.length <= 0) {
			return;
		}
		for(File f : files) {
			if(f.isDirectory()) {
				ergodicComDir(f.getAbsolutePath());
			}else {
				if(isFit(f.getAbsolutePath())) {
					count++;
					System.out.println(f.getAbsolutePath());
				}else if(f.getName().endsWith(JAR)) {
					ergodicJar(f);
				}
			}
		}
	}
	private void ergodicJar(File file) {
		try {
			JarFile jf = new JarFile(file);
			Enumeration<JarEntry> entrys = jf.entries();
			while(entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				if(isFit(jarEntry.getName())) {
					count++;
					System.out.println(file.getAbsolutePath() + "==>" + jarEntry.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean isFit(String name) {
		if(name.endsWith(CLASS) && name.contains(COM)) {
			return true;
		}else {
			return false;
		}
	}
}
