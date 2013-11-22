package com.lifeix.d20130810;

import java.io.BufferedInputStream;
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  
public class LjApp {  
    
    /**
     * 获取字符串之间的内容，如果包含嵌套，则返回最外层嵌套内容  
     * @param data
     * @param stag
     * @param etag
     * @return
     */
    private static List<String> get(String data,String stag, String etag){  
        // 存放起始节点，用于和结束节点匹配  
        Stack<Tag> work = new Stack<Tag>();  
        // 保存所有起始和结束节点  
        List<Tag> allTags = new ArrayList<Tag>();  
          
        // 在元字符前加转义符  
        String nstag = stag.replaceAll("([\\*\\.\\+\\(\\]\\[\\?\\{\\}\\^\\$\\|\\\\])", "\\\\$1");  
        String netag = etag.replaceAll("([\\*\\.\\+\\(\\]\\[\\?\\{\\}\\^\\$\\|\\\\])", "\\\\$1");  
          
        String reg = "((?:"+nstag+")|(?:"+netag+"))";  
          
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);  
          
        Matcher m = p.matcher(data);  
          
        while(m.find()){  
            Tag tag = new Tag(m.group(0),m.start(),m.end());  
            allTags.add(tag);  
        }  
        // 保存开始结束节点之间的内容，不含节点  
        List<String> result = new ArrayList<String>();  
          
        for(Tag t : allTags){  
        	try{
	            if (stag.equalsIgnoreCase(t.getValue())){  
	                work.push(t);  
	            }else if(etag.equalsIgnoreCase(t.getValue())){  
	                // 如果栈已空，则表示不匹配  
	                if (work.empty()){  
	                    throw new RuntimeException("pos "+t.getBeginPos()+" tag not match start tag.");  
	                }  
	                Tag otag = work.pop();  
	                // 如果栈为空，则匹配  
	                if (work.empty()){  
	                    String sub = data.substring(otag.getEndPos(), t.getBeginPos());  
	                    result.add(sub);  
	                }  
	            }  
        	}catch(Exception e){
//        		e.printStackTrace();
        	}
        } 
        try{
	        // 如果此时栈不空，则有不匹配发生  
	        if (!work.empty()){  
	            Tag t = work.pop();  
	            throw new RuntimeException("tag "+t.getValue()+ "not match.");  
	        }  
        }catch(Exception e){
        	
        }
        return result;  
    } 
    
    /**
     * 获取html
     * @param address
     * @return
     */
    private static String getHtml(String address){
    	StringBuffer html = new StringBuffer();
    	String result = null;
    	try{
    		URL url = new URL(address);
    		URLConnection conn = url.openConnection();
    		//伪装系统和浏览器
    		conn.setRequestProperty("User-Agent","Mozilla/5.0 (X11; U; Linux i586; en-US; rv:1.7.3) Gecko/20040924 FireFox/3.6.3 (Ubuntu)");
    		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
    		try {
    			String inputLine;
    			byte[] buf = new byte[4096];
    			int bytesRead = 0;
    			while (bytesRead >= 0) {
    				inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
    				html.append(inputLine);
    				bytesRead = in.read(buf);
    				inputLine = null;
    			}
    			buf = null;
    		} finally {
    			in.close();
    			conn = null;
    			url = null;
    		}
    		result = new String(html.toString().trim().getBytes("ISO-8859-1"), "utf-8").toLowerCase();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	html = null;
    	return result;
    }
    
    public static void main(String[] args) throws Exception {  
    	Long startTime = System.currentTimeMillis();
    	//代理IP
    	String eUrl = "154.61.135.179.167";
    	String port = "8080";
    	String url = "http://www.l99.com/3209027";
    	System.getProperties().setProperty("http.proxyHost", eUrl);
    	System.getProperties().setProperty("http.proxyPort", port);
		String all = getHtml(url);
		List<String> station_name = get(all,"class=\"station_name\">", "</span>");
		List<String> name = get(all,"title=\"查看TA的立方时空\">", "</a>");	
		List<String> longNO = get(all,">[", "]</span>");	
		List<String> find = get(all,"<li class=\"post\">", "</li>");
		
		String station = "未查找到";
		String accountName = "未查找到";
		String l99NO = "未查找到";
		String dashboard = "未查找到";
		if(station_name != null && station_name.size() > 0){
			station = station_name.get(0);
		}
		if(name != null && name.size() > 0){
			accountName = name.get(0);
		}
		if(longNO != null && longNO.size() > 0){
			l99NO = longNO.get(0);
		}
		if(find != null && find.size() > 0){
			find = get(find.get(0),"class=\"source_url\"","</a>");
			find = get(find.get(0),"href=\"","\"");
			String newUrl = find.get(0).replace("postid", "postId");
			String content = getHtml(newUrl);
			find = get(content,"<div class=\"post_desc \">","</div>");
			dashboard = find.get(0);
		}
		
		System.out.println("时空标题:"+station);
		System.out.println("用户名:"+accountName);
		System.out.println("龙号:"+l99NO);
		System.out.println("飞鸽内容:"+dashboard);
		Long endTime = System.currentTimeMillis();
		System.out.println("用时:"+((endTime - startTime)/1000));
		
    }  
    
}

