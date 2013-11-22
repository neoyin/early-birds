package com.lifeix.d20131102;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;

public class AWJ_Log {

	private static Map<String,Integer> ips = new HashMap<String,Integer>();

	private static Map<String,Integer> urls = new HashMap<String,Integer>();

	private static Map<String, Integer> browsers = new HashMap<String, Integer>();

	private static Map<String, Integer> systems = new HashMap<String, Integer>();

	public static void main(String[] args) {
		AWJ_Log log = new AWJ_Log();
		log.readLog();
		Entry [] ipMap = getSortedHashtableByValue(ips);
		System.out.println("IP访问前10");
		for(int i=ipMap.length; i>ipMap.length-10;i--){
			System.out.println(ipMap[i-1].getKey()+"访问量："+ipMap[i-1].getValue());
		}

		Entry [] urlMap = getSortedHashtableByValue(urls);
		System.out.println("页面访问前10");
		for(int i=urlMap.length; i>urlMap.length-10;i--){
			System.out.println(urlMap[i-1].getKey()+"页面访问量："+urlMap[i-1].getValue());
		}
		
		Entry [] browserMap = getSortedHashtableByValue(browsers);
		System.out.println("浏览器排行");
		for(int i=browserMap.length; i>0;i--){
			System.out.println(browserMap[i-1].getKey()+"浏览器："+browserMap[i-1].getValue());
		}
		
		//String content ="r\"effdsf\"fdsf\"dsf\"fdsf";
		//System.out.println(content.substring(getCharacterPosition(content, 1)+1,getCharacterPosition(content, 2)));
	}

	/**
	 * 提取IP
	 * @param content
	 * @return
	 */
	public String getIP(String content){
		String regexString="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(content);
		boolean result = m.find();
		String ip = "";
		while(result) {
			ip = m.group();
			break;
		}
		return ip;
	}

	/**
	 * 提取URL
	 * @param content
	 * @return
	 */
	public String getURL(String content){
		Pattern pattern = Pattern.compile("http://[\\w\\.\\-/:]+");
		Matcher matcher = pattern.matcher(content);
		StringBuffer buffer = new StringBuffer();
		while(matcher.find()){              
			buffer.append(matcher.group());
			break;
		}   
		return buffer.toString();
	}

	/**
	 * 文件读取
	 */
	public void readLog(){

		SshClient client=new SshClient();
		try{
			client.connect("192.168.1.110");
			//设置用户名和密码
			PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
			pwd.setUsername("root");
			pwd.setPassword("skstserver");
			int result=client.authenticate(pwd);
			if(result==AuthenticationProtocolState.COMPLETE){//如果连接完成
				//List<SftpFile> list = client.openSftpClient().ls("/tmp");
				SftpFile f = new SftpFile("/tmp/access.www.l99.com_20131101.log");
				//OutputStream os = new FileOutputStream("d:/"+f.getFilename());
				//client.openSftpClient().get("/tmp/access.www.l99.com_20131101.log", os);
				//以行为单位读取文件start
				File file = new File("d:/access.www.l99.com_20131101.log");
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					String tempString = null;
					int line = 1;//行号
					//一次读入一行，直到读入null为文件结束
					while ((tempString = reader.readLine()) != null) {
						String ip = getIP(tempString);
						if(ips.containsKey(ip)){
							ips.put(ip, ips.get(ip)+1);
						}else{
							ips.put(ip, 1);
						}
						String url = getURL(tempString);
						if(url != null && !url.equals("")){
							if(urls.containsKey(url)){
								urls.put(url, urls.get(url)+1);
							}else{
								urls.put(url, 1);
							}
						}
						String user_agent = tempString.substring(getCharacterPosition(tempString, 5, "\"")+1,getCharacterPosition(tempString, 6,  "\""));
						getBrowser(user_agent);
						line++;
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e1) {
						}
					}
				}
				//以行为单位读取文件end
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 排序
	 * @param map
	 * @return
	 */
	public static Entry[] getSortedHashtableByValue(Map<String, Integer> map) { 
		Set set = map.entrySet(); 
		Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]); 
		Arrays.sort(entries, new Comparator() { 
			public int compare(Object arg0, Object arg1) { 
				Long key1 = Long.valueOf(((Entry) arg0).getValue().toString()); 
				Long key2 = Long.valueOf(((Entry) arg1).getValue().toString()); 
				return key1.compareTo(key2); 
			} 
		}); 
		return entries; 
	} 


	public void getBrowser(String content){
		//content = content.toLowerCase();
		if(content.toLowerCase().indexOf("msie") !=-1){
			if(browsers.containsKey("IE")){
				browsers.put("IE", browsers.get("IE")+1);
			}else{
				browsers.put("IE", 1);
			}
		}else if(content.toLowerCase().indexOf("firefox") != -1){
			if(browsers.containsKey("FireFox")){
				browsers.put("FireFox", browsers.get("FireFox")+1);
			}else{
				browsers.put("FireFox", 1);
			}
		}else if(content.toLowerCase().indexOf("chrome") != -1){
			if(browsers.containsKey("Chrome")){
				browsers.put("Chrome", browsers.get("Chrome")+1);
			}else{
				browsers.put("Chrome", 1);
			}
		}else{
			if(browsers.containsKey("Other")){
				browsers.put("Other", browsers.get("Other")+1);
			}else{
				browsers.put("Other", 1);
			}
		}
	}

	/**
	 * 获取第n个指定字符串出现的位置
	 * @param string
	 * @param n
	 * @param type
	 * @return
	 */
	public static int getCharacterPosition(String string, int n , String type){
		
		Matcher slashMatcher = Pattern.compile(type).matcher(string);
		int mIdx = 0;
		while(slashMatcher.find()) {
			mIdx++;
			if(mIdx == n){
				break;
			}
		}
		return slashMatcher.start();
	}
}
