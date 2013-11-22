package com.lifeix.d20131113;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class JSH_LightHouse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSH_LightHouse lhouse = new JSH_LightHouse();
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int k = sc.nextInt();
		int[][] lh = new int[k][2];
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < 2; j++) {
				lh[i][j] = sc.nextInt();
			}
		}
		int count = lhouse.getBrace(lh);
		System.out.println("The count is : " + count);
	}
	private int getBrace(int[][] lh) {
		int count = 0;
		for(int i = 0; i < lh.length-1; i++) {
			for(int j = i+1; j < lh.length; j++) {
				if(lh[i][0] < lh[j][0] && lh[i][1] < lh[j][1] ||
						lh[i][0] > lh[j][0] && lh[i][1] > lh[j][1]) {
					count++;
				}
			}
		}
		return count;
	}
	
}
