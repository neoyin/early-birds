package com.lifeix.d20130731;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 猴子选大王
 * @author ANWJ
 *
 */
public class AWJ_MonkeyChooseKing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [] monkeyCount = {10,20,50,1050,10050};
		for(int i=0;i<monkeyCount.length;i++){
			List<Integer> monkeys = new ArrayList<Integer>();		
			// 猴子数量	
			int count = monkeyCount[i];		
			// 计数器	
			int number = 0;		
			// 为猴子编号		
			for (int j = 0; j < count; j++) {			
				monkeys.add(new Integer(j+1));		
			}		
			Iterator iterator = monkeys.listIterator();		
			while(monkeys.size() > 1){	
				number ++;	
				if(!iterator.hasNext()){				
					iterator = monkeys.listIterator();			
				}		
				iterator.next();		
				if(number%3 == 0){				
					iterator.remove();		
				}		
			}		
			System.out.println("恭喜" + monkeys.get(0) + "号猴子从["+count+"]个猴子中被选为猴王");	
		}
	}

}
