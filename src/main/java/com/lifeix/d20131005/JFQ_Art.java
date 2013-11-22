package com.lifeix.d20131005;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JFQ_Art {

	private Map<Integer,List<Integer>> source = null;
	private void validate(int n,int m) {
		source = new HashMap<Integer,List<Integer>>();
		Random random = new Random();
		for(int i = 0; i < n; i++) {
			List<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < m; j++) {
				int z = random.nextInt(10);
				list.add(z);
			}
			Collections.reverse(list);
			source.put(i, list);
		}
		sum();
	}
	
	
	
	private void sum() {
		int size = source.size();
		for(int i = 0; i < size; i++) {
			int sum1 = 0;
			int sum2 = 0;
			List<Integer> list = source.get(i);
			for(int j = 0; j < list.size(); j++) {
				int tmp = list.get(j);
				if((j + 1)%2 == 0) {
					if(tmp*2 >= 10) {
						sum1 += tmp*2 - 9;
					} else {
						sum1 += tmp*2;
					}
				} else {
					sum2 += tmp;
				}
			}
			System.out.print("\n第 "+(i+1)+" 个卡号 o="+sum1+",q="+sum2 + ", " + list.toString());
			int sum = sum1 + sum2;
			if(sum%10 == 0) {
				System.out.print("   【成功】");
			} else {
				System.out.print("   失败");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		new JFQ_Art().validate(100,15);
	}

}

