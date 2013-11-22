package com.lifeix.d20131016;

import com.lifeix.TestUtil;
import com.lifeix.Util;

public class IntEncoderTester {

	public void test() {
		TestUtil.searchAndTest("com.lifeix.d20131016", IntEncoder.class, "testEncoder", this);
	}

	/**
	 * 测试实现
	 * 
	 * @param encoder
	 */
	public void testEncoder(IntEncoder encoder) {
		System.out.println("classname===================:" + encoder.getClass().getName());
		int[] tests = new int[] { 5, 130, 128, 751, 65535, 123456789, 1012121212 };
		for (int src : tests) {
			byte[] result = encoder.encode(src);
			String bytes = "";
			for (byte b : result) {
				bytes += Util.byteToStr(b) + " ";
			}
			System.out.println(Util.fillString(src + "", 11) + " : " + bytes);
		}
	}

	public static void main(String[] args) {
		IntEncoderTester t = new IntEncoderTester();
		t.test();
	}
}
