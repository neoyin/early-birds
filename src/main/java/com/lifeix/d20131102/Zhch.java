package com.lifeix.d20131102;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lifeix.Util;
import com.lifeix.tool.useragent.UserAgent;

public class Zhch {
	Map<String, Integer> browsers = new HashMap<String, Integer>();
	Map<String, Integer> oses = new HashMap<String, Integer>();
	Map<String, Integer> ips = new HashMap<String, Integer>();
	Map<String, Integer> pages = new HashMap<String, Integer>();
	Map<String, Integer> statuses = new HashMap<String, Integer>();
	Map<String, Set<String>> pageIps = new HashMap<String, Set<String>>();
	Pattern p = Pattern.compile("(.*?) .*? - .* \".*? (.*?)\" (\\d+) .*? \".*?\" \"(.*?)\" .*");

	int errorCount = 0;

	public void test() throws Exception {
		long t1 = System.currentTimeMillis();
		File file = new File("/home/lifeix/temp/d3/access.www.l99.com_20131101.log");
		FileReader in = new FileReader(file);
		BufferedReader br = new BufferedReader(in);
		String line = br.readLine();
		int lineNum = 0;
		while (line != null) {
			lineNum++;

			Matcher m = p.matcher(line);
			if (m.find()) {
				String ip = m.group(1);
				String url = m.group(2);
				String status = m.group(3);
				String userAgent = m.group(4);
				UserAgent agent = new UserAgent(userAgent);
				setCount(browsers, agent.getBrowser().toString());
				setCount(oses, agent.getOperatingSystem().toString());
				setCount(ips, ip);
				setCount(pages, url);
				setCount(statuses, status);
				setPageIp(pageIps, url, ip);
				// System.out.println(ip + "   " + url + "   "+ status + "   " +
				// agent.getBrowser() + "    " + agent.getOperatingSystem());
			} else {
				errorCount++;
				System.out.println("error:   " + line);
			}

			line = br.readLine();
//			 if(lineNum > 1000){break;}
			if (lineNum % 5000 == 0) {
				System.out.println("lines:" + lineNum);
			}
		}
		printResult();
		long t2 = System.currentTimeMillis();
		System.out.println("time:" + (t2 - t1));
		System.out.println("errorCount:" + errorCount);
	}

	/**
	 * 结果排序打印
	 */
	private void printResult() {
		sort(browsers, "浏览器");
		sort(oses, "操作系统");
		sort(ips, "IP");
		sort(pages, "页面");
		sort(statuses, "状态");

	}

	void sort(Map<String, Integer> map, String name) {
		List<String> result = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			result.add(Util.fillString(Util.preFillString(entry.getValue().toString(), 10), 20) + entry.getKey());
		}
		Collections.sort(result);

		int count = result.size() > 10 ? 10 : result.size();
		System.out.println(name + "排名:");
		int total = result.size();
		for (int i = 0; i < count; i++) {
			System.out.println(result.get(total - 1 - i));
		}
	}

	/**
	 * 页面IP统计
	 * 
	 * @param map
	 * @param url
	 * @param ip
	 */
	private void setPageIp(Map<String, Set<String>> map, String url, String ip) {
		Set<String> set = map.get(url);
		if (set == null) {
			set = new HashSet<String>();
		}
		set.add(ip);
		map.put(url, set);
	}

	/**
	 * 计数
	 * 
	 * @param map
	 * @param key
	 */
	private void setCount(Map<String, Integer> map, String key) {
		Integer count = map.get(key);
		if (count == null) {
			count = 0;
		}
		map.put(key, count + 1);
	}

	public static void main(String[] args) throws Exception {
		Zhch t = new Zhch();
		t.test();
	}
}
