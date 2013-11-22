package com.lifeix.d20130914;

import java.util.List;


public class LJTest {
	public static void main(String[] args) {
		List<String> str = new LJApp<String>();
		String s = "aaa|bbb|ccc|ddd|eee|fff|ggg|hhh|iii|jjj|kkk|lll|mmm|nnn";
		String[] ss = s.split("\\|");
		for(int i=0;i<ss.length;i++){
			str.add(ss[i]);
		}
		print(str,"初始:");
		str.remove(0);
		print(str,"删除下标为0的元素:");
		str.add(0,"aaa");
		print(str, "在0添加'aaa':");
		System.out.println("是否存在123:"+str.contains("123"));
		System.out.println("是否存在bbb:"+str.contains("bbb"));
		System.out.println("ggg的位置:"+str.indexOf("ggg"));
		System.out.println("去下标为5的元素:"+str.get(5));
//		String[] tt = null;
//		String[] sss = str.toArray(tt);
//		System.out.println("转换数组：");
//		for(int i=0;i<sss.length;i++)System.out.print(sss[i]);
		System.out.println("是否为空："+str.isEmpty());
		str.remove("mmm");
		print(str,"去掉mmm:");
		List<String> newL = new LJApp<String>();
		newL.add("sdfdjskflsd");
		newL.add("iueur23");
//		str.addAll(newL);
//		print(str,"添加所有:");
		str.clear();
		print(str, "清空:");
		
		
	}


	public static void print(List<String> str, String s){
		System.out.print(s);
		if(str.size() <= 0)System.out.print("size 0");
		for (int i = 0; i < str.size(); i++) {
			System.out.print(str.get(i)+"|");
		}
		System.out.println();
		System.out.println("size:"+str.size());
	}
	
	
}
