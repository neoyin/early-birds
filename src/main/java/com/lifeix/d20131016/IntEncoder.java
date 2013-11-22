package com.lifeix.d20131016;

public interface IntEncoder {
	/**
	 * 只实现正整数的编码, 负数抛出异常 IllegalArgumentException 即可
	 * @param src
	 * @return
	 */
	byte[] encode(int src);
}
