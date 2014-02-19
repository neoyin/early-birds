package com.lifeix.d2014.0104csdnbing;

import java.util.ArrayList;
import java.util.List;


public class JFQ_Hui {

	private static List<String> results = new ArrayList<String>();
	private static String d = "";
	private static void hui(String data) {
		d = data;
		if("1".equals(data)) {
			results.add("1");
			return;
		} else if("2".equals(data)){
			results.add("11");
			return;
		}
		for(int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i);
			Integer g = Integer.parseInt(String.valueOf(ch));
			int split = g/2;
			String newData = "";
			if(i == 0) {
				newData = data.substring(i+1);
			} else if(i == data.length() - 1) {
				newData = data.substring(0,i);
			}else {
				newData = data.substring(0,i) + data.substring(i+1);
			}
			
			String tp = newData;
			processHui(pai(tp));
			if(results.size() < 1) {
				for(int j = 0; j < split; j++) {
					int extra = g - j - 1;
					int extraSplit = extra/2;
					//System.out.println("newData:" + newData + "," + split + "," + extraSplit);
					for(int k = 0; k < extraSplit; k++) {
						int extraMore = extra - k - 1;
						String temp = j+1+newData+extraSplit+""+extraMore;
						processHui(pai(temp));
					}
					
				}
			}
		}
	}
	
	
	
	private static char[] pai(String data) {
		char[] datas = data.toCharArray();
		return sort(datas);
	}
	
	private static char[] sort(char[] datas) {
	
        for( int i = 1;i < datas.length;i++) {
           char max = datas[i];
           int m = Integer.parseInt(String.valueOf(max));
           int j = i-1;
           while(j >= 0 && Integer.parseInt(String.valueOf(datas[j])) > m) {
        	   datas[j+1] = datas[j];
        	   j--;
           }
           datas[j+1] = max;
        }
       // System.out.println("sort:" + String.valueOf(datas));
        return datas;
	}
	
	private static void processHui(char[] data) {
		int length = data.length;
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		for(int i = 0; i < length; i++) {
			if((i + 1)%2 == 0) {
				sb2.append(data[i]);
			} else {
				sb1.append(data[i]);
			}
		}
		
		String d1 = "";
		if(length%2 !=0 ) {
			d1 = sb1.substring(0, sb1.length() - 1);
		} else {
			d1 = sb1.toString();
		}
		if(d1.equals(sb2.toString())) {
			System.out.println(d1 + ", " + sb2.toString());
			String d2 = sb2.reverse().toString();
			sb1.append(d2);
			if(!results.contains(sb1.toString())) {
			
				char[] r = sb1.toString().toCharArray();
				int sum = 0;
				int oldSum = 0;
				char[] r1 = d.toCharArray();
				for(int j = 0; j < r.length; j++) {
					sum += Integer.parseInt(String.valueOf(r[j]));
				}
				for(int k = 0; k < r1.length; k++) {
					oldSum += Integer.parseInt(String.valueOf(r1[k]));
				}
				if(sum == oldSum) {
					results.add(sb1.toString());
				}
				
			}
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hui("919");
		if(results.size() < 1) {
			System.out.println("impossiable");
		} else {
			System.out.println(results);
		}
	}

}
