package com.lifeix.d20131023;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LJApp implements IntDecoder {

	@Override
	public int parseInt(byte[] bytes) {
		return this.unencode(bytes);
	}

	@Override
	public List<byte[]> splitBytes(InputStream in) {
		try {
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				bytestream.write(ch);
			}
			byte[] by = bytestream.toByteArray();
			List<byte[]> bytes = new ArrayList<byte[]>();
			int k = 0;
			for (int i = 0; i < by.length; i++) {
				k++;
				if (by[i] < 0) {
					byte[] b = new byte[k];
					int n = 0;
					for (int j = (i - k + 1); j <= i; j++) {
						b[n] = by[j];
						n++;
					}
					bytes.add(b);
					k = 0;
				}
			}
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public int unencode(byte[] b) {
		int number = 0;
		int k = 1;
		int z = 1 << 7;

		for (int i = b.length - 1; i >= 0; i--) {
			if (b[i] < 0) {
				b[i] = (byte) (b[i] | (z));
			}
			number += this.getInt(b[i]) * (k << (7 * (b.length - i - 1)));
		}
		return number;
	}

	public int getInt(byte b) {
		int number = 0;
		for (int i = 6; i >= 0; i--) {
			if((byte) (b & 1) > 0){
				number += (1<<(6-i));
			}
			b = (byte) (b >> 1);
		}
		return number;
	}

}
