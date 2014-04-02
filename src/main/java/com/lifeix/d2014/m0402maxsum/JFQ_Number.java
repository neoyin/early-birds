package com.lifeix.d2014.m0402maxsum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class JFQ_Number {
	private static final String FILE_PATH = "/home/lifeix/number";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		startNumber();
	}

	private static void startNumber() throws IOException {
		FileInputStream fis = new FileInputStream(new File(FILE_PATH));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = br.readLine();
		int count = 0;
		int currentSum = 0;
		int startLine = 0;
		int endLine = 0;
		int lastSum = currentSum;
		int tempSum = 0;
		
		while(line != null) {
			count++;
			int l = Integer.parseInt(line.trim());
			if(count <3) {
				currentSum += l;
				endLine = count;
				lastSum = currentSum;
			} else {
				tempSum = currentSum;
				currentSum += l;
				
				if(tempSum < currentSum) {
					if(currentSum < l) {
						currentSum = l;
						startLine = count;
					}
					if(lastSum < currentSum) {
						lastSum = currentSum;
						endLine = count;
					}
				} else {
					if(lastSum < tempSum) {
						lastSum = tempSum;
					}
				}
			
			}
			line = br.readLine();
		}
		System.out.println("startLine=" + startLine + ", endLine=" +endLine + ", sumline=" + lastSum);
	}
	
}
