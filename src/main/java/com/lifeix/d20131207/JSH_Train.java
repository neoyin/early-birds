package com.lifeix.jsh;

import java.io.BufferedInputStream;
import java.util.Scanner;
/**
 * 
 * @author lifeix
 *
 */
public class JSH_Train implements TrainTest{
	
    public static void main(String[] args) {
        JSH_Train jsh = new JSH_Train();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        System.out.println(jsh.check(line1, line2));
    }
    /**
     * is ok
     */
    public boolean check(String mn, String trains) {
    	int[] mnInt = getInputParam(mn);
    	if(mnInt.length != 2) {
    		exitDoor();
    	}
    	int n = mnInt[0];
    	int m = mnInt[1];
    	int[] trainsInt = getInputParam(trains);
    	if(trainsInt.length != n) {
    		exitDoor();
    	}
    	int max = 0;
    	for(int i = 0; i < trainsInt.length-1; i++) {
    		int temp = 0;
    		if(trainsInt[i] > trainsInt[i+1]) {
    			max++;
    		} else {
    			max = 0;
    		}
    		for(int j = i+1; j < trainsInt.length; j++) {
    			if(trainsInt[j] < trainsInt[i]) {
    				if(temp == 0) {
    					temp = trainsInt[j];
    				} else if(trainsInt[j] < temp){
    					temp = trainsInt[j];
    				} else {
    					return false;
    				}
    			}
    		}
    	}
    	if(max > m) {
    		return false;
    	}
        return true;
    }
    /**
     * Param change string to int
     * @param param
     * @return
     */
    private int[] getInputParam(String param) {
    	String[] ps = param.split("\\s{1,}");
    	int length = ps.length;
    	int[] a = new int[length];
    	for(int i = 0; i < length; i++) {
    		a[i] = Integer.parseInt(ps[i].trim());
    	}
    	return a;
    }
    /**
     * exit
     */
    private void exitDoor() {
    	System.out.println("Param is error");
		System.exit(1);
    }
}
