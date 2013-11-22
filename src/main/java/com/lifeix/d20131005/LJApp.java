package com.lifeix.d20131005;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LJApp {

	public static int number = 100;
	public static int length = 16;
	
	public static void main(String[] args){
		Set<String> cards = makeCards();
		for(String s : cards){
			check(s);
		}
	}
	
	public static Set<String> makeCards(){
		Set<String> cards = new HashSet<String>();;
		Random random = new Random();
		while(cards.size() < number){
			StringBuilder builder = new StringBuilder();
			int k = 0;
			while(true){	//第一位不能是0
				if((k = random.nextInt(10) )!= 0){
					builder.append(k);
					break;
				}
			}
			for(int j=1;j<length;j++){
				builder.append(random.nextInt(10));
			}
			cards.add(builder.toString());
		}
		return cards;
	}
	
	public static void check(String s){
		char[] c = s.toCharArray();
		int count = 0;
		int k = 0;
		for(int i=c.length-1;i>=0;i--){
			k++;
			int n = c[i]-'0';
			if(k%2 == 0){
				n = 2*n;
				count += n > 9 ? n - 9 : n;
			}else{
				count += n;
			}
		}
		StringBuilder builder = new StringBuilder();
		builder.append(s).append(":");
		if(count%10 == 0){
			builder.append("成功");
		}else{
			builder.append("失败");
		}
		System.out.println(builder.toString());
	}
}
