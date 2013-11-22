package com.lifeix.d20131023;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lifeix.Util;

public class Zhch implements IntDecoder {

	@Override
	public int parseInt(byte[] bytes) {
		int result = 0;
		for(int i = bytes.length -1 ; i>=0;i--){
			int max = bytes.length - 1;
			if( i == max){
				result += bytes[i] & 127;
			}else{
				result += ((int)bytes[i]) << ((max - i)*7);
			}
		}
		return result;
	}

	@Override
	public List<byte[]> splitBytes(InputStream in) {
		List<byte[]> list = new ArrayList<byte[]>();
		byte[] bytes = new byte[10];
		int index = 0;
		try {
			int curByte = in.read();
			while (curByte > 0) {
				byte b = (byte) curByte;
				bytes[index] = b;
				index++;
//				System.out.println("(b & 128) == 128 : " + ((b & 128) == 128) + b + (b & 128));
				if ((b & 128) == 128) {
					byte[] bs = new byte[index];
					for (int i = 0; i < bs.length; i++) {
						bs[i] = bytes[i];
					}
					list.add(bs);
					index = 0;
				}
				curByte = in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
