package com.lifeix.d20130831;
/**
 * @DESCRIPTION:	
 * @DATE:2013-8-31 上午11:35:05
 **/
public class JFQ_Magic {

	private void magic() {
		for(int i = 1110; i < 10000; i++) {
			String str = String.valueOf(i);
			if(str.indexOf("00") >= 0) {
				continue;
			}
			if(str.subSequence(0, 2).equals(str.substring(2))) {  //如果像1212这种格式的，就直接跳过
				continue;
			}
			char[] chs = str.toCharArray();
			int count = 0;
			for(char ch : chs) {
				if(chs[0] == ch) {
					count++;
				}
			}
			if(count >= 3) {    //如果四个数字都一样，则跳过
				continue;
			}
			compose(chs, i);
		}
	}
	
	private boolean cal(char arg1, char arg2, char arg3, char arg4, int i) {
		String str1 = String.valueOf(arg1) + String.valueOf(arg2);
		String str2 = String.valueOf(arg3) + String.valueOf(arg4);
		int count = Integer.parseInt(str1)*Integer.parseInt(str2);
		if(i == count) {
			System.out.println(str1 + "*" + str2 + " = " + i);
			return false;
		}
		return true;
	}
	
	private void compose(char[] chs, int i) {
		char[] temp = new char[chs.length-1];
		for(char ch : chs) {
			if(ch == 0) {  //如果需要组合的数字中，第一个是0，则跳过，比如01*21, 09*32
				continue;
			}
			int tempCount = 0;
			int c = 0;
			for(char tch : chs) {
				if(tch == ch && c == 0) {
					c = 1;
					continue;
				} else {
					temp[tempCount++] = tch;
				}
			}
			boolean flag = cal(ch, temp[0], temp[1], temp[2], i);
			if(flag) {
				flag = cal(ch, temp[0], temp[2], temp[1], i);
			}
			if(flag) {
				flag = cal(ch, temp[1], temp[0], temp[2], i);
			}
			if(flag) {
				flag = cal(ch, temp[1], temp[2], temp[0], i);
			}
			if(flag) {
				flag = cal(ch, temp[2], temp[1], temp[0], i);
			}
			if(flag) {
				flag = cal(ch, temp[2], temp[0], temp[1], i);
			}
			if(!flag) {
				break;
			}
		}
	}
	
	/**@description:	
	 * @return:void
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFQ_Magic jfq = new JFQ_Magic();
		long start = System.currentTimeMillis();
		jfq.magic();
		long end = System.currentTimeMillis();
		System.out.println("总耗时：" + (end - start) + " ms");
	}

}

