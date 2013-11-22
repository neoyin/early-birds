package com.lifeix.d20130731;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JFQ_ErrorMonkey {
	

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
			// 数完一遍再从头来
			if(!it.hasNext()){
				it = list.iterator();
			}
			Integer num = it.next();
			
			// 数到3的出局
			if(index%3 == 0 && list.size()>1){
//				System.out.println(num + " out");
				//list.remove(num);
				it.remove();
			}
			index++;
			
			// 剩下一个当大王
			if(list.size() == 1){
				break;
			}
		}
		return list.get(0);
	}
	
	public static void main(String[] args) {
		int[] testData = new int[]{10,20,50,1050,10050};
		for(int i : testData){
			int king = voteKing(i);
			System.out.println(i + "只猴子" + king + "当大王.");
		}
	}
}
