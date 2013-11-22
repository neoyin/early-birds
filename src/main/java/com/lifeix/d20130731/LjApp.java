package com.lifeix.d20130731;

public class LjApp {

	/**
	 * 10,20,50,1050,10050
	 * @param args
	 */
	public static void main(String[] args){
		theKing(10);
		theKing(20);
		theKing(50);
		theKing(1050);
		theKing(10050);
	}
	/**
	 * 寻找大王
	 * @param num
	 * @return
	 */
	private static int theKing(int num){
		if(num <= 0)return 0;
		boolean[] monkeys = new boolean[num];
		for(int i=0;i<monkeys.length;i++){
			monkeys[i]=true;
		}
		int me = 0;
		int j = monkeys.length;
		int n = 0;
		while(j > 1){			//只剩下一个退出
			int k = n;
			if(n >= num){
				k = n%num;
			}
			if(monkeys[k]){
				me++;
				if(me%3 == 0){
					monkeys[k] = false;
					j--;
				}
			}
			n++;
		}
		for(int i=0;i<monkeys.length;i++){
			if(monkeys[i]){
				System.out.println(num+"只猴子时，第"+(i+1)+"只是大王");
			}
		}
		return 0;
	}
}
