package com.lifeix.d20131113;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JFQ_House {

	private int house(int n, int[][] data) {
		int count = 0;
		for(int i = 0; i < data.length; i++) {
			int[] t = data[i];
			int x = t[0];
			int y = t[1];
			for(int j = 0; j < data.length; j++) {
				int[] t1 = data[j];
				if(x > t1[0] && y > t1[1]) {
					System.out.println("x=" + x+", y=" + y + ", " + i + ", " + t1[0] + ", " + t1[1]);
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int sum = 10;
		int[][] a = new int[sum][2];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				a[i][j] = random.nextInt(100);
			}
		}
//		a[0][0] = 2;
//		a[0][1] = 2;
//		a[1][0] = 4;
//		a[1][1] = 3;
//		a[2][0] = 5;
//		a[2][1] = 1;
		System.out.println(new JFQ_House().house(sum, a));
	}

}
