package com.lifeix.d20131130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AWJ_Train implements TrainTest{

	@Override
	public boolean check(String mn, String trains) {
		String [] array = mn.split(" ");
		int n = Integer.valueOf(array[0]);//列车数量
		int m = Integer.valueOf(array[1]);//中转站车位
		String [] order = trains.split(" ");
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

	public static void main(String[] args) {
		System.out.println(new AWJ_Train().check("4 3", "1 4 2 3"));
		
	}


}
