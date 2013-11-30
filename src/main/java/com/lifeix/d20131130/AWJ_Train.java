package com.lifeix.d20131130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AWJ_Train implements TrainTest{

	@Override
	public boolean check(String mn, String trains) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(System.in);
		String str1,str2 = "";
		int m,n = 0;
		System.out.println("请输入列车数量和调度站可容纳列车数量（n m）,空格分隔，如：4 5");
		str1 = scanner.next();
		System.out.println(str1);
		System.out.println("请输入列车驶出顺序，空格分隔，如：1 3 2 4 5");
		str2 = scanner.next();*/
		System.out.println(manage("4 3", "1 4 2 3"));
		
	}

	public static boolean manage(String str1, String str2){
		String [] array = str1.split(" ");
		int n = Integer.valueOf(array[0]);//列车数量
		int m = Integer.valueOf(array[1]);//中转站车位
		String [] order = str2.split(" ");
		List<String> list =  Arrays.asList(order);

		//调度站车位数组
		List<String> schedule = new ArrayList<String>();
		
		List<String> out = new ArrayList<String>();
		//int count = 0;
		if(Integer.valueOf(order[0]) > m+1){
			return false;
		}else{
			for(int i=0; i<n; i++){
				if(Integer.valueOf(order[i]) != i+1){
					if(schedule.contains(order[i])){
						schedule.remove(order[i]);
						continue;
					}
					for(int j=Integer.valueOf(order[i])-1; j>0;j--){
						if(!schedule.contains(j) || !out.contains(String.valueOf(i+1))){
							if(schedule.size() > m){
								return false;
							}else{
								schedule.add(String.valueOf(j));
							}
						}
					}
				}else{
					out.add(String.valueOf(i+1));
				}
			}
		}

		return true;
	}

}
