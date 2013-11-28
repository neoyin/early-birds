package com.lifeix.d20131120;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zhch {
	int lineNumBit = 1;
	Map<Integer, Integer> lineRange = new HashMap<Integer, Integer>();

	/**
	 * 读入文件
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public List<String> readFile(String filePath) throws Exception {
		System.out.println("read...");
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("文件不存在: " + filePath);
		}
		List<String> result = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			result.add(line);
		}
		return result;
	}

	public void diff(String oldPath, String newPath) throws Exception {
		System.out.println("diff...");
		List<String> oldLines = readFile(oldPath);
		List<String> newLines = readFile(newPath);
		Map<String, List<Integer>> oldMap = new HashMap<String, List<Integer>>();
		for (int i = 0; i < oldLines.size(); i++) {
			String line = oldLines.get(i);
			List<Integer> list = oldMap.get(line);
			if(list == null){
				list = new ArrayList<Integer>();
			}
			list.add(i);
			oldMap.put(line, list);
		}
		List<Diff> diffList = new ArrayList<Diff>();
		for (int i = 0; i < newLines.size(); i++) {
			String line = newLines.get(i);
			List<Integer> list = oldMap.get(line);
			Integer lineNum = null;
			if(list != null){
				int delta = Integer.MAX_VALUE;
				for(Integer num : list){
					if(Math.abs(num - i) < delta){
						lineNum = num;
						delta = Math.abs(num -i);
					}
				}
			}
			Diff last = diffList.size() > 0 ? diffList.get(diffList.size() - 1) : new Diff();
			if (lineNum == null) {
				Diff diff = new Diff(i, line);
				diffList.add(diff);
			} else {

				if (lineNum == last.start + last.range) {
					last.range = last.range + 1;
					last.lines.add(line);
					Integer maxRange = lineRange.get(last.start);
					if(maxRange == null || maxRange< last.range){
						lineRange.put(last.start, last.range);
					}
				} else {
					Diff diff = new Diff(lineNum, 1, line);
					diffList.add(diff);
				}
			}
		}
		printResult(oldLines, newLines, diffList);
	}

	private void printResult(List<String> oldLines, List<String> newLines, List<Diff> diffList) {
		System.out.println("print..");
		setLineNumBit(oldLines.size());
		int oldIndex = 0;
		for (Diff diff : diffList) {
			if (diff.same) {
				Integer maxRange = lineRange.get(diff.start); 
				if(maxRange != null && maxRange == diff.range){
					for (int i = 0; i < (diff.start - oldIndex); i++) {
						printLine(-1, oldLines.get(oldIndex++));
					}
					for (int i = 0; i < diff.range; i++) {
						int lineNum = diff.start + i;
						printLine(lineNum, oldLines.get(lineNum));
						oldIndex++;
					}
				}else{
					for (int i = 0; i < diff.range; i++) {
						int lineNum = diff.start + i;
						printLine(-2, newLines.get(lineNum));
					}
				}
			} else {
				printLine(-2, newLines.get(diff.start));
			}
//			System.out.println("line:" + diff.start);
		}
	}

	private void setLineNumBit(int num) {
		lineNumBit = 0;
		while (num > 0) {
			lineNumBit++;
			num = num / 10;
		}
	}

	private String getFullString(String str) {
		int num = lineNumBit - str.length();
		for (int i = 0; i < num; i++) {
			str = ' ' + str;
		}
		return str;
	}

	private void printLine(int lineNum, String line) {
		String num = "";
		if (lineNum >= 0) {
			num = getFullString(lineNum + "") + ":";
		} else if (lineNum == -1) {
			num = getFullString("") + "-";
		} else {
			num = getFullString("") + "+";
		}
		System.out.println(num + line);
	}

	public static void main(String[] args) throws Exception {
		Zhch t = new Zhch();
		t.diff("/home/lifeix/temp/d3/Test.java", "/home/lifeix/temp/d3/Test2.java");
	}

	class Diff {
		public int start = -1;
		public int end = -1;
		public int range = 0;
		public boolean same = false;
		public List<String> lines = new ArrayList<String>();

		public Diff() {
		}

		public Diff(int s, String l) {
			start = s;
			lines.add(l);
		}

		public Diff(int s, int r, String l) {
			start = s;
			range = r;
			same = true;
			lines.add(l);
		}
	}
}
