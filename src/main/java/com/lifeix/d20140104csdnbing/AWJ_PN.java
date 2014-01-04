package com.lifeix.d20140104csdnbing;

public class AWJ_PN {
	
	public static void main(String[] args) {
		System.out.println(palindrom("919"));
	}
	
	public static String palindrom(String a) { 
		if(a.equals("1")) return "Impossible"; 
		try{ 
			long num = Long.parseLong(a); 
			int a_sum = sum(num);
			//a的各位数字和 
			for(long i=num+1;i<Long.MAX_VALUE;i++){ 
				//判断是否为回文数 
				if(i==reverse(i)){ 
					int i_sum = sum(i); 
					if(a_sum==i_sum){ 
						num = i; 
						break; 
					}
				} 
			} 
			return String.valueOf(num); 
		}catch (Exception e) { 
			return "Impossible"; 
		} 
	}
	
	//求和
	public static int sum(long m){ 
		long s = 0,b = 0; 
		while(m != 0){
			b = m % 10; m = m / 10; s = s + b; 
		} 
		return (int)s;
	} 
	
	//判断是否是回文数
	private static long reverse(long m) {
		long s = 0;
		while(m>0){
			s=s*10+m%10; m=m/10; 
		} 
		return s; 
	}

}
