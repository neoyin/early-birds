
package com.lifeix.d20130731;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*******************************************
 * @DESCRIPTION:	
 * @AUTHOR:fuqiangj
 * @DATE:2013-7-31 下午05:52:14
 *******************************************/
public class JFQ_Calculate {
	private int count = 0;
	public JFQ_Calculate() {
		calculate(10);
		calculate(20);
		calculate(50);
		calculate(1050);
		calculate(10050);
	}
	
	private void calculate(int n) {
			List<Integer> datas = new ArrayList<Integer>();		
			
			for (int i = 1; i <= n; i++) {			
				datas.add(i);		
			}		
			Iterator iterator = datas.listIterator();		
			while(datas.size() > 1){	
				count++;	
				if(!iterator.hasNext()){				
					iterator = datas.listIterator();			
				}		
				iterator.next();		
				if(count%3 == 0){				
					iterator.remove();		
				}		
			}		
			print(datas);
	}
	
	private void print(List<Integer> datas) {
		System.out.print(datas.get(0));
	}
	

	/**@description:	
	 * @author:fuqiangj
	 * @return:void
	 * @param args*/

	public static void main(String[] args) {
		new JFQ_Calculate();
		
	}

}

