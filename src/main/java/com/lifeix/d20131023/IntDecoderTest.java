package com.lifeix.d20131023;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lifeix.TestUtil;
import com.lifeix.Util;

public class IntDecoderTest {

	public void test() {
		TestUtil.searchAndTest("com.lifeix.d20131023", IntDecoder.class, "testDecoder", this);
	}

	/**
	 * 测试实现
	 * 
	 * @param encoder
	 */
	public void testDecoder(IntDecoder decoder) {

		List<byte[]> list = new ArrayList<byte[]>();
		list.add(new byte[] { (byte) 133, (byte) 3, (byte) 133, (byte) 12, (byte) 15, (byte) 133, (byte) 133, (byte) 3,
				(byte) 127, (byte) 255 });

		System.out.println("classname===================:" + decoder.getClass().getName());
		for (byte[] bytes : list) {
			InputStream in = new ByteArrayInputStream(bytes);
			List<byte[]> intBytes = decoder.splitBytes(in);
			List<Integer> ints = new ArrayList<Integer>();
			System.out.println("输入流:" + Util.bytesToStr(bytes));
			for (byte[] b : intBytes) {
				System.out.println("数字:" + Util.bytesToStr(b));
				int integer = decoder.parseInt(b);
				ints.add(integer);
			}
			System.out.println("结果:" + ints);
		}
	}

	public static void main(String[] args) {
		IntDecoderTest t = new IntDecoderTest();
		t.test();
	}
}
