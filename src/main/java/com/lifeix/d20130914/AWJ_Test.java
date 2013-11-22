package com.lifeix.d20130914;


public class AWJ_Test {
	
	public static void main(String[] args) {
		AWJ_ArrayList<String> names = new AWJ_ArrayList<String>();
		names.add("尹威");
		names.add("张程");
		names.add("李江");
		names.add("张东磊");
		names.add("琚苏鸿");
		names.add("蒋富强");
		names.add("安万军");
		names.remove(names.size()-1);
		for(int i = 0; i<names.size(); i++){
			System.out.println(names.get(i));
		}
		names.clear();
		System.out.println(names.size());
	}

}
