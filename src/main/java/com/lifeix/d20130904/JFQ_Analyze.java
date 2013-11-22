package com.lifeix.d20130904;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @DESCRIPTION:	
 * @DATE:2013-9-4 下午05:33:08
 **/
public class JFQ_Analyze implements AnagramAnalyzer {

	public List<String> analyze(List<String> words) {
		List<String> results = new ArrayList<String>();
		Iterator<String> it = words.iterator();
		StringBuffer sbResult = new StringBuffer();
		while(it.hasNext()) {
			String temp = it.next();
			if(sbResult.indexOf(temp) >= 0) {
				continue;
			} else {
				sbResult = new StringBuffer();
			}
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> its = words.iterator();
			sbResult.append(temp).append("|");
			while(its.hasNext()) {
				String str = its.next();
				if(str.equals(temp) || (str.length() != temp.length())) {
					continue;
				} else {
					char[] chs = str.toCharArray();
					char[] temps = temp.toCharArray();
					for(int i = 0; i < temps.length; i++) {
						StringBuffer sb = new StringBuffer();
						sb.append(chs[i]);
						for(int j = 0; j < chs.length; j++) {
							if(temp.indexOf(temps[i]) == str.indexOf(chs[j])) {
								continue;
							} else {
								sb.append(chs[j]);
							}
						}
						map.put(sb.toString(), temp);
					}
					System.out.println(str + ", " + temp);
					if(map.containsKey(str) && map.containsValue(temp)) {
						sbResult.append(str).append("|");
					} 
				}
			}
			if(sbResult.length() == (sbResult.indexOf("|") + 1)) {
				continue;
			}
			results.add(sbResult.substring(0, sbResult.length() - 1));
		}
		return results;
	}

	public static void main(String[] args) {
		JFQ_Analyze jfq = new JFQ_Analyze();
		List<String> results = jfq.analyze(new ArrayList(){{add("ports");add("sport");add("rpots");add("abc");add("cba");add("port");}});
		System.out.println(results);
	}
}

