package com.lifeix.d20130831;

import java.util.Date;

public class ZDL_VamPire {
	static int count =0;
	public static void main(String[] args) {
		ZDL_VamPire vampire = new ZDL_VamPire();
		Long d =System.currentTimeMillis();
		vampire.allFours();
		System.out.println(System.currentTimeMillis()-d);

	}

	public void allFours() {
		Integer modNum;//余数
		Integer twoNum ;//第二个数
		Integer vamPire = 1000;//总数

		while(vamPire<10000){
			if(vamPire%100!=0){//总数后两位不为0
				aa:for(Integer oneNum = 10;oneNum<100;oneNum++){//第一个数为2位数
					if((modNum=vamPire%oneNum)==0){//总数/第一个数为0向下执行
						twoNum = vamPire/oneNum;//算出第二个数
						if(oneNum<twoNum){//第一个数小于第二个数
							//判断两位数的各个位数数字是否相等	
							if(filterNum(vamPire,oneNum,twoNum)){//总数包含两个数各个位数
								if(twoNum<100){//第二个数小于100
									System.out.println(oneNum+"*"+twoNum+"="+vamPire);
									count++;
									break aa;
								}
							}
						}
					}
				}
			}

			vamPire++;
		}
	}

	/***
	 * 总数是否包含各个位数的数
	 * @param vamPire 总数
	 * @param num 被包含的数dsdsdshf
	 * @return
	 */
	public boolean filterNum(Integer vamPire,Integer oneNum,Integer twoNum) {
		boolean flag=false;
		StringBuffer sb = new StringBuffer();
		sb = sb.append(String.valueOf(vamPire))
				.append(String.valueOf(oneNum))
				.append(String.valueOf(twoNum));
		for(int i=0;i<sb.length()/2;i++){
			char one = sb.charAt(i);
			aa:for(int y=sb.length()/2;y<sb.length();y++){
				char two = sb.charAt(y);
				if(one==two){
					sb.setCharAt(i, ' ');
					sb.setCharAt(y, ' ');
					break aa;
				}
			}			
		}
		if(sb.substring(0, sb.length()).equals("        "))
		{
			flag=true;
		}
		return flag;
	}
}