package com.lifeix.d20131102;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JFQ_Statics {
	
	private static Map<String, String> browser = new HashMap<String, String>(){{
		put("MSIE", "MSIE");put("Chrome", "Chrome");
		put("Safari", "Safari");put("QQBrowser", "QQBrowser");
		put("LBBROWSER","LBBROWSER");put("baidubrowser", "baidubrowser");
		put("Firefox", "Firefox");put("Opera", "Opera");put("360se", "360se");
		put("TencentTraveler", "Tencent");
	}};
	
	private static Map<String, String> os = new HashMap<String, String>(){{
		put("Windows", "Windows");put("iPhone", "IOS");
		put("Mac", "Mac");put("X11; Linux x86_64","Linux");
		put("Linux; U; Android", "Android");
	}};
	private static long total = 0;
	private void statics(String path) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
		String line = "";
		Map<String, Integer> ipMap = new HashMap<String, Integer>();
		Map<String, Integer> status = new HashMap<String, Integer>();
		Map<String, Integer> browserMap = new HashMap<String, Integer>();
		Map<String, Integer> osMap = new HashMap<String, Integer>();
		Map<String, Integer> singleIpMenu = new HashMap<String, Integer>();
		Map<String, Integer> menu = new HashMap<String, Integer>();
		while((line = br.readLine()) != null) {
			StringBuffer sb = new StringBuffer();
			for(String str : line.split("\"")) {
				sb.append(str).append("#");
			}
			//System.out.println(sb.toString());
			String[] strs = sb.toString().split("#");
			String info1 = strs[0];
			String info2 = strs[2].trim();
			String infoMenu = strs[1].trim();
			String info3 = strs[5].trim();
			
			String[] arr1 = info1.split(" ");
			//System.out.println(info3);
			if(ipMap.containsKey(arr1[0])) {
				ipMap.put(arr1[0], ipMap.get(arr1[0]) + 1);
			} else {
				ipMap.put(arr1[0],1);
			}
			
			if(infoMenu.length() > 1) {
				if(infoMenu.indexOf("?") > 0) {
					infoMenu = infoMenu.substring(infoMenu.indexOf(" /") + 1, infoMenu.indexOf("?"));
				} else {
					infoMenu = infoMenu.substring(infoMenu.indexOf(" /") + 1, infoMenu.indexOf("HTTP") - 1);
				}
				if(menu.containsKey(infoMenu)) {
					menu.put(infoMenu, menu.get(infoMenu));
				} else {
					menu.put(infoMenu, 1);
				}
				if(singleIpMenu.containsKey(infoMenu + "_" + arr1[0])) {
					singleIpMenu.put(infoMenu + "_" + arr1[0], singleIpMenu.get(infoMenu + "_" + arr1[0]) + 1);
				} else {
					singleIpMenu.put(infoMenu + "_" + arr1[0], 1);
				}
			}
			
			String[] arr2 = info2.split(" ");
			if(status.containsKey(arr2[0])) {
				status.put(arr2[0], status.get(arr2[0]) + 1);
			} else {
				status.put(arr2[0], 1);
			}
			if(info3.length() > 1) {
				for(Entry<String, String> entry : browser.entrySet()) {
					if(info3.indexOf(entry.getKey()) > 0) {
						//System.out.println(info3);
						if(browserMap.containsKey(entry.getKey())) {
							browserMap.put(entry.getKey(), browserMap.get(entry.getKey()) + 1);
						} else {
							browserMap.put(entry.getKey(), 1);
						}
						break;
					}
				}
				for(Entry<String, String> entry : os.entrySet()) {
					if(info3.indexOf(entry.getKey()) > 0) {
						//System.out.println("os=" + info3);
						if(osMap.containsKey(entry.getValue())) {
							osMap.put(entry.getValue(), osMap.get(entry.getValue()) + 1);
						} else {
							osMap.put(entry.getValue(), 1);
						}
						break;
					}
				}
			} 
		}
		long end = System.currentTimeMillis();
		total = end - start;
		System.out.println("统计出并打印出访问最多的ip排序========================");
		print(ipMap);
		System.out.println("统计出并打印出访问最多的浏览器排序====================");
		print(browserMap);
		System.out.println("统计出并打印出访问最多的操作系统排序=====================");
		print(osMap);
		System.out.println("统计出并打印出访问状态码(200,404等)排序==================");
		print(status);
		System.out.println("统计出并打印出访问最多的页面排序========================");
		print(menu);
		System.out.println("统计出并打印出独立ip访问最多的页面排序========================");
		print(singleIpMenu);
	}
	
	private void print(Map<String, Integer> map) {
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o1.getValue()).compareTo(o2.getValue());
		    }
		}); 
		for(Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + ", 次数= " + entry.getValue());
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		new JFQ_Statics().statics("/home/lifeix/access.www.l99.com_20131101.log");
		System.out.println(total + "ms");
	}

}
