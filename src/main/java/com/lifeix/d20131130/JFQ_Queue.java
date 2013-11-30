package com.lifeix.d20131130;

import java.util.Iterator;
import java.util.Stack;

public class JFQ_Queue implements TrainTest {

	@Override
	public boolean check(String mn, String trains) {
		long start = System.currentTimeMillis();
		if(null == mn || "".equals(mn) || null == trains || "".equals(trains)) {
			throw new NullPointerException();
		}
		String[] mns = mn.split(" ");
		String[] ts = trains.split(" ");
		int n = Integer.parseInt(mns[0]);
		int m = Integer.parseInt(mns[1]);
		if(m > 100000 || n > 100000 || m > n) {
			throw new IllegalArgumentException();
		} 
		int[] alist = new int[n];
		for(int i = 1; i <= n; i++) {
			alist[i-1] = i;
		}
		int[] tarray = new int[ts.length];
		int count = 0;
		for(String t : ts) {
			tarray[count++] = Integer.parseInt(t);
		}
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> b = new Stack<Integer>();
		
		int index = 0;
		boolean flag = false;
		for(int i = 0; i < tarray.length; i++) {
			int a = tarray[i];
			for(int j = index; j < alist.length; j++) {
				if(a != alist[j]) {
					if(stack.isEmpty()) {
						stack.push(alist[j]);
					} else {
						int ta = stack.peek();
						if(ta == a) {
							b.push(stack.pop());
							break;
						} else {
							if(!stack.contains(a)) {
								stack.push(alist[j]);
							} else {
								if(ta == a) {
									b.push(stack.pop());
								} else {
									flag = true;
									break;
								}
							}
						}
					}
					
				} else {
					b.push(alist[j]);
					index = j+1;
					break;
				}
			}
			if(flag || index >= alist.length) {
				break;
			}
		}
		Iterator<Integer> it = stack.iterator();
		while(it.hasNext()) {
			b.push(it.next());
		}
		long end = System.currentTimeMillis();
		System.out.println("time is " + (end - start) + " bstack = " + b.toString());
		return !flag;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(new JFQ_Queue().check("5 2", "1 3 2 4 5"));
		
	}

}
