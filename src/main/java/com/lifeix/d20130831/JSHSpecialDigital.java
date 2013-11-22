package com.lifeix.d20130831;

public class JSHSpecialDigital {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int i=11 ; i<=99 ; i++) {
			for(int j=i+1 ; j<=99 ; j++) {
				int value = i * j;
				if(value < 1000 || value > 9999) {
					continue;
				}
				StringBuffer val = new StringBuffer().append(value);
				String left = String.valueOf(i);
				String right = String.valueOf(j);
				int k = -1;
				if((k = val.indexOf(String.valueOf(left.charAt(0)))) == -1) {
					continue;
				}
				val.deleteCharAt(k);
				k = -1;
				if((k = val.indexOf(String.valueOf(left.charAt(1)))) == -1) {
					continue;
				}
				val.deleteCharAt(k);
				k = -1;
				if((k = val.indexOf(String.valueOf(right.charAt(0)))) == -1) {
					continue;
				}
				val.deleteCharAt(k);
				k = -1;
				if((k = val.indexOf(String.valueOf(right.charAt(1)))) == -1) {
					continue;
				}
				System.out.println(value + " = " + left + " * " +right);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
