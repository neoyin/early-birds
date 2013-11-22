package com.lifeix.d20130831;

public class AWJ_Vampire {
	
	
	private static boolean getVampireNumber(int num) {
		int a[] = new int[4];
		// 千位数字
		a[0] = (int) num / 1000;
		// 百位数字
		a[1] = (int) ((num - a[0] * 1000) / 100);
		// 十位数字
		a[2] = (int) ((num - a[0] * 1000 - a[1] * 100) / 10);
		// 个位数字
		a[3] = (int) (num - a[0] * 1000 - a[1] * 100 - a[2] * 10);
		// 如果后两位是0，直接返回
		if (a[2] == 0 && a[3] == 0) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						if (i != j && i != k && i != l && j != k && j != l
								&& j != l && k != l) {
							int n1 = a[i] * 10 + a[j];
							int n2 = a[k] * 10 + a[l];
							if (n1 * n2 == num) {
								System.out.println(num + "=" + n1 + "x" + n2);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		for (int num = 1000; num < 10000; num++) {
			getVampireNumber(num);
		}
	}
}
