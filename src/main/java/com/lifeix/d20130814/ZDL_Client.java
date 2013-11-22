package com.lifeix.d20130814;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ZDL_Client {
	static String str = "http://www.l99.com";
	static final String sendUrl = "http://www.l99.com/timeline_saveText.action";
	public static void main(String[] args) {
		URL url=null;
		HttpURLConnection connection;
		try {
			url=new URL(str);			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			// 为post放入请求参数
			String content = "e=" + URLEncoder.encode("15010", "utf-8")+";"+"m=" + URLEncoder.encode("000000", "utf-8");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(content); 
			out.flush();
			out.close(); // 请求url
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			connection.disconnect();
			//获取cookie
			Map<String,List<String>> map=connection.getHeaderFields();
			Set<String> set=map.keySet();
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				System.out.println(key);if(key!=null){
					if (key.equals("Set-Cookie")) {
						List<String> list = map.get(key);
						StringBuilder builder = new StringBuilder();
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
						for (String str : list) {
							builder.append(str+"\n").toString();
						}
						System.out.println("cookie內容为"+builder);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {			
			url=new URL(sendUrl);			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			StringBuffer sb = new StringBuffer();
			sb.append("Host: "+sendUrl+"\r\n");
			sb.append("Accept-Language:zh-cn\r\n");
			sb.append("ContentType:text.ztFlag=false;text.textTitle=fasd;text.textContent=sfsafdg;e=15010;m=000000");
			//sb.append("Connection:Keep-Alive\r\n");
			//请求头结束
			out.writeBytes(sb.toString()); 
			out.flush();
			out.close(); // 请求url
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			System.out.println("发送飞鸽");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			};
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     

	}
}
