package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Zhch2 {

	/**
	 * 获取GET内容
	 * 
	 * @param getURL
	 * @return
	 * @throws IOException
	 */
	public String readContentFromGet(String getURL) throws IOException {
		URL getUrl = new URL(getURL);
		// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
		// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestProperty("user-agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0");
		connection.connect();
		// 取得输入流，并使用Reader读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
		String lines;
		StringBuilder sb = new StringBuilder();
		while ((lines = reader.readLine()) != null) {
			// sb.append(lines);
			sb.append(lines + System.getProperty("line.separator"));
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public String readFile() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(new File("/home/lifeix/temp/d3/aa")));// 设置编码,否则中文乱码
			String lines;
			StringBuilder sb = new StringBuilder();
			while ((lines = reader.readLine()) != null) {
				// sb.append(lines);
				sb.append(lines + System.getProperty("line.separator"));
			}
			reader.close();
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) throws IOException {
		Zhch2 zhch = new Zhch2();
		String url = "http://www.l99.com/3079271";
		// System.out.println(readContentFromGet(url));
		String content = zhch.readFile();
		// System.out.println(content);
	}
}

class Element {
	private String name;
	private List<String> classes;
	private List<Element> subEles;

	public Element(String content) {
		parseContent(content);
	}

	private void parseContent(String content) {
		String nameBegin = "";
		String nameEnd = "";
		boolean nameStart = false;
		boolean endNameStart = false;
		boolean contentStart = false;
		StringBuilder html = new StringBuilder();
		StringBuilder subhtml = new StringBuilder();
		char pre = 0;
		char c = 0;
		int nameCount = 0;
		for (int i = 0; i < content.length(); i++) {
			pre = c;
			c = content.charAt(i);
			if (contentStart) {
				html.append(c);
				subhtml.append(c);
			}
			if (c == '<') {
				nameStart = true;
				nameCount++;
				continue;
			}

			if (c == ' ' || c == '>') {
				nameStart = false;
				if(endNameStart){
					nameCount--;
				}
				endNameStart = false;
				if(nameCount ==1 && nameBegin==nameEnd){
					
				}
			}

			if (c == '/') {
				if (nameStart) {
					endNameStart = true;
					continue;
				}
			}

			if (nameStart) {
				nameBegin += c;
			} else if (endNameStart) {
				nameEnd += c;
			}
			
			if (c == '>') {
				if (!contentStart) {
					contentStart = true;
					this.name = nameBegin;
				} else {

				}
			}
			if (nameStart) {
				nameBegin += c;
				continue;
			}

		}
	}
}
