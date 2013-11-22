package com.lifeix.d20131026;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class LJApp {
	public static List<String> names;
	public List<String> getPage(String u) {
		List<String> list = new ArrayList<String>();
		URL url;
		int responsecode;
		HttpURLConnection urlConnection;
		BufferedReader reader;
		String line;
		try {
			url = new URL(u);
			// 打开URL
			urlConnection = (HttpURLConnection) url.openConnection();
			// 获取服务器响应代码
			responsecode = urlConnection.getResponseCode();
			if (responsecode == 200) {
				// 得到输入流，即获得了网页的内容
				reader = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream(), "UTF-8"));
				while ((line = reader.readLine()) != null) {
					list.add(line);
				}
			} else {
				System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);
			}
		} catch (Exception e) {
			System.out.println("获取不到网页的源码,出现异常：" + e);
		}
		return list;
	}

	public void write(List<String> list, String name, String path, String datePath, String newName) {
		try {
			this.replace(name, path, datePath, newName);
			FileWriter f = new FileWriter(path+name, false);
			BufferedWriter w = new BufferedWriter(f);
			for (int i = 0; i < list.size(); i++) {
				w.write(list.get(i));
				w.newLine();
			}
			w.flush();
			w.close();
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void replace(String name, String path, String datePath, String newName) {
		try {
			File source = new File(path+name);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(source)));
			new File(path+datePath).mkdirs();
			File desc = new File(path+datePath+newName);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(desc)));

			String str = null;
			while ((str = in.readLine()) != null) {
				out.write(str);
				out.newLine();
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd/");
		return df.format(new Date());
	}

	public String getName() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		return builder.append("media-").append(df.format(new Date()))
				.append(".html").toString();
	}

	public static void main(String args[]) {
		names = new ArrayList<String>();
		names.add("");
		names.add("art");
		names.add("car");
		names.add("autos");
		names.add("design");
		names.add("tech");
		names.add("history");
		names.add("music");
		names.add("movie");
		names.add("reading");
		names.add("self-portrait");
		names.add("sex");
		names.add("fun");
		names.add("travel");
		names.add("gif");
		names.add("finance");
		names.add("sports");
		names.add("competition");
		names.add("photograph");
		names.add("game");
		names.add("food");
		names.add("animation");
		names.add("cartoon");
		names.add("architecture");
		names.add("gossip");
		names.add("app");
		names.add("news");
		names.add("star");
		names.add("jobs");
		names.add("fashion");
		names.add("military");
		names.add("wisdom");
		names.add("wine");
		names.add("basketball");
		names.add("pets");
		names.add("golf");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LJApp app = new LJApp();
//				for(int i=0;i<LJApp.names.size();i++){
					app.write(app.getPage("http://www.l99.com/media_index.action"), "media.html", "/home/abc/workspace/l06-static/static/temp/", app.getPath(), app.getName());
//				}
			}
		}, 0, 30*1000);
	}

}
