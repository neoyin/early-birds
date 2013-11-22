package com.lifeix;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import sun.tools.tree.BitAndExpression;

public class Util {

	private static int[] bits = new int[] { 1, 2, 4, 8, 16, 32, 64, 128 };

	/**
	 * 返回包名下的所有class
	 * 
	 * @param packageName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Set<Class<?>> getClasses(String packageName) throws UnsupportedEncodingException {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		Enumeration<URL> dirs = null;
		String packageDirName = packageName.replace('.', '/');
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		URL url = dirs.nextElement();
		String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
		File dir = new File(filePath);
		File[] dirfiles = dir.listFiles();
		for (File file : dirfiles) {
			if (!file.getName().endsWith(".class")) {
				continue;
			}
			String className = file.getName().substring(0, file.getName().length() - 6);
			try {
				classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}

	/**
	 * 返回byte位的字符串形式
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToStr(byte b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 7; i >= 0; i--) {
			sb.append((b & bits[i]) == bits[i] ? '1' : '0');
		}
		return sb.toString();
	}
	
	/**
	 * 返回byte[]位的字符串形式
	 * @param bs
	 * @return
	 */
	public static String bytesToStr(byte[] bs){
		String bytes = "";
		for (byte b : bs) {
			bytes += Util.byteToStr(b) + " ";
		}
		return bytes;
	}

	/**
	 * 把字符串补到指定的长度
	 * 
	 * @param src
	 * @param length
	 * @return
	 */
	public static String fillString(String src, int length) {
		if (src.length() < length) {
			int delta = length - src.length();
			for (int i = 0; i < delta; i++) {
				src += " ";
			}
		}
		return src;
	}
	

	/**
	 * 利用反射获得方法
	 * 
	 * @param clz 方法的类
	 * @param methodName 方法名
	 * @param args 参数
	 * @return
	 */
	public static Method getMethod(Class<?> clz, String methodName, Class<?>... args) {
		Method m = null;
		try {
			m = clz.getMethod(methodName, args);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static void main(String[] args) {
		System.out.println(byteToStr((byte) 256));
	}
}
