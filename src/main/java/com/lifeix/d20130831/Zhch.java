package com.lifeix.d20130831;

public class Zhch {
	public void test() {
		for (int i = 10; i < 100; i++) {
			for (int j = i; j < 100; j++) {
				int product = i * j;
				if (product > 999 && isVampire(i, j, product)) {
					System.out.println("吸血鬼数字: " + product + " = " + i + " * " + j);
				}
			}
		}
	}

	public boolean isVampire(int x1, int x2, int y) {
		String a = x1 + "" + x2;
		String b = y + "";
		for (int i = 0; i < b.length(); i++) {
			int index = a.indexOf(b.charAt(i));
			if (index >= 0) {
				a = a.substring(0, index) + a.substring(index+1);
			} else {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Zhch t = new Zhch();
		t.test();
		int index = 0;
		String a ="0";
		System.out.println(a = a.substring(0, index) + a.substring(index+1));
	}
}
