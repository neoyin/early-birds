package com.lifeix.d20130814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSHNetwork {
	
	private static final String SAVETEXT_URL_PATH = "http://www.l99.com/timeline_saveText.action";
	private static final String LOGIN_URL_PATH = "http://www.l99.com/EditAccount_login.action";
	
	public static void main(String[] args){
		String textContent = "俯视地球村";
		sendText(textContent);
	}
	private static String getCookies() {
		HttpURLConnection conn = null;
		String cookies = "";
		try {
			URL url = new URL(LOGIN_URL_PATH);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    conn.setRequestProperty("User-agent","Mozilla/5.0 (Linux; Android 4.2.1; Nexus 7 Build/JOP40D) Gecko/20100101 Firefox/22.0");
			conn.setRequestMethod("POST");
			String content = "e=3213081&m=19881225&a=true";
			OutputStream os = conn.getOutputStream();
			os.write(content.toString().getBytes("utf-8"));
			os.close();
			conn.connect();
			String key = null;
			for(int i=1 ; (key = conn.getHeaderFieldKey(i))!=null ; i++) {
				if(key.equalsIgnoreCase("set-cookie")) {
					String headerField = conn.getHeaderField(i);
//					cookies += (headerField.substring(0,headerField.indexOf(";"))+";");
					cookies += headerField + ";";
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return cookies;
	}
	public static void sendText(String textContent) {
		HttpURLConnection conn = null;
		try{
			URL url = new URL(SAVETEXT_URL_PATH);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    conn.setRequestProperty("Cookie", getCookies());
		    conn.setRequestProperty("User-agent","Mozilla/5.0 (Linux; Android 4.2.1; Nexus 7 Build/JOP40D) Gecko/20100101 Firefox/22.0");
			conn.setRequestMethod("POST");
			String content = "text.textContent="+textContent;
			OutputStream os = conn.getOutputStream();
			os.write(content.toString().getBytes("utf-8"));
			os.close();
			conn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines = "";
		    while  ((lines  =  reader.readLine())  !=  null )
		    {
		        System.out.println(lines.trim());
		    }
		    reader.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
	}
}
