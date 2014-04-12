package com.lifeix.d2014.m0412bing;


public class JFQ_Number {
	private static String str = "iinbinbing";
	public static void main(String[] args) {
		str = str.substring(str.indexOf("b"));
		str = str.substring(0,str.lastIndexOf("g") + 1);
		int count = 0;
		while(str.indexOf("b") >= 0) {
			char[] chs = str.toCharArray();
			for(int i = 1; i < chs.length; i++) {
				if("i".equals(String.valueOf(chs[i]))) {
					for(int j = i + 1; j< chs.length; j++) {
						if("n".equals(String.valueOf(chs[j]))) {
							for(int k = j + 1; k < chs.length; k++) {
								if("g".equals(String.valueOf(chs[k]))) {
									count++;
								}
							}
						}
					}
				}
			}
			str = str.substring(1);
			if(str.indexOf("b") > 0) {
				str = str.substring(str.indexOf("b"));
			}
		}
		System.out.println("count="  + count);
	}

}
