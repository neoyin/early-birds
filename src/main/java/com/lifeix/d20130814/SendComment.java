package com.lifeix.d20130814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SendComment {

	private static final String CHARSET = "UTF-8";
	
	private HttpURLConnection getConnection(String url) throws MalformedURLException, IOException {
		URLConnection conn = new URL(url).openConnection();
		return (HttpURLConnection)conn;
	}
	
	/**
	 * @description: 登录	
	 * @return:List<String>
	 * @param params
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private List<String> getCookies(Map<String, String> params, String url) throws MalformedURLException, IOException {
		if(params.size() > 0) {
			url += "?";
			for(Entry<String, String> entry : params.entrySet()) {
				url += entry.getKey() + "=" + entry.getValue() + "&";
			}
		}
		HttpURLConnection conn = getConnection(url);
		conn.setRequestProperty("Accept-Charset", CHARSET);
		List<String> list = conn.getHeaderFields().get("Set-Cookie");
		conn.disconnect();
		return list;
	}
	
	/**
	 * @description: 发随笔	
	 * @return:void
	 * @param url
	 * @param dataMap
	 * @param list
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void sendText(String url, Map<String, String> dataMap, List<String> list) throws MalformedURLException, IOException {
		System.out.println("start send...............");
		HttpURLConnection conn = getConnection(url);
		
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);
//		conn.setRequestProperty("referer", "http://www.l99.com");
		
		StringBuffer sb = new StringBuffer();
		for(String str : list) {
			sb.append(str).append(";");
		}
		conn.setRequestProperty("Cookie", sb.toString());
		StringBuffer param = new StringBuffer();
		for(Entry<String,String> entry : dataMap.entrySet()) {
			param.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		OutputStream os = conn.getOutputStream();
		os.write(param.toString().getBytes());
		os.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		while((line = br.readLine()) != null) {
			System.out.println(line);
		}
		conn.disconnect();
		System.out.println("send successfully");
	}
	
	/**@description:	
	 * @return:void
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */

	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://www.l99.com/EditAccount_login.action";
		String messageUrl = "http://www.l99.com/Comm_reply.action";
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("boardId","4299859");
		dataMap.put("content","abcd");
		dataMap.put("push", "false");
		Map<String, String> map = new HashMap<String, String>(){{put("e","3251354");put("m","321654");}};
		SendComment sm = new SendComment();
		sm.sendText(messageUrl, dataMap, sm.getCookies(map, url));
	}

}
