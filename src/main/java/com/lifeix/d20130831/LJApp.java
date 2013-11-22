package com.lifeix.d20130831;


public class LJApp {

	public static void main(String[] args){
		Long start = System.currentTimeMillis();
		for(int i=11;i<99;i++){
			int j = 99;
			while(j>=i){
				int sum = i * j;
				if(sum>=1000 && sum%100 > 0){		//四位数，结尾不能为两个0
					String[] s = String.valueOf(sum).split("");
					String[] num = (String.valueOf(i)+String.valueOf(j)).split("");
					boolean isXixuegui = false;
					for(int k=0;k<s.length;k++){
						isXixuegui = false;
						boolean equals = false;
						for(int l=0;l<num.length;l++){
							if(s[k].equals(num[l])){
								num[l]="";
								equals = true;
								break;
							}
						}
						if(!equals)break;
						isXixuegui = true;
					}
					if(isXixuegui)System.out.println(sum+" = "+i+" * "+j);
				}
				j--;
			}
		}
		Long end = System.currentTimeMillis();
		System.out.println(end-start+"ms");
	}
}
