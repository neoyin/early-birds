package com.lifeix.d20140108templet;

public class AWJ_Test {
	public static void main(String[] args) {
		TeaWater teaWater = new TeaWater();
		Coffee coffee = new Coffee();
		System.out.println("--------冲茶水-------");
		teaWater.recipe();
		System.out.println();
		System.out.println("--------冲咖啡-------");
		coffee.recipe();
	}

}
