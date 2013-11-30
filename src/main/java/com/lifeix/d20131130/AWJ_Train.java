package com.lifeix.d20131130;

import java.util.ArrayList;
import java.util.List;

public class AWJ_Train implements TrainTest{

	@Override
	public boolean check(String mn, String trains) {
		String [] array = mn.split(" ");
		int n = Integer.valueOf(array[0]);//列车数量
		int m = Integer.valueOf(array[1]);//中转站车位
		String [] order = trains.split(" ");
		//List<String> list =  Arrays.asList(order);

		//调度站车位数组
		List<String> schedule = new ArrayList<String>();
		//记录已出站列车
		List<String> out = new ArrayList<String>();
		if(Integer.valueOf(order[0]) > m+1){
			return false;
		}else{
			for(int i=0; i<n; i++){
				if(Integer.valueOf(order[i]) != i+1){
					if(schedule.contains(order[i])){
						schedule.remove(order[i]);
						out.add(order[i]);
						continue;
					}
					for(int j=Integer.valueOf(order[i])-1; j>0;j--){
						//如果调度前面的列车没在调度站或没被开出，则将列车停在调度站
						if(!schedule.contains(j) && !out.contains(String.valueOf(j))){
							if(schedule.size() > m){
								return false;
							}else{
								if(!schedule.contains(String.valueOf(j))){
									schedule.add(String.valueOf(j));
								}
							}
						}
					}
					out.add(order[i]);

				}else{
					out.add(order[i]);
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(new AWJ_Train().check("5 2", "3 1 4 5 2"));

	}


}
