package com.lifeix.d20131130;

import java.util.Stack;

public class LJApp implements TrainTest{

	@Override
	public boolean check(String mn, String trains) {
		String[] MN = mn.split(" ");
		String[] OUT = trains.split(" ");
		if(MN.length < 2)return false;
		int m = Integer.parseInt(MN[1]);
		int n = Integer.parseInt(MN[0]);
		if(OUT.length != n)return false;
		String[] IN = new String[n];
		for(int i=0;i<OUT.length;i++){
			IN[i] = String.valueOf(i+1);
		}
		Stack<String> stack = new Stack<String>();
		int in = 0;
		int out = 0;
		while(stack.size() <= m){
			if(stack.empty()){
				if(in < IN.length){	
					stack.push(IN[in]);
					in ++;
				}else{
					break;
				}
			}else{
				if(stack.peek().equals(OUT[out])){
					stack.pop();
					out ++;
				}else{
					if(in < IN.length){
						stack.push(IN[in]);
						in ++;
					}else{
						break;
					}
				}
			}
		}
		if(out == in && in == n){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		String mn = "5 2";
		String trains = "1 2 3 5 4";
		TrainTest test = new LJApp();
		System.out.println(test.check(mn, trains));
	}
	
}
