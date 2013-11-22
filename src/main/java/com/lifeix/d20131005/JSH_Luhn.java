package com.lifeix.d20131005;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JSH_Luhn {

	private static final int TEN = 10;
	private static final int LENGTH = 16;
	private static final int NUM = 100;
	
	public static void main(String[] args) {
		JSH_Luhn luhn = new JSH_Luhn();
		for(String card : luhn.getRandom()) {
			if(luhn.verify(card)) {
				System.out.println(card + " : Successd");
			}else {
				System.out.println(card + " : Failed");
			}
		}
	}

	private boolean verify(String card) {
		int cardLength = card.length() - 1;
		int totel = 0;
		for(int i = cardLength,j=1 ; i >= 0 ; i--,j++) {
			if(j%2 != 0) {
				totel += Integer.parseInt(Character.toString(card.charAt(i)));
			}else {
				int temp = 2 * Integer.parseInt(Character.toString(card.charAt(i)));
				if(temp >= TEN) {
					temp = temp - 9;
				}
				totel += temp;
			}
		}
		if(totel%TEN == 0) {
			return true;
		}else {
			return false;
		}
	}
	private List<String> getRandom() {
		List<String> cards = new ArrayList<String>();
		Random random = new Random();
		for(int index=0 ; index<NUM ;index++) {
			StringBuffer card = new StringBuffer();
			for(int i=0 ; i<2 ; i++) {
				int intNum = Math.abs(random.nextInt());
				card.append(intNum);
			}
			cards.add(card.substring(0, LENGTH));
		}
		return cards;
	}
}
