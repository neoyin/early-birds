package com.lifeix.d20131127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JFQ_Sina {

	public static void main(String[] args) {
		 String strUrl = "http://www.hao123.com/";  
	        try {  
				URL url = new URL(strUrl);  
	            System.setProperty("proxySet", "true");
	            System.setProperty("http.proxyHost", "192.168.1.121");
	            System.setProperty("http.proxyPort", "8080");
	            InputStreamReader isr = new InputStreamReader(url.openStream(),  
	                    "UTF-8");  
	            BufferedReader br = new BufferedReader(isr);  
	            while (br.readLine() != null) {  
	                System.out.println(br.readLine());  
	            }  
	            br.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	}
}
