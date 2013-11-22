package com.lifeix.d20131016;

import java.util.ArrayList;
import java.util.List;

/**
 * @DESCRIPTION:	
 * @DATE:2013-10-16 下午07:05:05
 **/
public class JFQ_encode implements IntEncoder {

	public byte[] encode(int src) {
		String asc = Integer.toBinaryString(src);
		StringBuffer sb = new StringBuffer(asc);
		sb.reverse();
		asc = sb.toString();
		List<String> list = new ArrayList<String>();
		int length = asc.length();
		int start = 0;
		for(int i = 0; i < length; i++) {
			if(i == length -1) {
				list.add(asc.substring(start));
				continue;
			}
			if((i + 1)%7 == 0) {
				list.add(asc.substring(start, i + 1));
				start = i + 1;
			}
		}
		StringBuffer sb1 = new StringBuffer();
		for(int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			if(i == 0 && length > 8) {
				str += "1";
			} else {
				int len = str.length();
				for(int j = len + 1; j <= 8; j++) {
					str += "0";
				}
			}
			sb1.append(str).append(",");
		}
		String target = sb1.reverse().substring(1);
		System.out.println("source: " + asc  + " \n" + target);
		return asc.getBytes();
	}

	public static void main(String[] args) {
		System.out.println(new JFQ_encode().encode(65535));
	}
}

