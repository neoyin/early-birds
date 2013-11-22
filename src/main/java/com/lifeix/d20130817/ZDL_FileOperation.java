package com.lifeix.d20130817;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZDL_FileOperation implements Runnable{
	static long countline;
	static BufferedReader fileInputStream;
	static boolean bo =true;
	static File file = new File("Z://lifeix-growth-road.txt");
	
	public static void main(String[] args) {
		operFile(file);
	}	
	public static void operFile(File file) {
		try {
			fileInputStream=new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
			int order=1;
			long start = System.currentTimeMillis();
			System.out.println("start....\n");
			while(order<150){
					readFile(fileInputStream);
					new Thread(new ZDL_FileOperation()).start();
					order++;
			}
			System.out.println("end....\n"+(System.currentTimeMillis()-start));
			System.out.println("countline:"+countline+"ms");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private synchronized static String synreadFile(BufferedReader br) {

		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static  void readFile(BufferedReader br) {
			String StrFile;
			while((StrFile=synreadFile(br))!=null){
				if(bo){
				countline++;
				System.out.println("countline"+countline);
				}
			}
			System.out.println("countline"+countline);
			bo=false;
	}
	@Override
	public void run() {
		readFile(fileInputStream);
	}	
}
