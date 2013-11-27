package com.lifeix.d20131127;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class AWJ_Proxy {
	public static void main(String[] args) {

		try {
			java.net.InetAddress addr = InetAddress.getByName("192.168.1.121");
			InetSocketAddress sa = new InetSocketAddress(addr, 8070);
			java.net.Proxy proxy = new java.net.Proxy(Proxy.Type.HTTP, sa);
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("root",new String("skstserver").toCharArray());
				}
			});
			String strURL = "http://www.sina.com"; 
			URL url = new URL(strURL);  
			URLConnection conn = url.openConnection(proxy);
			InputStreamReader input = new InputStreamReader(conn.getInputStream(), "utf-8");  
			BufferedReader bufReader = new BufferedReader(input);  
			String line = "";  
			while ((line = bufReader.readLine()) != null) {  
				System.out.println(line); 
			}  
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}   

	}

}
