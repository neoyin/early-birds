package com.lifeix.d20131214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Integer> map = new HashMap<String, Integer>(n);
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
		int isNone = 0;
		for(String line : list) {
			String[] datas = line.split(" ");
			if(map.containsKey(datas[0])) {
				int value = map.get(datas[0]);
				if(value >= n - 2) {
					flag = true;
					isNone += 1;
					break;
				} else {
					map.put(datas[0], ++value);
				}
			} else {
				map.put(datas[0], 1);
			}
			if(map.containsKey(datas[1])) {
				int value = map.get(datas[1]);
				if(value >= n - 2) {
					flag = true;
					isNone += 1;
					break;
				} else {
					map.put(datas[1], ++value);
				}
			} else {
				map.put(datas[1], 1);
			}
		}
		if(flag || (isNone <= 1 && map.size()%2 != 0)) {
			System.out.println("-1");
		} else {
			System.out.println("1");
		}
	}

}
