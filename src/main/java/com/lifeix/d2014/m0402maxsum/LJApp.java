package com.lifeix.d2014.m0402maxsum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LJApp {

	
	public static List<Integer> read(String path) throws NumberFormatException, IOException{
		List<Integer> numbers = new ArrayList<Integer>();
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String s = null;
		while((s=br.readLine())!=null){
			numbers.add(Integer.parseInt(s.trim()));
		}
		return numbers;
	}
	public static void main(String[] args){
		try{
			List<Integer> number = read("/home/abc/test/number");
			int sum = 0;
			int temp = 0;
			int start = 0;
			int end = 0;
			int font = 0;
			for(int i=0;i<number.size();i++){
				if(temp<0){
					temp = number.get(i);
					font = i;
				}else{
					temp += number.get(i);
				}
				if(sum < temp){
					sum = temp;
					start = font;
					end = i;
				}
			}
			System.out.println(sum+"|"+start+"|"+end);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
