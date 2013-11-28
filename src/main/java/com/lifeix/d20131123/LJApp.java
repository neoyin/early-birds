package com.lifeix.d20131123;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LJApp {

	public static String basePath = "/nfsdata/filedata/birdTest/base";
	public static String proPath = "/nfsdata/filedata/birdTest/pro";

	public static List<Line> proList = new ArrayList<Line>();
	public static List<Line> baseList = new ArrayList<Line>();
	public static void main(String[] args){
		try {
			HashMap<String, List<Line>> base = read(basePath, true);
			HashMap<String, List<Line>> pro = read(proPath, false);
			int baseNumber = 0;
			for(int i=0;i<proList.size();i++){
				int number = baseList.size();
				Line line = proList.get(i);
				if(base.containsKey(line.getS())){
					int choose = -1;
					List<Line> li = base.get(line.getS());
					for(int j=0;j<li.size();j++){
						Line l = li.get(j);
						if(l.getNumber() > baseNumber && l.getNumber() <= number){
							number = l.getNumber();
							choose = j;
						}
					}
					if(choose >= 0){
						li.get(choose).setChoose(true);
						baseNumber = number;
						for(int j=0;j<pro.get(line.getS()).size();j++){
							if(pro.get(line.getS()).get(j).getNumber() == line.getNumber()){
								pro.get(line.getS()).get(j).setChoose(true);
								break;
							}
						}
						line.setChoose(true);
					}
				}
				
			}
			System.out.println("add:");
			for(int i=0;i<proList.size();i++){
				if(!proList.get(i).isChoose()){
					System.out.println(proList.get(i).getNumber()+":"+proList.get(i).getS());
				}
			}
			System.out.println("delete:");
			for(int i=0;i<baseList.size();i++){
				if(!baseList.get(i).isChoose()){
					System.out.println(baseList.get(i).getNumber()+":"+baseList.get(i).getS());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String, List<Line>> read(String path, boolean base) throws IOException{
		HashMap<String, List<Line>> lines = new HashMap<String, List<Line>>();
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int number = 0;
		Line tmpLine = new Line();
		String before = null;
		String s;
		while((s=br.readLine()) != null){
			tmpLine.setAfter(s);
			number ++;
			Line line = new Line();
			line.setNumber(number);
			line.setS(s);
			if(before != null){
				line.setBefore(before);
			}
			if(lines.containsKey(s)){
				lines.get(s).add(line);
			}else{
				List<Line> newLine = new ArrayList<Line>();
				newLine.add(line);
				lines.put(s, newLine);
			}
			tmpLine = line;
			if(!base){
				proList.add(line);
			}else{
				baseList.add(line);
			}
		}
		return lines;
	}
}

class Line {
	private String before;
	private String s;
	private String after;
	private int number;
	private boolean choose = false;
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public boolean isChoose() {
		return choose;
	}
	public void setChoose(boolean choose) {
		this.choose = choose;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	};
}
