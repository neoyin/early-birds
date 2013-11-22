package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSHNetworkRequest {

	private static String respond = "";
	private static String title = "";
	private static final String TITLE_REGEX = "(?<=<title>).*?(?=</title>)";
	private static String userName = "";
	private static final String USERNAME_REGEX = "<a\\s+class=\"usernameLong\"\\s+title=\"查看TA的立方时空\"\\s+href=(\"([^\"]*)\")>.*?</a>";
	private static String longNO = "";
	private static final String LONGNO_REGEX = "(?<=<span>\\[).*?(?=\\]</span>)";
	private static String content = "";
	private static final String CONTENT_REGEX = "<div\\s+class=\"mini_content\">.*?</div>";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputHtml();
		getInfo();
		put();
	}
	/**
	 * 得到页面信息
	 */
	public static void inputHtml(){
		String gateway_url = "http://www.l99.com/3267389";
		HttpURLConnection conn = null;
		try
		{
		    URL url = new URL(gateway_url);
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("User-agent","Mozilla/5.0 (Linux; Android 4.2.1; Nexus 7 Build/JOP40D) Gecko/20100101 Firefox/22.0");
		    conn.connect();
		    BufferedReader reader = new BufferedReader(new  InputStreamReader(conn.getInputStream()));
		    String lines = "";
		    while  ((lines  =  reader.readLine())  !=  null )
		    {
		        respond += lines.trim();
		    }
		    reader.close();
		    conn.disconnect();
		} catch (Exception e) {
		    respond = e.getMessage();
		} finally {
		    if (conn != null) {
		        conn.disconnect();
		    }
		    conn =null;
		}
	}
	/**
	 * 匹配
	 * @param line
	 * @param matching
	 * @return
	 */
	public static String isContain(String line,String matching) {
		String result = null;
		Pattern p = Pattern.compile(matching);
		Matcher match = p.matcher(line);
		if(match.find()) {
			result = match.group();
		}
		return result;
	}
	/**
	 * 获取信息
	 */
	public static void getInfo() {
		title = isContain(respond,TITLE_REGEX);
		userName = isContain(respond,USERNAME_REGEX);
		longNO = isContain(respond,LONGNO_REGEX);
		content = isContain(respond,CONTENT_REGEX);
		deal();
	}
	/**
	 * 处理
	 */
	public static void deal() {
		userName = userName.replaceAll("<.*?>", "");
		content = content.replaceAll("<.*?>", "");
	}
	/**
	 * 输出
	 */
	public static void put() {
		System.out.println("立方时空标题：" + title);
		System.out.println("用户名：" + userName);
		System.out.println("龙号：" + longNO);
		System.out.println("第一篇飞鸽内容：" + content);
	}
}
