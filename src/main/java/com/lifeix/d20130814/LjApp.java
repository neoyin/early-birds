package com.lifeix.d20130814;

import java.io.IOException;

//import org.apache.commons.httpclient.Cookie;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.cookie.CookiePolicy;
//import org.apache.commons.httpclient.cookie.CookieSpec;
//import org.apache.commons.httpclient.methods.PostMethod;


public class LjApp {
	static final String LOGON_SITE = "http://www.l99.com";
	static final int LOGON_PORT = 8081;
	static final String loginurl = "http://www.l99.com/EditAccount_login.action";
	static final String loginparematername = "e";
	static final String loginparematerpass = "m";
	static final String username = "3209027";
	static final String password = "19881225";
	   
	static final String sendUrl = "http://www.l99.com/timeline_saveText.action";
//	   
//	/**
//	 * 登录   
//	 * @param LOGON_SITE
//	 * @param LOGON_PORT
//	 * @param loginurl
//	 * @param loginparematername
//	 * @param loginparematerpass
//	 * @param username
//	 * @param password
//	 * @return
//	 * @throws IOException
//	 * @throws HttpException
//	 */
//	private static HttpClient imitateLogin(String LOGON_SITE, int LOGON_PORT,
//			String loginurl,String loginparematername,String loginparematerpass,String username,String password) throws IOException, HttpException {
//		HttpClient client = new HttpClient();
//	    client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);
//	    
//	    // 模拟登录页面
//	    PostMethod post = new PostMethod(loginurl);
//	    NameValuePair name = new NameValuePair(loginparematername,username );
//	    NameValuePair pass = new NameValuePair(loginparematerpass,password );
//	    post.setRequestBody(new NameValuePair[] { name, pass });
//	    client.executeMethod(post);
//	    System.out.println(post.getResponseBodyAsString());
//	    post.releaseConnection();
//	    // 查看cookie信息
//	    CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
//	    Cookie[] cookies = cookiespec.match(LOGON_SITE, LOGON_PORT, "/", false,
//	    		client.getState().getCookies());
//	    if (cookies != null){
//	    	if (cookies.length == 0) {
//	    		System.out.println("None");
//           	} else {
//           		for (int i = 0; i < cookies.length; i++) {
//           			System.out.println(cookies[i].toString());
//           		}
//           	}
//	    }
//	    return client;
//	}
//	   
//	/**
//	 *发飞鸽
//	 * @param client
//	 * @param getUrl
//	 * @throws IOException
//	 * @throws HttpException
//	 */
//	private static void imitateGetUrl(HttpClient client, String getUrl) throws IOException, HttpException {
//		PostMethod post = new PostMethod(getUrl);
//	    String content = new String("终于饿了".getBytes("utf-8"), "ISO-8859-1");	//随笔内容
//	    String title = new String("".getBytes("utf-8"), "ISO-8859-1");		//随笔标题
//	       
//	    NameValuePair textContent = new NameValuePair("text.textContent", content);
//	    NameValuePair textTitle = new NameValuePair("text.textTitle", title);
//	    post.setRequestBody(new NameValuePair[] {textTitle,textContent});
//	    client.executeMethod(post);
//	    System.out.println(post.getResponseBodyAsString());
//	    post.releaseConnection();
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		HttpClient client = imitateLogin(LOGON_SITE, LOGON_PORT, loginurl, loginparematername, loginparematerpass, username, password);
//		// 访问所需的页面
//		imitateGetUrl(client, sendUrl);
//	}
}

