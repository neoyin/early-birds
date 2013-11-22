package com.lifeix.d20131009;

import java.text.DateFormat;
import java.util.Date;

/**
 * 相关常量
 * @author ANWJ
 *
 */
public class AWJ_ChatUtil {

	/**
	 * 系统图标图片位置
	 */
	public final static String LOGO_IMAGE = "logo.png";

	public final static String SERVER_IP = "127.0.0.1";

	/**
	 * 用户上线消息头
	 */
	public static final String MSG_LOGIN = "LOGIN&&";

	/**
	 * 用户下线消息头
	 */
	public static final String MSG_EXIT = "EXIT&&";

	/**
	 * 获取用户列表消息头
	 */
	public static final String MSG_USERLIST = "USERLIST&&";

	
	public static String SEND_INFO = null;

	/**
	 * 消息数组
	 */
	public static String[] infos = null;

	public static DateFormat df = null;

	public static String[] formatInfos(String info) throws Exception{
		String[] str0 = info.split("--");//分离sender
		String[] str1 = str0[1].split("##");//分离receiver
		String[] str2 = str1[1].split("&&");//分离send time

		infos = null;
		infos = new String[4];
		infos[0] = str0[0];//sender
		infos[1] = str1[0];//receiver
		infos[2] = str2[0];//send time
		infos[3] = str2[1];//message

		return infos;
	}

	/**
	 * 组装发送至服务器的消息
	 * @param msg
	 * @return
	 */
	public static String fittingSendMsg(String sender, String receiver, Date now, String msg){
		SEND_INFO = null;
		SEND_INFO = sender + "--" + receiver + "##" + getDateString(now) + " " + getTimeString(now)
				+ "&&" + msg;

		return SEND_INFO;
	}

	/**
	 * 登录消息
	 * @param user
	 * @return
	 */
	public static String fittingLoginMsg(String user){
		return MSG_LOGIN + user;
	}

	/**
	 * 退出消息
	 * @param user
	 * @return
	 */
	public static String fittingExitMsg(String user){
		return MSG_EXIT + user;
	}

	/**
	 * 返回日期字符串
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date){
		df = DateFormat.getDateInstance();
		return df.format(date);
	}

	/**
	 * 返回时间字符串
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date){
		df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		return df.format(date);
	}
}
