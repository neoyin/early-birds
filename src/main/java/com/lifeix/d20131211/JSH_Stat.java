package com.lifeix.d20131211;

public class JSH_Stat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSH_Stat jsh = new JSH_Stat();
		System.out.println(jsh.getTotalCycle(40));
		System.out.println(jsh.getTotalRecursion(40));
	}
	/**
	 * Cycle efficiency higher than Recursion
	 * @param m
	 * @return
	 */
	private int getTotalCycle(int m) {
		if(m == 1) {
			return 0;
		}
		if(m == 2) {
			return 1;
		}
		int before = 0;
		int after = 1;
		int sum = 0;
		for(int i = 3; i <= m; i++) {
			sum = before + after;
			before = after;
			after = sum;
		}
		return sum;
	}
	/**
	 * Recursion
	 * @param m
	 * @return
	 */
	private int getTotalRecursion(int m) {
		if(m == 1) {
			return 0;
		}
		if(m == 2) {
			return 1;
		}
		int sum = getTotalRecursion(m-1) + getTotalRecursion(m-2);
		return sum;
	}
}
