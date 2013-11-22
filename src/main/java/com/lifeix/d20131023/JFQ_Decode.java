package com.lifeix.d20131023;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JFQ_Decode implements IntDecoder {

	public int parseInt(byte[] bytes) {
		return Integer.parseInt(Arrays.toString(bytes).replace(", ", "")
				.replace("[", "").replace("]", ""),2);
	}

	private static byte[] toByte(byte b) {
		byte[] bu = new byte[8];
		for(int i = 7; i >=0; i--) {
			bu[i] = (byte) (b&1);
			b = (byte) (b >> 1);
		}
		return bu;
	}
	
	public List<byte[]> splitBytes(InputStream in) {
		byte[] buffer = new byte[8];
		List<byte[]> list = new ArrayList<byte[]>();
		try {
			StringBuffer sb = new StringBuffer();
			while(in.read(buffer, 0, buffer.length) > 0) {
				for(int i = 0; i < buffer.length; i++) {
					byte b = buffer[i];
					String temp = Arrays.toString(toByte(b)).replace(", ", "")
							.replace("[", "").replace("]", "");
					sb.append(temp);
					if(temp.startsWith("1")) {
						byte[] bb = new byte[sb.length()];
						String str = sb.toString();
						for(int j = 1; j < str.length(); j++) {
							bb[j] = Byte.parseByte(str.substring(j, j+1));
						}
						bb[0] = 0;
						list.add(bb);
						sb = new StringBuffer();
					}
				}
				buffer = new byte[8];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		JFQ_Decode jfq = new JFQ_Decode();
		List<byte[]> list = new ArrayList<byte[]>();
		list.add(new byte[] { (byte) 133, (byte) 3, (byte) 133, (byte) 12, (byte) 15, (byte) 133, (byte) 133, (byte) 3,
				(byte) 127, (byte) 255 });
		for (byte[] bytes : list) {
			InputStream in = new ByteArrayInputStream(bytes);
			List<byte[]> intBytes = jfq.splitBytes(in);
			List<Integer> ints = new ArrayList<Integer>();
			for (byte[] b : intBytes) {
				int integer = jfq.parseInt(b);
				ints.add(integer);
			}
			System.out.println(ints);
		}
	}

}

