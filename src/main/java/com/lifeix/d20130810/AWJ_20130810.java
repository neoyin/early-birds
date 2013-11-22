package com.lifeix.d20130810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AWJ_20130810
{
	private static final String TITLE_REGEX   = "<title>.*?</title>";
	private static final String SPACE_REGEX   = "<span class=\"station_name\">.*?</span>";
	private static final String NAME_REGEX    = "<a class=\"usernameLong\".*?</a>";
	private static final String LONG_REGEX    = "<div class=\"lst_username\"><a class=\"usernameLong\".*?</div>";
	private static final String CONTENT_REGEX = "<div class=\"post_desc \">(.*?)</div>";
	private static final String USER_AGENT    = "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10";

	public static void main(final String args[])
	{
		String url = "";
		final List<String> list = new ArrayList<String>();
		System.out.print("输入立方网空间地址，一行一个，输入结束后输入 go 程序开始运行:   \n");
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			while (!(url = br.readLine()).equals("go"))
			{
				list.add(url);
			}
		}
		catch (final Exception e)
		{
			e.getMessage();
		}
		final AWJ_20130810 awj = new AWJ_20130810();
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++)
		{
			map = awj.getContent(list.get(i));
			System.out.println("标题："    +map.get("title"));
			System.out.println("名称："    +map.get("name"));
			System.out.println("龙号："    +map.get("long"));
			System.out.println("空间名称："+map.get("space_name"));
			System.out.println("内容： \n" +map.get("post_desc").trim());
		}

	}


	/**
	 * 读取一个网页全部内容
	 * @param htmlurl
	 * @return
	 * @throws IOException
	 */
	public String getHtml(final String htmlurl) throws IOException
	{	
		URLConnection connection = null;
		String temp;
		final StringBuffer sb = new StringBuffer();
		try
		{	
			connection = new URL(htmlurl).openConnection();
			connection.setRequestProperty("user-agent", USER_AGENT);
			final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 读取网页全部内容
			while ((temp = in.readLine()) != null)
			{
				sb.append(temp);
			}
			in.close();
		}
		catch (final MalformedURLException e)
		{
			System.out.println("你输入的URL格式有问题！请仔细输入");
			e.getMessage();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param s
	 * @return 获取网页标题
	 */
	public String getTitle(final String s)
	{
		String title = "";
		final List<String> list = new ArrayList<String>();
		final Pattern pa = Pattern.compile(TITLE_REGEX, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		while (ma.find())
		{
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++)
		{
			title = title + list.get(i);
		}
		return deleteTag(title);
	}

	/**
	 * 
	 * @param s
	 * @return 去掉标记
	 */
	public String deleteTag(final String s)
	{
		return s.replaceAll("<.*?>", "");
	}

	/**
	 * 获取立方网我的时空最新随笔
	 * @param s
	 * @return
	 */
	public HashMap<String, String> getContent(final String s)
	{
		final HashMap<String, String> maps = new HashMap<String, String>();
		final StringBuffer sb = new StringBuffer();
		String html = "";
		System.out.println("\n------------------开始读取网页(" + s + ")--------------------");
		try
		{
			html = getHtml(s);
		}
		catch (final Exception e)
		{
			e.getMessage();
		}
		System.out.println("------------------读取读取网页(" + s + ")结束--------------------\n");
		System.out.println("------------------获取(" + s + ")结果如下--------------------\n");
		String title = deleteTag(getTitle(html));
		String spaceName = getSpaceName(html);
		String longNO = getLong(html);
		String name = getName(html);
		final Pattern pa = Pattern.compile(CONTENT_REGEX, Pattern.DOTALL);
		final Matcher ma = pa.matcher(html);
		if(ma.find())
		{
			sb.append(ma.group());
		}
		String temp = sb.toString();
		temp = temp.replaceAll("<br>", "\n");// 转化换行
		temp = temp.replaceAll("</p>", "\n");// 替换p结束标签为换行
		temp = (temp.trim() != null || !temp.trim().equals("")) ? temp : "没有内容";
		maps.put("title", title);
		maps.put("space_name", spaceName);
		maps.put("post_desc", deleteTag(temp.trim()));
		maps.put("name", name.trim());
		maps.put("long", longNO.trim());
		return maps;

	}

	/**
	 * 获取立方空间名字
	 * @param s
	 * @return
	 */
	public String getSpaceName(final String s)
	{
		String spaceName = "";
		final List<String> list = new ArrayList<String>();
		final Pattern pa = Pattern.compile(SPACE_REGEX, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		if (ma.find())
		{
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++)
		{
			spaceName = spaceName + list.get(i);
		}
		return deleteTag(spaceName);
	}

	/**
	 * 获取名字
	 * @param s
	 * @return
	 */
	public String getName(final String s)
	{
		String spaceName = "";
		final List<String> list = new ArrayList<String>();
		final Pattern pa = Pattern.compile(NAME_REGEX, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		if (ma.find())
		{
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++)
		{
			spaceName = spaceName + list.get(i);
		}
		return deleteTag(spaceName);
	}

	/**
	 * 获取龙号
	 * @param s
	 * @return
	 */
	public String getLong(final String s)
	{
		String longNumber = "";
		final List<String> list = new ArrayList<String>();
		final Pattern pa = Pattern.compile(LONG_REGEX, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		if (ma.find())
		{
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++)
		{
			longNumber = longNumber + list.get(i);
		}
		longNumber = longNumber.substring(longNumber.indexOf("[")+1,longNumber.indexOf("]"));
		return deleteTag(longNumber);
	}

}
