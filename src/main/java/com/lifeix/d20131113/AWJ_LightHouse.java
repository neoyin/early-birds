package com.lifeix.d20131113;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AWJ_LightHouse {

	public static void main(String[] args) {
		
		System.out.println("请输入灯塔的总数量");
		Scanner reader=new Scanner(System.in); 
		int total = reader.nextInt();
		List<LightHouse> houses = new ArrayList<LightHouse>();
		for(int n=0; n<total; n++){
			System.out.println("请输入第"+(n+1)+"个灯塔的坐标，用逗号分隔 如：2,5");
			String local = reader.next();
			String [] locals = local.split(",");
			houses.add(new LightHouse("灯塔"+(n+1),Integer.valueOf(locals[0]), Integer.valueOf(locals[1])));
		}
		
		int count = 0;//记录照亮彼此的灯塔对的数量
		for(int i=0;i<houses.size();i++){
			LightHouse house1 = houses.get(i);
			for(int j=i+1;j<houses.size();j++){
				LightHouse house2 = houses.get(j);
				if(house1.getX()<house2.getX() && house1.getY()<house2.getY()){
					count++;
					System.out.println(house1.getName()+"["+house1.getX()+","+house1.getY()+"]"+"<<<<>>>>>"+house2.getName()+"["+house2.getX()+","+house2.getY()+"]");
				}
			}
		}
		System.out.println("总计有"+count+"对灯塔可以彼此照亮");
	}



}

/**
 * 灯塔
 * @author ANWJ
 *
 */
class LightHouse{

	public LightHouse(String name, int x,int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}

	private String name;

	private int x;

	private int y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
