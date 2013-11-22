package com.lifeix.d20130925;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LJApp {

	public final static String name = "com";
	public final static String fileName = "file";
	public final static String jarName = "jar";
	public final static String endName = ".class";
	public final static String sp = "/";
	
	public static void main(String[] args) {
		List<String> list = getClassList(name);
		System.out.println("total:" + list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	public static List<String> getClassList(String packageName) {
		List<String> classList = new ArrayList<String>();
		try {
			Enumeration<URL> urls = Thread.currentThread()
					.getContextClassLoader().getResources(packageName);
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals(fileName)) {
						String packagePath = url.getPath();
						addClass(classList, packagePath, packageName);
					} else if (protocol.equals(jarName)) {
						JarURLConnection jarURLConnection = (JarURLConnection) url
								.openConnection();
						JarFile jarFile = jarURLConnection.getJarFile();
						Enumeration<JarEntry> jarEntries = jarFile.entries();
						while (jarEntries.hasMoreElements()) {
							JarEntry jarEntry = jarEntries.nextElement();
							String jarEntryName = jarEntry.getName();
							if (jarEntryName.endsWith(endName)) {
								if (jarEntryName.substring(0,jarEntryName.indexOf(sp)).replaceAll(sp, "").equals(name)) {
									classList.add(jarEntryName);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	private static void addClass(List<String> classList, String packagePath,
			String packageName) {
		try {
			File[] files = getClassFiles(packagePath);
			if (files != null) {
				for (File file : files) {
					String fileName = file.getName();
					if (file.isFile()) {
						classList.add(fileName);
					} else {
						String subPackagePath = packagePath + sp + fileName;
						String subPackageName = fileName;
						addClass(classList, subPackagePath, subPackageName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static File[] getClassFiles(String packagePath) {
		return new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(endName))
						|| file.isDirectory();
			}
		});
	}
}
