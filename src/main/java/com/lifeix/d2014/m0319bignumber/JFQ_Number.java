package com.lifeix.d2014.m0319bignumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class JFQ_Number {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("I'm sorry, you have to input a number:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String str1 = "";
		try {
			str1 = br1.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if("".equals(str1)) {
			System.out.println("Do You want to kill me? The params have to be not empty!");
			System.exit(0);
		}
		System.out.println("I'm sorry, you have to input a number again:");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		
		String str2 = "";
		try {
			str2 = br2.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if("".equals(str2)) {
			System.out.println("Do You want to kill me? The params have to be not empty!");
			System.exit(0);
		}
		BigDecimal bd1 = new BigDecimal(str1);
		BigDecimal bd2 = new BigDecimal(str2);
		BigDecimal bd = bd1.multiply(bd2);
		System.out.println(bd.toString());

	}

}
