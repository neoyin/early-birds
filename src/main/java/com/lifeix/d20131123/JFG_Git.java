package com.lifeix.d20131123;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JFG_Git {

	public void print() throws IOException {
		String cmd = "git diff";
		Process p = Runtime.getRuntime().exec(cmd);
		InputStream is = p.getInputStream();
		byte[] buffer = new byte[1024];
		StringBuffer sb = new StringBuffer();
		while(is.read(buffer) > -1) {
			sb.append(new String(buffer));
		}
		String[] lines = sb.toString().split("\n");
		int count = 0;
		Map<String, String> reduceMap = new HashMap<String, String>(lines.length);
		Map<String, String> addMap = new HashMap<String, String>(lines.length);
		String name1 = "";
		String name2 = "";
		for(String line : lines) {
			if(line.startsWith("-")) {
				if(line.startsWith("---")) {
					name1 = line.substring(line.indexOf("---") + 4);
				} else {
					reduceMap.put(name1, line);
				}
			} else if(line.startsWith("+")) {
				if(line.startsWith("+++")) {
					name2 = line.substring(line.indexOf("+++") + 4);
				} else {
					addMap.put(name2, line);
				}
			}
		}
		printMsg(reduceMap, "删除了");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		printMsg(addMap, "增加");
	}
	
	private void printMsg(Map<String, String> data,String flag) {
		for(Entry<String, String> entry : data.entrySet()) {
			System.out.println("文件" + entry.getKey() + " " + flag + " " + entry.getValue());
		}
	}
	
	public static void main(String[] args) throws IOException {
		new JFG_Git().print();
	}

}
