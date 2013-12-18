package com.lifeix.d20131211;

public class AWJ_Fb {


	public static void main(String[] args) {
		AWJ_Fb test = new AWJ_Fb();
		System.out.println(test.rb(40));
		System.out.println(test.unfb(40));
	}

	public int rb(int n){
		if(n == 1){
			return 0;
		}else if(n<=3){
			return 1;
		}else if(n>3){
			return rb(n-1)+rb(n-2);
		}else if(n<=0){
			System.out.println("输入有误");
			return -1;
		}
		return 0;
	}

	public Long unfb(int n) {
		if (n == 1)
			return 0L;
		else if(n<=3){
			return 1L;
		}
		long l1 = 1;
		long l2 = 1;
		int index = 3;
		while (index < n - 1) {
			long l = l2;
			l2 = l1 + l2;
			l1 = l;
			index++;
		}
		return l1 + l2;
	}
}
