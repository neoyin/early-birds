package com.lifeix.d20130925;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AWJ_Test {

	private static boolean flag = false;

	private static Integer count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String classPath = AWJ_Test.class.getClassLoader().getResource("").getFile();
		File file = new File(classPath);
		System.out.println(file.getAbsolutePath());
		new AWJ_Test().readClassPath(file);
		System.out.println(count);
	}

	public void readClassPath(File file){
		if(file != null){
			File [] files = file.listFiles();
			for(int i=0;i<files.length;i++){
				File f = files[i];
				if(!flag){
					if(f.isDirectory() && f.getName().equals("com")){
						flag = true;
						readClassPath(f);
					}
				}else{
					if(f.isDirectory()){
						readClassPath(f);
					}else if(javaFile(f.getName())){
						System.out.println(f.getName());
						count++;
					}else if(jarFile(f.getName())){
						try {
							readJar(f.getAbsolutePath(), count);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public boolean javaFile(String fileName){
		String type = "";
		if(fileName != null || !"".equals(fileName)){
			type = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		}
		if(type.equals("class")){
			return true;
		}
		return false;
	}

	public boolean jarFile(String fileName){
		String type = "";
		if(fileName != null || !"".equals(fileName)){
			type = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		}
		if(type.equals("jar")){
			return true;
		}
		return false;
	}


	public void readJar(String jarPath, int count) throws IOException {
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> entrys = jarFile.entries();
		while (entrys.hasMoreElements()) {
			JarEntry jarEntry = entrys.nextElement();
			if(javaFile(jarEntry.getName())){
				if(jarEntry.getName().startsWith("com/")){
					String name = jarEntry.getName();
					System.out.println(name.substring(name.lastIndexOf("/")+1,name.length()));
					count++;
				}
			}
		}				
	}


}
