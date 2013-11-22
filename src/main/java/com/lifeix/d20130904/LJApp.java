package com.lifeix.d20130904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LJApp{
	
	public static List<String> words;
	
	private static void init(){
		words = new ArrayList<String>();
		words.add("pots");
		words.add("abc");
		words.add("stop");
		words.add("def");
		words.add("cba");
		words.add("must");
		words.add("de");
	}
	
	public static void main(String[] args){
		init();	
		AnagramAnalyzer anagramAnalyzer = new Analyzer();
		List<String> same = anagramAnalyzer.analyze(words);
		if(same != null && same.size() > 0){
			for(int i=0;i<same.size();i++){
				System.out.println(same.get(i));
			}
		}
	}
	
}

class Analyzer implements AnagramAnalyzer {
	private final static String sp ="|";
	public List<String> analyze(List<String> words){
		List<String> same = new ArrayList<String>();
		
		List<char[]> word = new ArrayList<char[]>();
		for(int i=0;i<words.size();i++){
			char[] arr = words.get(i).toCharArray();
			Arrays.sort(arr);
			word.add(arr);
		}
		while(words.size() > 1){
			StringBuilder str = new StringBuilder();
			String s = words.get(0);
			str.append(s);
			char[] arr = word.get(0);
			for(int i=1;i<words.size();i++){
				char[] arr2 = word.get(i);
				boolean equals = true;
				for(int j=0;j<arr.length;j++){
					try{
						if(arr[j] != arr2[j]){
							equals = false;
							break;
						}
					}catch(Exception e){
						equals = false;
						break;
					}
				}
				if(equals){
					str.append(sp).append(words.get(i));
					words.remove(i);
					word.remove(i);
					i--;
				}
			}
			words.remove(0);
			word.remove(0);
			if(str.toString().indexOf(sp)>0){
				same.add(str.toString());
			}
		}
		
		return same;
	}
}
