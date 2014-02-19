package com.lifeix.d2014.m0108templet;


public abstract class AWJ_Templet {
	
	public void recipe(){
		//烧水
		boilWater();
		//添加料
		addCondiments();
		//倒入杯子
		pourInCup();
	}
	
	public void boilWater(){
		System.out.println("start boil water..........");
	}
	
	public abstract void addCondiments();
	
	
	public void pourInCup(){
		System.out.println("Pouring into cup..........");
	}
}

class TeaWater extends AWJ_Templet{

	@Override
	public void addCondiments() {
		System.out.println("adding tea into cup..........");
	}
	
}

class Coffee extends AWJ_Templet{

	@Override
	public void addCondiments() {
		System.out.println("adding coffee into cup..........");
	}
}

