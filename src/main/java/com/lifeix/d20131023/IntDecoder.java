package com.lifeix.d20131023;

import java.io.InputStream;
import java.util.List;

public interface IntDecoder {
	/**
	 * 把byte数组转换为整数
	 * @param bytes
	 * @return 
	 */
	int parseInt(byte[] bytes);
	
	/**
	 * 把输入流切为整数的数组
	 * @param in
	 * @return
	 */
	List<byte[]> splitBytes(InputStream in);
}
