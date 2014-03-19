package com.lifeix.d2014.m0319bignumber;

import java.util.Scanner;

public class LJApp {

	public String toString(int[] sum){
		StringBuilder builder = new StringBuilder();
		int j = sum.length-1;
		while(sum[j] <= 0){
			j--;
		}
		for(int i=j;i>=0;i--){
			builder.append(sum[i]);
		}
		return builder.toString();
	}
	public int[] toInt(String s){
		int[] x = new int[s.length()];
		char arr[]=s.toCharArray();
		int i=x.length-1;
		for(char c:arr){
			x[i] = c-'0';
			i--;
		}
		return x;
	}
	public int[] plus(int[] x, int[] y){
		int[] sum = new int[x.length > y.length ? x.length+1 : y.length+1];
		int up = 0;
		int i=0;
		while(i<x.length && i<y.length){
			sum[i] = up;
			int k = x[i] + y[i];
			up = k/10;
			sum[i] += k%10;
			i++;
		}
		if(up>0)sum[i] = up;
		return sum;
	}
	public int[] multiply(int[] x, int[] y){
		int[] sum = new int[x.length + y.length];
		int up = 0;
		for(int i=0;i<x.length;i++){
			for(int j=0;j<y.length;j++){
				int k = x[i]*y[j];
				sum[i+j] += k%10;
				if(k/10 > 0){
					plus(i+j+1,sum,k/10); 
				}
			}
		}
		return sum;
	}
	
	public void plus(int i, int[] x, int y){
		int k= x[i]+y;
		x[i] = k%10;
		if(k/10 > 0){
			x[i+1] += k/10;
		}
	}
	
	public static void main(String[] args){
		LJApp app = new LJApp();
		Scanner scaner = new Scanner(System.in);
		String x = scaner.nextLine();
		String y = scaner.nextLine();
		System.out.println(x+"+"+y+"="+app.toString(app.plus(app.toInt(x), app.toInt(y))));
		System.out.println(x+"*"+y+"="+app.toString(app.multiply(app.toInt(x), app.toInt(y))));
	}
	
}
