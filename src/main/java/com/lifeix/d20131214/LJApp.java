package com.lifeix.d20131214;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LJApp {

	public static void main(String[] args){
		Scanner scanner = new Scanner( System.in );
		boolean go = true;
		goo: while(go){
			try{
				System.out.println("输入：");
				String[] s = scanner.nextLine().split(" ");
				int n=Integer.parseInt(s[0]);
				int m=Integer.parseInt(s[1]);
				int[][] country = new int[2*m][2];
				for(int i=0;i<2*m;i+=2){
					String[] str = scanner.nextLine().split(" ");
					int j = Integer.parseInt(str[0]);
					int k = Integer.parseInt(str[1]);
					country[i][0] = j;
					country[i][1] = k;
					country[i+1][0] = k;
					country[i+1][1] = j;
				}
				LJApp app = new LJApp();
				System.out.println(app.calculate(country));
			}catch (Exception e) {
				System.out.println("输入出错，重新来过！");
				continue goo;
			}
			System.out.println("继续？【yes/no】");
			String s = scanner.nextLine().trim();
			if(s.equals("no"))go = false;
		}
	}
	
	public int calculate(int[][] m){
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<Integer, List<Integer>> list = new HashMap<Integer, List<Integer>>();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<m.length;i++){
			if(list.containsKey(m[i][0])){
				list.get(m[i][0]).add(m[i][1]);
			}else{
				List<Integer> l = new ArrayList<Integer>();
				l.add(m[i][1]);
				list.put(m[i][0], l);
			}
			builder.append(m[i][0]).append(",").append(m[i][1]);
			map.put(builder.toString(), null);
			builder.setLength(0);
		}
		for(int i=0;i<m.length;i++){
			if(list.containsKey(m[i][1])){
				List<Integer> l = list.get(m[i][1]);
				for(Integer in : l){
					builder.append(m[i][0]).append(",").append(in);
					if(map.containsKey(builder.toString()))return -1;
					builder.setLength(0);
				}
			}
		}
		return 1;
	}
}
