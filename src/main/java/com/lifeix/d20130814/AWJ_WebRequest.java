package com.lifeix.d20130814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AWJ_WebRequest {
	
	static final String loginURL = "http://www.l99.com/EditAccount_login.action";
	static final String textURL = "http://www.l99.com/timeline_saveText.action";

	
	public static void main(String[] args) throws MalformedURLException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("text.textTitle","这是标题");
		map.put("text.textContent","这是内容");
		List<String> list = login("3251315","lovetingmei",loginURL);
		saveText(textURL, map, list);
	}
	
	static List<String> cookies = new ArrayList<String>();
	
	
	private static List<String> login(String name,String pwd, String loginURL) throws MalformedURLException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("e="+name);
		sb.append("&m="+pwd);
		URL url = new URL(loginURL+"?"+sb);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		List<String> list = conn.getHeaderFields().get("Set-Cookie");
		conn.disconnect();
		return list;
	}
	
	
	private static void saveText(String textURL, Map<String, String> textMap, List<String> list) throws MalformedURLException, IOException {
		URL url = new URL(textURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		
		StringBuffer sb = new StringBuffer();
		for(String s : list) {
			sb.append(s).append(";");
		}
		System.out.println(sb.toString()+"-----------");
		conn.setRequestProperty("Cookie", sb.toString());
		StringBuffer param = new StringBuffer();
		for(Entry<String,String> entry : textMap.entrySet()) {
			param.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		OutputStream os = conn.getOutputStream();
		os.write(param.toString().getBytes());
		os.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String readLine = "";
		while((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}
		conn.disconnect();
	}
	
}

