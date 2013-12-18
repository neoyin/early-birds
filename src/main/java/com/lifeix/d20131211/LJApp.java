package com.lifeix.d20131211;

public class LJApp {

	public int calculate(int n){
		if(n <= 1)return 0;
		int one = 0;
		int two = 1;
		int temp = 0;
		for(int i=2;i<n;i++){
			temp = two;
			two += one;
			one = temp;
		}
		return two;
	}
	
	public int calculate(int one, int two, int now, int n){
		if(n <=1)return 0;
		if(n == 2)return 1;
		int result = 0;
		result = one + two;
		if(now < n){
			result = calculate(two, result, ++now, n);
		}
		return result;
	}
	
	public static void main(String[] args){
		LJApp app = new LJApp();
		System.out.println("直接算："+app.calculate(40));
		System.out.println("递归："+app.calculate(0,1,3,40));
	}
}
