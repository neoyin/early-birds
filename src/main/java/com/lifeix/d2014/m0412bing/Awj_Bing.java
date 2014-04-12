package com.lifeix.d2014.m0412bing;

public class Awj_Bing {
	
	public static void main(String[] args) {
		String bing = "iinbinbing";
		System.out.println(bingCount(bing));
	}
	
	public static int bingCount(String bing){
		int count = 0;
		for(int i = 0;i<bing.length();i++){
			if(bing.substring(i, i+1).equals("b")){
				for (int j = i+1; j < bing.length(); j++) {
					if(bing.substring(j, j+1).equals("i")){
						for (int k = j+1; k < bing.length(); k++) {
							if(bing.substring(k, k+1).equals("n")){
								for (int l = k+1; l < bing.length(); l++) {
									if(bing.substring(l, l+1).equals("g"))
										count++;
								}
							}
						}
					}
				}
			}
		}
		return count;
	}

}
