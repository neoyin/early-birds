package com.lifeix.d20130731;

import java.util.ArrayList;
import java.util.List;

public class JSHKingOfMonkey {
	
	private List<JSHMonkey> monkeys = new ArrayList<JSHMonkey>();
	
	public JSHKingOfMonkey(int key){
		for(int i=0; i<key; i++) {
			monkeys.add(new JSHMonkey(i+1));
		}
	}
	public JSHMonkey getKing(){
		int startIndex = 0;
		while(monkeys.size() != 1){
			int removeIndex = (startIndex + 2) % monkeys.size();
			monkeys.remove(removeIndex);
			startIndex = removeIndex;
		}
		return monkeys.get(0);
	}
	public static void main(String[] args){
		int[] number = {10,20,50,1050,10050};
		for(int num : number){
			JSHKingOfMonkey jsh = new JSHKingOfMonkey(num);
			System.out.println(num + "只猴子的king：NO " + jsh.getKing().index);
		}
	}
}
