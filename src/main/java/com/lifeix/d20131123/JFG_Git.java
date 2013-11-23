package com.lifeix.d20131123;

import java.io.IOException;
import java.io.InputStream;

public class JFG_Git {

	public void print() throws IOException {
		String cmd = "git diff HEAD";
		Process p = Runtime.getRuntime().exec(cmd);
		InputStream is = p.getInputStream();
		byte[] buffer = new byte[1024];
		StringBuffer sb = new StringBuffer();
		while(is.read(buffer) > -1) {
			sb.append(new String(buffer));
		}
		System.out.println(sb.toString());
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new JFG_Git().print();
	}

}
