package com.lifeix.d20131127;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class JSH_JavaSSH {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		JSH_JavaSSH jsh = new JSH_JavaSSH();
		String urlPath = "http://www.google.com.hk/";
		System.out.println(jsh.getURLContent(urlPath));
	}
	/**
	 * 7070 ssh proxy
	 * @return
	 */
	private String getURLContent(String urlPath) {
		StringBuffer sb = new StringBuffer();
		InputStream is = null;
		try {
			URL url = new URL(urlPath);
			InetSocketAddress isa = new InetSocketAddress("127.0.0.1",7070);
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, isa);
			URLConnection conn = url.openConnection(proxy);
			is = conn.getInputStream();
			int i = -1;
			byte[] b = new byte[1024];
			while((i = is.read(b)) != -1) {
				sb.append(new String(b, 0, i));
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
