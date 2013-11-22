package com.lifeix.d20131009;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 聊天室服务端
 * @author ANWJ
 *
 */
public class AWJ_ChatServer extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8018527690720767833L;

	private JTextArea ta_info;

	private ServerSocket server;

	private Socket socket;

	private int port =  8080;

	private Hashtable<String, Socket> map = new Hashtable<String, Socket>();

	/**
	 * 构造方法
	 */
	public AWJ_ChatServer(){
		super();
		addWindowListener(new WindowAdapter(){
			public void windowIconified(final WindowEvent arg0) {
				System.out.println("最小化窗口时调用");
				setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent e){
				System.out.println("关闭窗口时调用");
				System.exit(1);
			}
		});
		
		URL url =  AWJ_ChatServer.class.getResource(AWJ_ChatUtil.LOGO_IMAGE);
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage();
		
		this.setTitle("立方聊天室服务端");//标题
		this.setBounds(100, 100, 415, 285);//窗口位置及大小
		this.setIconImage(image);//设置窗口图标
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭方式
		final JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);

		ta_info = new JTextArea();
		scrollPane.setViewportView(ta_info);//信息显示区域

		//系统托盘
		if(SystemTray.isSupported()){
			TrayIcon trayIcon = new TrayIcon(image);
			trayIcon.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2){
						showFrame();//双击显示界面
					}
				}

			});
			trayIcon.setToolTip("立方聊天");
			PopupMenu popupMenu = new PopupMenu();//弹出菜单
			MenuItem exit = new MenuItem("EXIT");
			exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);//退出菜单
				}
			});
			popupMenu.add(exit);
			
			trayIcon.setPopupMenu(popupMenu);//添加至托盘图标
			SystemTray st = SystemTray.getSystemTray();//获得系统托盘图标
			try{
				st.add(trayIcon);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置显示
	 */
	public void showFrame(){
		this.setVisible(true);
		this.setState(Frame.NORMAL);
	}

	/**
	 * 服务端socket初始化
	 */
	public void initServer(){
		try {
			server = new ServerSocket(port);
			while(true){
				System.out.println("fuck...........");
				ta_info.append("等待客户连接......\n");
				socket = server.accept();
				ta_info.append("客户端建立连接：" + socket + "。\n");
				new ServerThread(socket).start();//创建
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AWJ_ChatServer frame = new AWJ_ChatServer();
		frame.setVisible(true);
		frame.initServer();
	}


	/**
	 * 服务线程类
	 * @author ANWJ
	 *
	 */
	class ServerThread extends Thread {

		private Socket socket;

		public ServerThread(Socket socket){
			this.socket = socket;
		}

		public void run(){
			try{
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				while(true){
					System.out.println("^^^^^^^^^^^^^");
					Vector v = null;
					try{
						v = (Vector)ois.readObject();
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("=======与客户端通信出现错误=======");
					}
					
					if(v!=null && v.size()>0){
						for(int i=0; i<v.size(); i++){
							String info = (String)v.get(i);
							System.out.println(info+"******");
							String key = "";
							if(info.startsWith(AWJ_ChatUtil.MSG_LOGIN)){
								key = info.substring(7);//获得用户名
								map.put(key, socket);//客户端添加至列表
								ta_info.append(key + " login...\n");

								Set<String> set = map.keySet();
								Iterator<String> it = set.iterator();
								while(it.hasNext()){//迭代map，向所有客户端发送登录信息
									String reciveKey = it.next();//表示接收信息的key，即用户名
									Socket s = map.get(reciveKey);
									PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
									pw.println(info);
									pw.flush();
								}
							}else if(info.startsWith(AWJ_ChatUtil.MSG_EXIT)){//用户退出
								key = info.substring(6);//获得退出用户的key
								ois.close();
								socket.close();
								map.remove(key);
								Set<String> set = map.keySet();
								Iterator<String> it = set.iterator();
								while(it.hasNext()){
									String reciveKey = it.next();
									Socket s = map.get(reciveKey);
									PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
									pw.println(info);
									pw.flush();
								}
								ta_info.append(key + " exit...\n");
								return;//退出线程
							}else if(info.startsWith(AWJ_ChatUtil.MSG_USERLIST)){//传递当前在线用户列表
								String userList = "";
								Set<String> set = map.keySet();
								Iterator<String> it = set.iterator();
								while(it.hasNext()){
									key = it.next();
									userList += key + ",";
								}
								key = info.substring(10);
								Iterator<String> it2 = set.iterator();
								while(it2.hasNext()){
									String reciveKey = it2.next();
									if(key.equals(reciveKey)){
										Socket s = map.get(reciveKey);
										PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
										pw.println(AWJ_ChatUtil.MSG_USERLIST + userList);
										pw.flush();
									}
								}
							}else{//正常的消息转发
								String[] infos = AWJ_ChatUtil.formatInfos(info);
								Set<String> set = map.keySet();
								Iterator<String> it = set.iterator();
								while(it.hasNext()){
									String reciveKey = it.next();
									if(reciveKey.equals(infos[1])){//寻找到接受者
										Socket s = map.get(reciveKey);
										PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
										pw.println(info);//原文转发，客户端进行处理
										pw.flush();
									}
								}
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				ta_info.append(socket + "退出异常。\n");
			}
		}

	}

}