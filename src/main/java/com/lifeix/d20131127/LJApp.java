package com.lifeix.d20131127;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
public class LJApp {

	 public static void main(String[] args) {
		 try {
			 URL url = new URL("http://www.youku.com/");
			 InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 7070);  
			 Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); 
		     URLConnection conn = url.openConnection(proxy);  
		     InputStream in = conn.getInputStream();  
			 byte buffer[] = new byte[1024];
			 int i;
			 while ((i = in.read(buffer)) != -1) {
				 System.out.write(buffer, 0, i);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
}

