package com.lifeix.d20131026;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class AWJ_StaticPage extends TimerTask{

	private final String L99URL = "http://www.l99.com/media";

	private final String PATH = "D:/tmp";


	public void convert() {
		String [] types = {" ","news","fashion","photograph","gossip","design","fun","sex","finance","art","architecture","tech","jobs","competition","app","cartoon","movie","autos","military","reading","bedigest","music","golf","star","travel","wine"};

		File file = new File(PATH);
		if(!file.exists()){
			file.mkdir();
		}
		File [] fiels = file.listFiles();
		if(file.listFiles().length>0){
			System.out.println("开始备份");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String path = "D:/"+sdf.format(new Date());
			File newFilePath = new File(path);
			if(!newFilePath.exists()){
				newFilePath.mkdir();
			}
			for(int i=0; i<fiels.length; i++){
				fiels[i].renameTo(new File(newFilePath+"/"+fiels[i].getName()));
			}
			System.out.println("备份完毕");
		}
		System.out.println("*******开始生成静态页面*******");
		for(int i=0; i<types.length; i++){
			parseHTML(types,L99URL+(!types[i].equals(" ") ? "/"+types[i] : ""), PATH+"/"+(!types[i].equals(" ") ? types[i] : "media")+".html");
		}
		System.out.println("*******生成静态页面结束*******");

	}

	public void parseHTML(String [] types,String l99Url, String  localPath) { 
		try { 
			URL url = new URL(l99Url); 
			URLConnection uc = url.openConnection(); 
			InputStream is = uc.getInputStream(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(localPath)));
			String line = ""; 
			while ((line = br.readLine()) != null) { 
				line = repacleURL(types, line);
				pw.print(line);
			} 
			is.close(); 
			pw.flush();
			pw.close();
			br.close();
			System.out.println(localPath+"静态页面生成完成");
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 

	private String repacleURL(String [] types,String content){
		for(int i=0; i<types.length; i++){
			String type = !types[i].equals(" ") ? "/"+types[i] : " ";
			String l99URL = "href=\""+L99URL+type.trim()+"\"";
			if(content.indexOf(l99URL.trim()) != -1){
				content = content.replace(l99URL, "href=\""+PATH+(!type.equals(" ") ? type : "/media")+".html\"");
				break;
			}
		}
		return content;
	}

	public void run() {
		convert();
	}
}
