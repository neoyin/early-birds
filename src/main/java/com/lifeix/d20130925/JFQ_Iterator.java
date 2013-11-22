package com.lifeix.d20130925;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @DESCRIPTION:	
 * @DATE:2013-9-25 下午05:11:47
 **/
public class JFQ_Iterator {
	
	public static Map<String, String> classMap = Collections.synchronizedMap(new HashMap<String, String>());
	public static final String jar = "jar";
	public static final String fn = "class";
	public static volatile int count1 = 0;
	public static volatile int count2 = 0;
	private void start(File file) {
		if(!file.isDirectory()) {
			return;
		} 
		File[] files = file.listFiles();
		if(files.length < 1) {
			return;
		}
		for(File f : files) {
			if(f.isDirectory()) {
				File sf = new File(f.getAbsolutePath());
				File[] fs = sf.listFiles();
				if(fs.length > 0) {
					for(File ff : fs) {
						if(ff.isDirectory()) {
							count1 += 1;
		//					System.out.println("count1=" + count1);
							new Thread(new FileIterator(f.getAbsolutePath())).start();
						}else if(f.isFile()) {
							String name = ff.getName();
							if(name.endsWith(jar)) {
								count2 += 1;
								new Thread(new JarIterator(ff.getAbsolutePath())).start();
					
							} else if(name.endsWith(fn)) {
								classMap.put(ff.getAbsolutePath(), name);
							}
						}
					}
				}
			} else if(f.isFile()) {
				String name = f.getName();
				if(name.endsWith(jar)) {
					new Thread(new JarIterator(f.getAbsolutePath())).start();
					count2 += 1;
				} else if(name.endsWith(fn)) {
					classMap.put(f.getAbsolutePath(), name);
				}
			}
		}
	}
	
	/**@description:	
	 * @return:void
	 * @param args
	 * @throws InterruptedException 
	 */

	public static void main(String[] args){
		JFQ_Iterator jfq = new JFQ_Iterator();
		long start = System.currentTimeMillis();
		jfq.start(new File("D:\\jiang\\workspaces\\l06"));
		long time = 0;
		while(true) {
			if(count1 <= 0 && count2 <= 0) {
				long end = System.currentTimeMillis();
				for(Entry entry : classMap.entrySet()) {
					System.out.println("======" + entry.getKey() + ", " + entry.getValue());
				}
				System.out.println("size=" + classMap.size() + ", time=" + (end - start));
				break;
			}
		}
	}

	
}

class FileIterator implements Runnable {
	private String path;
	public FileIterator(String path) {
		this.path = path;
	}
	
	public void run() {
//		if(path.endsWith("svn") || path.endsWith("settings")) {
//			return;
//		}
		File file = new File(path);
		File[] files = file.listFiles();
		if(files.length > 0) {
			for(File f : files) {
				String name = f.getName();
				if(name.endsWith("svn")) {
					continue;
				}
				
				if(f.isDirectory()) {
					iteratorDir(f.getAbsolutePath());
				} else if(f.isFile()) {
					if(name.endsWith(JFQ_Iterator.jar)) {
						JFQ_Iterator.count2 += 1;
						new Thread(new JarIterator(f.getAbsolutePath())).start();
					} else if(name.endsWith(JFQ_Iterator.fn)) {
						JFQ_Iterator.classMap.put(f.getAbsolutePath(), name);
					}
				}
			}
		}
		JFQ_Iterator.count1 -= 1;
	}
	
	private void iteratorDir(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		if(files.length > 0) {
			for(File f : files) {
				String name = f.getName();
				if(name.endsWith("svn")) {
					continue;
				}
				if(f.isDirectory()) {
					iteratorDir(f.getAbsolutePath());
				} else if(f.isFile()) {
					if(name.endsWith(JFQ_Iterator.jar)) {
						JFQ_Iterator.count2 += 1;
						new Thread(new JarIterator(f.getAbsolutePath())).start();
					} else if(name.endsWith(JFQ_Iterator.fn) && f.getAbsolutePath().indexOf("\\com\\") > 0) {
						JFQ_Iterator.classMap.put(f.getAbsolutePath(), name);
					}
				}
			}
		}
	}
}

class JarIterator implements Runnable {
	private String path;
	public JarIterator(String path) {
		this.path = path;
	}
	
	public void run() {
		calJarClass(path);
		JFQ_Iterator.count2 -= 1;
//		System.out.println("count2=" + JFQ_Iterator.count2 + ", " + JFQ_Iterator.count1);
	}
	
	private void calJarClass(String path) {
		try {
			JarFile zf = new JarFile(path);
			Enumeration e = zf.entries();
			while(e.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) e.nextElement();
				String name = ze.getName();
				if(name.endsWith(JFQ_Iterator.fn) && name.indexOf("com/") >= 0) {
					JFQ_Iterator.classMap.put(zf.getName()+"_"+name, name.substring(name.lastIndexOf("/") + 1));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			
		}
		
	}
}