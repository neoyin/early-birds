package com.lifeix.d20131211;


public class JFQ_F {

	public static int cycle(int n) {
		if(n <= 1){  
            return 0;  
        }else if(n < 2) {
        	return 1;
        }else{  
            return cycle(n-1) + cycle(n-2);  
        }  
	}
	
	public static int noCycle(int n) {
		if(n <= 1){  
            return 0;  
        }else if(n < 2) {
        	return 1; 
        }  
        int n1 = 1;
        int n2 = 1;
        int sn = 0;  
        for(int i = 0; i < n - 2; i ++){  
            sn = n1 + n2;  
            n1 = n2;  
            n2 = sn;  
        }  
        return sn;  
	}
	
	public static void main(String[] args) {
		int n = 40;
        System.out.println(cycle(n)); 
		System.out.println(noCycle(n));
	}

}
