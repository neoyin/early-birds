package com.lifeix.d20131016;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class JSH_IntEncoder implements IntEncoder {

	private static final int BASE = 128;
	private static final int BASE_LENGTH = 7;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int number = sc.nextInt();
		JSH_IntEncoder jsh = new JSH_IntEncoder();
		byte[] b = jsh.encode(number);
		System.out.println(new String(b));
	}
	
	@Override
	public byte[] encode(int number) {
		// TODO Auto-generated method stub
		String binNum = encodeString(number);
		byte[] b = binNum.getBytes();
		return b;
	}
	private String encodeString(int number) {
		String str = "";
		while(number != 0) {
			int n = number % BASE;
			String later = getLater(n);
			if(number < 128) {
				str = "0" + later + " " + str;
				break;
			}else {
				str = "1" + later + " " + str;
			}
			number /= BASE;
		}
		return str;
	}
	private String getLater(int n) {
		StringBuffer num = new StringBuffer();
		String later = Integer.toBinaryString(n);
		int length = later.length();
		int supply = BASE_LENGTH - length;
		while(supply != 0) {
			supply--;
			num.append("0");
		}
		num.append(later);
		return num.toString();
	}
}
