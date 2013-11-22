package com.lifeix.d20130904;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZDL_Analyzer implements AnagramAnalyzer{

	@Override
	public List<String> analyze(List<String> words) {
		/**
		 * 遍历list
		 * a0      b1  2  3  4   6 words.length-1
		 * abtrue     将这后边的下标存到list中      false				String st="[pots,abc,stop,def,cba,must,de]";
		 * */
		ArrayList<String> newWords= new ArrayList<String>();
		ArrayList<String> aWords= new ArrayList<String>();//单词数组
		for(int item=0;item<words.size()-1;item++){
			String oneWord = words.get(item);
			if(aWords.contains(oneWord)){//如果该字符已是变位词，直接跳过
				continue;
			}
			boolean flag = true;
			String wordstr = "";
			for(int twoWords=item+1;twoWords<words.size();twoWords++){
				String twoWord = words.get(twoWords);
				if(wordCompare(oneWord,twoWord)){
					if(flag){//只有第一次时把第一个=单词加入
						wordstr += oneWord;
						aWords.add(oneWord);
						flag=false;
					}
					wordstr = wordstr+"|"+twoWord;
					aWords.add(twoWord);
				}
			}
			if(!wordstr.equals("")){
				newWords.add(wordstr);
			}
		}
		return newWords;
	}

	public boolean wordCompare(String worda,String wordb) {//判断两个字符串是否为变位词
		boolean flag=false;
		if(worda.length()==wordb.length()){
			for(int index = 0;index<worda.length();index++){
				if(wordb.indexOf(worda.charAt(index))==-1){
					flag=false;
					break;
				}else{
					flag=true;
				}
			}
		}
		return flag;

	}

	public static void main(String[] args) {
		String st="[pots,abc,stop,def,cba,must,de]";
		ArrayList<String> words= new ArrayList<String>();
		String sta [] = st.substring(1, st.length()-1).split(",");
		for(String stb:sta)
			words.add(stb);
		ZDL_Analyzer analyzer=new ZDL_Analyzer();
		List<String> newWords= analyzer.analyze(words);
		System.out.println(newWords);
	}

}
