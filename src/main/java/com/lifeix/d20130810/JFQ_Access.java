package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @DESCRIPTION:	
 * @DATE:2013-8-10 上午10:46:40
 **/
public class JFQ_Access {

	private static final String agent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.0.8) Gecko/2009032711 Ubuntu/8.10 (intrepid) Firefox/22.0";
	private static final String url = "http://www.l99.com/3289814";
	private static final String titleReg = "<span class=\"station_name\">.+</span>";
	private static final String longReg = "<div class=\"lst_username\"><a class=\"usernameLong\".*</div>";
	private static final String contentReg = "<li class=\"post\">";
	
	private URLConnection getConnection(String url) throws MalformedURLException, IOException {
		URLConnection conn = new URL(url).openConnection();
		conn.setRequestProperty("User-agent", agent);
		return conn;
	}
	
	private void readTimeLine(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Pattern patt = Pattern.compile(titleReg);
		Pattern longPatt = Pattern.compile(longReg);
		Pattern divpatt = Pattern.compile(contentReg);
		boolean flag = true;
		boolean isFirst = false;
		
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null) {
			line = line.trim();
			Matcher m = patt.matcher(line);
			if(m.find()) {
				readTitle(m.group());
				continue;
			}
			Matcher longM = longPatt.matcher(line);
			if(longM.find()) {
				readNameAndlongNo(longM.group());
				continue;
			}
			
			if(flag) {
				Matcher divM = divpatt.matcher(line);
				if(divM.find()) {
					isFirst = true;
					sb.append(divM.group());
				} else if(isFirst){
					if(line.indexOf("</li>") >= 0) {
						flag = false;
						sb.append(line);
					} else {
						sb.append(line);
					}
				}
				continue;
			}
			
		}
		readContent(sb.toString());
		br.close();
	}
	
	private void readTitle(String line) {
		line = line.substring(line.indexOf(">") + 1);
		line = line.substring(0, line.indexOf("<"));
		System.out.println("标题为：" + line);
	}
	
	private void readNameAndlongNo(String line) {
		line = line.substring(line.indexOf(">") + 1);
		line = line.substring(line.indexOf(">") + 1);
		String temp = line;
		line = line.substring(0, line.indexOf("<"));
		System.out.print("用户名：" + line + "\t");
		temp = temp.substring(temp.indexOf("[") + 1);
		temp = temp.substring(0,temp.indexOf("]"));
		System.out.println("龙号：" + temp);
	}
	
	private void readContent(String line) throws MalformedURLException, IOException {
		System.out.println(line);
		
	}
	
	/**@description:	
	 * @return:void
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		JFQ_Access jfq = new JFQ_Access();
		jfq.readTimeLine(jfq.getConnection(url).getInputStream());
	}

}

