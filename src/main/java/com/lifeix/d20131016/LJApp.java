package com.lifeix.d20131016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LJApp implements IntEncoder{

	@Override
	public byte[] encode(int src) {
		String s = Integer.toBinaryString(src);
		char[] a = s.toCharArray(); 
		byte b = 0;
		byte b2 = 1;
		List<Byte> bytes = new ArrayList<Byte>();
		int i = a.length-1;
		int k = 0;
		boolean more = false;
		while(i>=0){
			if(k==7){
				k=-1;
				i++;
				if(!more){
					b= (byte)(b | (b2 << 7));
					more = true;
				}
				bytes.add(b);
				b = 0;
			}else{
				if(a[i] == '1'){
					b = (byte)(b | (b2<<k));
				}
			}
			b2 = 1;
			k++;
			i--;
		}
		if(b > 0 ){
			if(!more)b= (byte)(b | (b2 << 7));
			bytes.add(b);
		}
		byte[] array = new byte[bytes.size()];
		for(int j=0;j<bytes.size();j++){
			array[j] = bytes.get(j);
		}
		Byte[] byteArr = new Byte[array.length];
		for(int index = 0;index< array.length;index++){
			byteArr[index] = array[index];
		}
		List<Byte> list = Arrays.asList(byteArr);
		Collections.reverse(list);
		for(int index = 0;index< array.length;index++){
			array[index] = list.get(index);
		}
		return array;
	}
	
	public static void main(String[] args){
		int src = 127;
		IntEncoder encoder = new LJApp();
		byte[] b = encoder.encode(src);
		for(int i=b.length-1;i>=0;i--){
			System.out.println(Arrays.toString(getBooleanArray(b[i])).replaceAll(",", "").replace("[", "").replace("]", "").replaceAll(" ", ""));
		}
	}
	 public static byte[] getBooleanArray(byte b) {  
	        byte[] array = new byte[8];  
	        for (int i = 7; i >= 0; i--) {  
	            array[i] = (byte)(b & 1);  
	            b = (byte) (b >> 1);  
	        }  
	        return array;  
	    }  

}
