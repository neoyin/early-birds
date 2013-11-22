package com.lifeix.d20131005;

import java.util.Random;

public class Zhch {
	public boolean validate(long number) {
		long odd = 0;
		long even = 0;
		int index = 1;
		while(number >= 10){
			long mod = number%10;
			number = number / 10;
			if(index%2 == 1){
				odd += mod;
			}else{
				mod *= 2;
				mod = mod > 9 ? mod - 9 : mod;
				even += mod;
			}
			index++;
		}
		if(index%2 == 1){
			odd += number;
		}else{
			even += number;
		}
		return (odd + even) % 10 == 0;
	}

	public static void main(String[] args) {
		Zhch t = new Zhch();
		Random r = new Random();
		for(int i = 0; i < 100 ; i++){
			long number = r.nextLong();
			number = number < 0 ? -number : number;
			boolean result = t.validate(number);
			System.out.println(number + " : " + (result ? "成功" : "失败"));
		}
		System.out.println(356827027232780L + " : " + (t.validate(356827027232780L) ? "成功" : "失败"));
	}
}
