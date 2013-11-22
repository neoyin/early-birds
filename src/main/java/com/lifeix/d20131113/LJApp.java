package com.lifeix.d20131113;

import java.util.Scanner;

public class LJApp {

	public static int distance = 10000000;
	public static int max = 200000;
	public static int min = 1;
	public static void main(String[] args){
		Scanner scanner = new Scanner( System.in );
		boolean goon = true;
		begin:
		while(goon){
			System.out.println("请输入:(灯塔数量最少"+min+"个，最多"+max+"个)");
			int number = 0;
			try{
				 number = Integer.parseInt(scanner.nextLine().trim());
			}catch(Exception e){
				System.out.println("输入错了，重来!");
				continue begin;
			}
			if(number< min || number > max){
				System.out.println("输入错了，重来!");
				continue begin;
			}
			int[][] deng = new int[number][2];
			for(int i=0;i<number;i++){
				try{
					String[] str = scanner.nextLine().split(" ");
					deng[i][0] = Integer.parseInt(str[0]);
					deng[i][1] = Integer.parseInt(str[1]);
					if(deng[i][0] <0 || deng[i][0] > distance || deng[i][1] <0 || deng[i][1] > distance){
						System.out.println("灯塔插到山上去了！！！！重来！");
						continue begin;
					}
				}catch(Exception e){
					System.out.println("输入错了，重来！！");
					continue begin;
				}
			}
			System.out.println("照亮灯塔数量："+light(deng));
			
			while(true){
				System.out.println("继续/退出？[yes/no]");
				String s = scanner.nextLine().trim();
				if(s.equals("yes")){
					goon = true;
					break;
				}
				if(s.equals("no")){
					goon = false;
					break;
				}
				System.out.println("听话，输入(yes/no)");
			}
		}
	}
	
	public static int light(int[][] deng){
		int n = 0;
		for(int i=0;i<deng.length-1;i++){
			int[] d1 = deng[i];
			for(int j=i+1;j<deng.length;j++){
				int[] d2 = deng[j];
				if(isLight(d1,d2))n++;
			}
		}
		return n;
	}
	
	public static boolean isLight(int[] d1, int[] d2){
		if(d1[0] > d2[0] && d1[1] > d2[1]){
			return true;
		}
		if(d1[0] < d2[0] && d1[1] < d2[1]){
			return true;
		}
		return false;
	}
}
