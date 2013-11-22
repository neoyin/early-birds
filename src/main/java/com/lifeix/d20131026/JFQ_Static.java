package com.lifeix.d20131026;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @DESCRIPTION:	
 * @DATE:2013-10-26 下午02:23:59
 **/
public class JFQ_Static implements Runnable{
	
	private int num = 5;
	private boolean flag = false;
	private static String tempPath = "c:/tmp";
	
	public void run() {
		while(!flag) {
			saveHtml();
			try {
				Thread.sleep(num*1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = true;
			}
		}
	}
	
	public void saveHtml() {
		URLConnection conn = null;
		try {
			conn = new URL("http://www.l99.com/media").openConnection();
			InputStream is = conn.getInputStream();
			writeHtml(is);
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
		} 
	}
	
	private void writeHtml(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tsdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String path = tempPath+"/" + sdf.format(new Date());
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(path+"/"+tsdf.format(new Date())+".html");
		if(!file.exists()) {
			file.createNewFile();
		}
		
		PrintWriter bw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
		String line = "";
		
		while((line = br.readLine()) != null) {
			if(line.indexOf("href=\"http://www.l99.com/media\"") > 0) {
				line = line.replace("http://www.l99.com/media", "file:///" + file.getAbsolutePath());
			} else {
				if(line.indexOf("http://www.l99.com/editors") > 0) {
					String temp = line.substring(line.indexOf("www.l99.com/editors"));
					String n = temp.substring(temp.indexOf("/") + 1,temp.indexOf(">") -1);
					String url = map.get(n);
					String pp = file.getAbsolutePath();
					pp = pp.substring(0, pp.lastIndexOf("\\"));
					line = line.replace(url, "file:///" + pp + "\\" + n + "\\" + file.getName());
				} else if(line.indexOf("href=\"http://www.l99.com/media/") > 0) {
					String temp = line.substring(line.indexOf("http://www.l99.com/media/"));
					temp = temp.substring(0, temp.indexOf(" ") - 1);
					String n = temp.substring(temp.lastIndexOf("/") + 1);
					String url = map.get(n);
					String pp = file.getAbsolutePath();
					pp = pp.substring(0, pp.lastIndexOf("\\"));
					line = line.replace(url, "file:///" + pp + "\\" + n + "\\" + file.getName());
				}
			}
			bw.print(line);
		}
		bw.flush();
		bw.close();
		br.close();
		System.out.println(file.getAbsolutePath());
	}
	
	
	private static Map<String, String> map = new HashMap<String, String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFQ_Static jfq = new JFQ_Static();
		jfq.readXML();
		new Thread(jfq).start();
		for(Entry<String, String> entry : map.entrySet()) {
			new Thread(new CatThread(entry.getValue(), entry.getKey(), tempPath, map)).start();
		}
		
	}
	private void readXML() {
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(new File(this.getClass().getResource("caturl.xml").getFile()));
			Element ele = doc.getDocumentElement();
			NodeList list = ele.getChildNodes();
			for(int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if(node.getNodeType() == 3) {
					continue;
				}
				Element element = (Element) node;
				String name = element.getAttribute("name");
				String url = element.getAttribute("href");
				map.put(name, url);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

class CatThread implements Runnable {
	private String url;
	private String name;
	private String tempPath;
	private boolean flag = false;
	private Map<String, String> map;
	public CatThread(String url, String name, String path,Map<String, String> map) {
		this.url = url;
		this.name = name;
		this.tempPath = path;
		this.map = map;
	}
	
	public void run() {
		while(!flag) {
			saveHtml();
			try{
				Thread.sleep(5*60*1000);
			} catch(InterruptedException e) {
				flag = true;
			}
		}
	}
	
	
	public void saveHtml() {
		URLConnection conn = null;
		try {
			conn = new URL(url).openConnection();
			InputStream is = conn.getInputStream();
			writeHtml(is);
			is.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
		} 
	}
	
	private void writeHtml(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tsdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String path = tempPath+"/" + sdf.format(new Date())  + "/" + name;
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(path+"/"+tsdf.format(new Date())+".html");
		if(!file.exists()) {
			file.createNewFile();
		}
		
		PrintWriter bw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
		String line = "";
		
		while((line = br.readLine()) != null) {
			if(line.indexOf("href=\"http://www.l99.com/media\"") > 0) {
				String pp = file.getAbsolutePath();
				pp = pp.substring(0, pp.indexOf("\\" + name + "\\"));
				line = line.replace("http://www.l99.com/media","file:///" +  pp + "\\" + file.getName());
			} else { 
				
				if(line.indexOf("http://www.l99.com/editors") > 0) {
					String temp = line.substring(line.indexOf("www.l99.com/editors"));
					String n = temp.substring(temp.indexOf("/") + 1,temp.indexOf(">") -1);
					String url = map.get(n);
					String pp = file.getAbsolutePath();
					pp = pp.substring(0, pp.lastIndexOf("\\"));
					pp = pp.substring(0, pp.lastIndexOf("\\"));
					line = line.replace(url, "file:///" + pp + "\\" + n + "\\" + file.getName());
				} else if(line.indexOf("href=\"http://www.l99.com/media/") > 0) {
					String temp = line.substring(line.indexOf("http://www.l99.com/media/"));
					temp = temp.substring(0, temp.indexOf(" ") - 1);
					String n = temp.substring(temp.lastIndexOf("/") + 1);
					String url = map.get(n);
					String pp = file.getAbsolutePath();
					pp = pp.substring(0, pp.indexOf("\\" + name + "\\"));
				
					line = line.replace(url, "file:///" + pp + "\\" + n + "\\" + file.getName());
				}
			}
			bw.print(line);
		}
		bw.flush();
		bw.close();
		br.close();
		System.out.println(file.getAbsolutePath());
	}
	
}
