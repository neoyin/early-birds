package com.lifeix.d20140108templet;

abstract class LJApp {
	public final void prepareRecipe(){
		boilWater();
		brew();
		daoru();
	}

	abstract void brew();
	//烧水
	public void boilWater(){
		System.out.println("现在开始烧水");
	}
	
	public void daoru(){
		System.out.println("现在导入杯子中");
	}
	
	public static void main(String[] args) {
		LJAppCoffe app=new LJAppCoffe();
		app.prepareRecipe();
		
		LJAppCha appc=new LJAppCha();
		appc.prepareRecipe();
	}
}
