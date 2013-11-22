package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

public class Zhch {

	private Pattern titlePattern = Pattern.compile("<.*>(.*)<.*>");
	private Pattern namePattern = Pattern.compile("<a.*?>(.*)</a>");
	private Pattern longnoPattern = Pattern.compile("\\[(.*)\\]");

	/**
	 * 获取GET内容
	 * 
	 * @param getURL
	 * @return
	 * @throws IOException
	 */
	public List<String> readContentFromGet(String getURL) throws IOException {
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
		List<String> list = new ArrayList<String>();
		while ((lines = reader.readLine()) != null) {
			// sb.append(lines);
			list.add(lines);
		}
		reader.close();
		connection.disconnect();
		return list;
	}

	public List<String> readFile() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(new File("/home/lifeix/temp/d3/aa")));// 设置编码,否则中文乱码
			String lines;
			List<String> list = new ArrayList<String>();
			while ((lines = reader.readLine()) != null) {
				// sb.append(lines);
				list.add(lines);
			}
			reader.close();
			return list;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	public void printContent(List<String> list) {
		boolean infoStart = false;
		int infoIndex = 0;
		int contentIndex = 0;
		String content = "";
		for (String line : list) {
			if (line.contains("lst_stationname_title") ) {
				Matcher matcher = titlePattern.matcher(line);
				if (matcher.find()) {
					System.out.println("标题:" + matcher.group(1));
				}
			}
			if (line.contains("lst_username") && infoIndex == 0) {
				infoIndex++;
			}
			if (line.contains("lst_username") && infoIndex == 1) {
				infoIndex++;
				Matcher matcher = namePattern.matcher(line);
				if (matcher.find()) {
					System.out.println("用户名:" + matcher.group(1));
				}
				matcher = longnoPattern.matcher(line);
				if (matcher.find()) {
					System.out.println("龙号:" + matcher.group(1));
				}
			}
			if (line.contains("lst_contentlist") && contentIndex==0) {
				contentIndex++;
			}else
		
			
			if (line.contains("lst_contentlist") && contentIndex==1) {
				contentIndex++;
				if(contentIndex == 2){
					System.out.println("第一个飞鸽:");
					System.out.println(content);
				}
			}
			if (contentIndex==1) {
				content += line + System.getProperty("line.separator");
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		Zhch zhch = new Zhch();
		String url = "http://www.l99.com/3079271";
		// System.out.println(readContentFromGet(url));
//		List<String> content = zhch.readFile();
		List<String> content = zhch.readContentFromGet(url);
		zhch.printContent(content);
	}
}
