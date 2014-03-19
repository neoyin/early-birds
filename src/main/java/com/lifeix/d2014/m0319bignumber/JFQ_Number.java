package com.lifeix.d2014.m0319bignumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JFQ_Number {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("I'm sorry, you have to input a number:");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String str1 = "";
		try {
			str1 = br1.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if("".equals(str1)) {
			System.out.println("Do You want to kill me? The params have to be not empty!");
			System.exit(0);
		}
		System.out.println("I'm sorry, you have to input a number again:");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		
		String str2 = "";
		try {
			str2 = br2.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if("".equals(str2)) {
			System.out.println("Do You want to kill me? The params have to be not empty!");
			System.exit(0);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		int length1 = ch1.length;
		int length2 = ch2.length;
		for(int i = 0; i < length1; i++) {
			StringBuffer zero1 = new StringBuffer();
			for(int k = 0; k < (length1 - 1 - i); k++) {
				zero1.append("0");
			}
			int n1 = Integer.valueOf(String.valueOf(ch1[i]));
			for(int j = 0; j < ch2.length; j++) {
				int n2 = Integer.valueOf(String.valueOf(ch2[j]));
				StringBuffer zero2 = new StringBuffer();
				for(int k2 = 0; k2 < (length2 - 1 - j); k2++) {
					zero2.append("0");
				}
				//System.out.println(n1 + ", " + n2 + ", " + zero1.toString() + ", " + zero2.toString());
				map.put((length1 - 1 - i) + "_" + (length2  -1 - j), n1*n2+zero2.append(zero1).toString());
			}
		}
		StringBuffer sb = new StringBuffer();
		for(Entry<String, String> entry : map.entrySet()) {
			String value = entry.getValue();
			if(sb.length() > 0) {
				char[] chh1 = sb.toString().toCharArray();
				char[] chh2 = value.toCharArray();
				StringBuffer temp = new StringBuffer();
				int tempCount = 0;
				if(chh1.length >= chh2.length) {
					for(int i = chh2.length - 1; i >= 0; i--) {  //旧值
						char c2 = chh2[i];
						int nn2 = Integer.valueOf(String.valueOf(c2));
						char c1 = chh1[i + chh1.length - chh2.length];
						int nn1 = Integer.valueOf(String.valueOf(c1));
						int count = nn1 + nn2 + tempCount;
						if(count >= 10) {
							tempCount = 1;
							count = count - 10;
						} else {
							tempCount = 0;
						}
						temp.append(count);
						if(i == 0) {
							for(int j = (chh1.length - chh2.length - 1); j >= 0; j--) {
								int tt = Integer.valueOf(String.valueOf(chh1[j]));
								int ct = tt + tempCount;
								if(ct >= 10) {
									ct = ct - 10;
									tempCount = 1;
								} else {
									tempCount = 0;
								}
								temp.append(ct);
								if(j == 0 && tempCount != 0) {
									temp.append(tempCount);
								}
							}
						}
					}
					sb = temp.reverse();
				} else {
					for(int i = chh1.length - 1; i >= 0; i--) {  
						char c1 = chh1[i];
						int nn1 = Integer.valueOf(String.valueOf(c1));
						char c2 = chh2[i + chh2.length - chh1.length];
						int nn2 = Integer.valueOf(String.valueOf(c2));
						int count = nn1 + nn2 + tempCount;
						if(count >= 10) {
							tempCount = 1;
							count = count - 10;
						} else {
							tempCount = 0;
						}
						temp.append(count);
						
						if(i == 0) {
							for(int j = (chh2.length - chh1.length - 1); j >= 0; j--) {
								int tt = Integer.valueOf(String.valueOf(chh2[j]));
								int ct = tt + tempCount;
								if(ct >= 10) {
									ct = ct - 10;
									tempCount = 1;
								} else {
									tempCount = 0;
								}
								temp.append(ct);
								if(j == 0 && tempCount != 0) {
									temp.append(tempCount);
								}
							}
						}
					}
					sb = temp.reverse();
				}
			} else {
				sb.append(value);
			}
		}
		
		System.out.println(sb.toString() + ", " + map);

	}

}
