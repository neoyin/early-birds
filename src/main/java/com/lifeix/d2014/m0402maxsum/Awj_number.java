package com.lifeix.d2014.m0402maxsum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Awj_number {

	public static void main(String[] args) throws IOException {

		String filePath = "/tmp/number";
		File file = new File(filePath);
		InputStreamReader reader;
		try {
			String line = "";
			int number = 0;
			int sum = 0;
			int temp = 0;
			int start_line = 0;
			int end_line = 0;
			int mark = 0;
			reader = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader br = new BufferedReader(reader);
			while ((line = br.readLine())!=null) {
				number++;
				if(temp<0){
					temp = Integer.valueOf(line);
					mark = number;
				}else{
					temp += Integer.valueOf(line);
				}
				if(sum < temp){
					sum = temp;
					start_line = mark;
					end_line = number;
				}
			}
			System.out.println("最大和:"+sum);
			System.out.println("开始结束行为:"+start_line+"~"+end_line);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
