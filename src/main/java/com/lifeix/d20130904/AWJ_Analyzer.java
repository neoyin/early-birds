package com.lifeix.d20130904;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AWJ_Analyzer {
	
	public static void main(String[] args) {
		List<String> str = new ArrayList<String>();
		str.add("abc");
		str.add("bac");
		str.add("cba");
		str.add("efg");
		str.add("gef");
		str.add("awj");
		List<String> result = returnResult(str);
		for(String s : result){
			System.out.println(s);
		}
	}


	/**
	 * 判断两个字符串是否是变位词
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean sure(String str1, String str2) {		
		char[] arr1 = str1.toCharArray();		
		char[] arr2 = str2.toCharArray();		
		Arrays.sort(arr1);		
		Arrays.sort(arr2);		
		for(int i = 0; i < arr1.length; i++) {			
			if(arr1[i] != arr2[i]) {				
				return false;			
			}		
		}	
		return true;
	}

	/**
	 * 输出结果
	 * @param list
	 * @return
	 */
	public static List<String> returnResult(List<String> list){
		List<String> result = new ArrayList<String>();
		Map<String, Integer> maps = new HashMap<String, Integer>();
		for(int i=0; i<list.size(); i++){
			//过滤
			if(maps.containsKey(list.get(i))) continue;
			StringBuffer sb = new StringBuffer();
			for(int j=0; j<list.size(); j++){
				if(i==j)continue;
				if(sure(list.get(i), list.get(j))){
					maps.put(list.get(j), 1);
					if(sb.length() == 0){
						sb.append(list.get(i)+"|"+list.get(j));
					}else{
						sb.append("|"+list.get(j));
					}
				}
			}
			if(!sb.equals("")){
				result.add(sb.toString());
			}
		}
		return result;
	}
}
