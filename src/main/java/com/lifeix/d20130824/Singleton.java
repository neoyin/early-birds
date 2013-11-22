package com.lifeix.d20130824;

public class Singleton extends Thread {
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 5; i++) {
			JSH_Singleton_Two singleton_two = JSH_Singleton_Two.getSingleton();
			singleton_two.say();
			
			JSH_Singleton_Three singleton_three = JSH_Singleton_Three.getSingleton();
			singleton_three.say();
			
			JSH_Singleton_Four singleton_four = JSH_Singleton_Four.getSingleton();
			singleton_four.say();
		}
		for(int i = 0; i < 5; i++) {
			new Singleton().start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		JSH_Singleton_One singleton_one = JSH_Singleton_One.getSingleton();
		singleton_one.say();
		
		JSH_Singleton_Five singleton_five = JSH_Singleton_Five.getSingleton();
		singleton_five.say();
	}
	
}
