package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ZDL_RequestUrlTest {
	public static String pageurl = "http://www.l99.com/10515";

	public static String getData(String pageurl) {
		/*
		 * try { PostMethod httppost = null; HttpClient httpclient = new
		 * HttpClient(); httpclient.getParams().setSoTimeout(10 * 60 * 1000);//
		 * 设置超时时间 httppost = new PostMethod(pageurl); HttpMethodParams params =
		 * new HttpMethodParams(); params.setParameter("username", "10515");
		 * params.setParameter("password", "000000");
		 * httppost.setParams(params); httpclient.executeMethod(httppost);
		 * byte[] by = httppost.getResponseBody(); String response = new
		 * String(by); return response; } catch (IllegalArgumentException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		StringBuffer sb=null;
		String inputLineString=null;
		try {
			URL url = new URL(pageurl);
			HttpURLConnection ht = (HttpURLConnection) url.openConnection();
			BufferedReader dis = new BufferedReader(new InputStreamReader(
					ht.getInputStream(), "utf-8"));
			sb =new StringBuffer();
			while ((inputLineString = dis.readLine()) != null) {
				sb.append(inputLineString + "\n");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();

	}

	public static void main(String[] args) {
		String st = getData(pageurl);
		String regEx1 = "(<title>.*?</title>)";// 以<img开头，/>结束
		Pattern pattern1 = Pattern.compile(regEx1);
		Matcher matcher1 = pattern1.matcher(st);
		matcher1.find();
		String title = matcher1.group();
		System.out.println("title"
				+ title.substring(title.indexOf(">") + 1,
						title.lastIndexOf("<")));
		String regEx2 = "(longNO=[0-9]+)";
		Pattern pattern2 = Pattern.compile(regEx2);
		Matcher matcher2 = pattern2.matcher(st);
		matcher2.find();
		String longNO = matcher2.group();
		System.out.println("longNO" + longNO);
		st = st.substring(st.indexOf("<li class=\"post") + 17,
				st.indexOf("</li>"));
		System.out.println("内容为" + st);
	}
}
