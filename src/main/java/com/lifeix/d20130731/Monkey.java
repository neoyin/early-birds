package com.lifeix.d20130731;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Monkey {
	public static int voteKing(int count){
		if(count < 1){
			return 0;
		}
		
		// 初始化数据
		List<Integer> list = new ArrayList<Integer>();
		for(int num = 0;num<count;num++){
			list.add(num+1); 
		} 
		
		// 计数器
		int index = 1;
		Iterator<Integer> it = list.iterator();
		while(true){
			// 如果数完一遍再从头来
			if(!it.hasNext()){
				it = list.iterator();
			}
			int num = it.next();
			
			// 数到3的出局
			if(index++%3 == 0 && list.size()>1){
//				System.out.println(num + " out");
				it.remove();
			}
			
			// 剩下一个当大王
			if(list.size() == 1){
				break;
			}
		}
		return list.get(0);
	}
	
	public static void main(String[] args) {
		int[] testData = new int[]{10,20,50,1050,10050}; // 420117542842
//		int[] testData = new int[]{5};
		for(int i : testData){
//			int king1 = voteKing(i);
//			System.out.println(i + "只猴子" + king1 + "当大王.");
			int king = chooseKing(i);
			System.out.println(i + "只猴子" + king + "当大王.");
		}
	}
	
	public static int chooseKing(int count){
		int[] monkeys = new int[count];
		int out = 0;
		int num = 1;
		while(true){
			for(int i = 0;i< monkeys.length;i++){
				if(monkeys[i] == 1){
					continue;
				}
				if(num%3 == 0){
					monkeys[i] = 1;
					out++;
//					System.out.println("out:" + (i+1) + " out:" + out);
				}
				num++;
			}
//			System.out.println("num : " + num);
//			print(monkeys);
			if(out + 1 == monkeys.length)
			break;
		}
		
		int index = 0;
		for(int monkey : monkeys){
			if(monkey == 0){
				return index + 1;
			}
			index++;
		}
		return 0;
	}
	
	public static void print(int[] arr){
		for(int i : arr){
			System.out.print(i+",");
		}
		System.out.println();
	}
}
