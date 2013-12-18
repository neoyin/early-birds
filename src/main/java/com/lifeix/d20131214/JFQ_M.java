package com.lifeix.d20131214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JFQ_M {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("please input n m:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nm = br.readLine();
		if(null == nm || "".equals(nm)) {
			throw new IllegalArgumentException("you have to input n m~");
		}
		//br.close();
		int n = Integer.parseInt(nm.split(" ")[0]);
		int m = Integer.parseInt(nm.split(" ")[1]);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < m; i++) {
			System.out.println("请输入两个镇子:");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
			String line = br1.readLine();
			if(null == line || "".equals(line)) {
				System.out.println("镇子不能为空，请重新输入....");
			//	br1.close();
				BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
				line = br2.readLine();
				//br2.close();
			}
			list.add(line);
		}
		br.close();
		boolean flag = false;
		Map<String, List<String>> map = new HashMap<String, List<String>>(n);
		for(String line : list) {
			String[] datas = line.split(" ");
			if(map.containsKey(datas[0])) {
				List<String> value = map.get(datas[0]);
				if(!value.contains(datas[1])) {
					value.add(datas[1]);
				}
				map.put(datas[0], value);
			} else {
				List<String> value = new ArrayList<String>();
				value.add(datas[1]);
				map.put(datas[0], value);
			}
			if(map.containsKey(datas[1])) {
				List<String> value = map.get(datas[1]);
				if(!value.contains(datas[0])) {
					value.add(datas[0]);
				}
				map.put(datas[1], value);
			} else {
				List<String> value = new ArrayList<String>();
				value.add(datas[0]);
				map.put(datas[1], value);
			}
		}
		for(Entry<String, List<String>> entry : map.entrySet()) {
			List<String> data = entry.getValue();
			for(String line : list) {
				String[] st = line.split(" ");
				if(data.contains(st[0]) && data.contains(st[1])) {
					flag = true;
					break;
				}
			}
		}
		if(flag) {
			System.out.println("-1");
		} else {
			System.out.println("1");
		}
	}

}
