package com.lifeix.d20131009;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 聊天室客户端
 * @author ANWJ
 *
 */
public class AWJ_ChatClient extends JFrame {

	private static final long serialVersionUID = 5229667631648476409L;

	private JTextField tf_newUser;

	private JList userList;

	private JTextArea ta_info;

	private JTextField tf_send;

	private ObjectOutputStream out;

	private Socket socket = null;

	private boolean loginFlag = false;

	private int port =  8080;

	/**
	 * 构造函数
	 * 在构造中初始化界面
	 */
	public AWJ_ChatClient(){
		super();
		this.setTitle("立方聊天室客户端");
		this.setBounds(500, 200, 385, 488);
		URL url =  AWJ_ChatServer.class.getResource(AWJ_ChatUtil.LOGO_IMAGE);
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage();
		this.setIconImage(image);
		final JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.SOUTH);

		final JLabel label = new JLabel();
		label.setText("输入聊天内容");
		panel.add(label);

		tf_send = new JTextField();
		tf_send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				send();//调用发送方法
			}
		});
		tf_send.setPreferredSize(new Dimension(180, 25));
		panel.add(tf_send);//发送消息区域

		final JButton button = new JButton();
		button.setText("发   送");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				send();
			}
		});
		panel.add(button);//添加发送按钮

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(100);//分隔条的位置
		this.getContentPane().add(splitPane, BorderLayout.CENTER);

		final JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		ta_info = new JTextArea();
		ta_info.setFont(new Font("", Font.BOLD, 14));
		scrollPane.setViewportView(ta_info);//信息显示区域

		final JScrollPane scrollPane1 = new JScrollPane();
		userList = new JList();
		userList.setModel(new DefaultComboBoxModel(new String[] {""}));
		scrollPane1.setViewportView(userList);
		splitPane.setLeftComponent(scrollPane1);

		final JPanel panel_1 = new JPanel();
		final JLabel label_1 = new JLabel();
		label_1.setText("用户名称：");
		panel_1.add(label_1);

		tf_newUser = new JTextField();
		tf_newUser.setPreferredSize(new Dimension(140, 25));
		panel_1.add(tf_newUser);

		final JButton button_1 = new JButton();
		button_1.setText("登   录");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {//进行登录操作
				if(loginFlag){
					JOptionPane.showConfirmDialog(null, "当前用户已登录");
					return;
				}
				String userName = tf_newUser.getText().trim();
				Vector<String> v = new Vector<String>();
				v.add(AWJ_ChatUtil.fittingLoginMsg(userName));//发送登录信息
				try{
					out.writeObject(v);//发送
					out.flush();
				}catch(Exception e){
					e.printStackTrace();
				}
				tf_newUser.setEnabled(true);//用户名不可修改
				button_1.setVisible(false);//登录按钮隐藏
				loginFlag = true;
				getOnlineUser(userName);//获取在线用户列表
			}
		});
		panel_1.add(button_1);

		final JButton button_2 = new JButton();
		button_2.setText("退   出");
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(!loginFlag){//未登录
					JOptionPane.showMessageDialog(null, "已退出或未登录！");
					return;
				}
				String exitUser = tf_newUser.getText().trim();
				Vector<String> v = new Vector<String>();
				v.add(AWJ_ChatUtil.fittingExitMsg(exitUser));//退出信息
				try{
					out.writeObject(v);
					out.flush();
				}catch(Exception e){
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
		panel_1.add(button_2);
		this.getContentPane().add(panel_1, BorderLayout.NORTH);

		if(SystemTray.isSupported()){
			TrayIcon trayIcon = new TrayIcon(image);
			trayIcon.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2){
						showFrame();//显示界面
					}
				}
			});
			trayIcon.setToolTip("立方聊天");
			PopupMenu popupMenu = new PopupMenu();
			MenuItem exitMenu = new MenuItem("EXIT");
			exitMenu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					String exitUser = tf_newUser.getText().trim();
					Vector<String> v = new Vector<String>();
					v.add(AWJ_ChatUtil.fittingExitMsg(exitUser));//退出信息
					try{
						out.writeObject(v);
						out.flush();
						out.close();
						socket.close();
					}catch(Exception e){
						e.printStackTrace();
					}
					System.exit(0);
				}
			});
			popupMenu.add(exitMenu);
			trayIcon.setPopupMenu(popupMenu);
			SystemTray systemTray = SystemTray.getSystemTray();
			try {
				systemTray.add(trayIcon);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void showFrame(){
		this.setVisible(true);
		this.setState(Frame.NORMAL);
	}

	/**
	 * 创建客户端socket服务线程
	 */
	private void initClientSocket(){
		try{
			socket = new Socket(AWJ_ChatUtil.SERVER_IP, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			new ClientThread(socket).start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 客户端发送消息
	 */
	private void send(){
		if(!loginFlag){
			JOptionPane.showMessageDialog(null, "请先登录");
			return;
		}
		String sendUser = tf_newUser.getText().trim();
		String info = tf_send.getText();//获取用户输入信息
		if(info.equals("")){
			return;//输入为空，不发送
		}
		Vector<String> v = new Vector<String>();
		Object[] reciveUserNames = userList.getSelectedValues();
		if(reciveUserNames.length<=0){
			return;//没有选择发送对象，无法发送
		}
		Date now = new Date();
		for(int i=0; i<reciveUserNames.length; i++){
			//迭代receiver
			String receiver = (String)reciveUserNames[i];
			String msg = AWJ_ChatUtil.fittingSendMsg(sendUser, receiver, now, info);//组装发送信息
			v.add(msg);//信息添加至发送向量
		}
		try{
			out.writeObject(v);//发送信息
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		//显示信息
		String reciveInfo = tf_send.getText().trim();
		ta_info.append("  " + sendUser + "  "  + AWJ_ChatUtil.getDateString(now) +  "  " + AWJ_ChatUtil.getTimeString(now)
				+ "\n  " + reciveInfo + "\n");
		tf_send.setText(null);
		ta_info.setSelectionStart(ta_info.getText().length()-1);
		ta_info.setSelectionEnd(ta_info.getText().length());
	}

	/**
	 * 从服务器获得在线用户列表
	 * @return
	 */
	private String[] getOnlineUser(String user){
		String[] users = new String[]{};
		String msg = AWJ_ChatUtil.MSG_USERLIST + user;
		Vector<String> v = new Vector<String>();
		v.add(msg);
		try{
			out.writeObject(v);//发送信息
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}

		return users;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try{
					AWJ_ChatClient cf = new AWJ_ChatClient();
					cf.setVisible(true);
					cf.initClientSocket();//调用创建socket
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 客户端线程类
	 * @author ANWJ
	 *
	 */
	class ClientThread extends Thread{

		Socket socket;

		public ClientThread(Socket socket){
			this.socket = socket;
		}

		public void run(){
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DefaultComboBoxModel model = null;
				while(true){
					String info = br.readLine();
					System.out.println("info-->" + info);
					if(info.startsWith(AWJ_ChatUtil.MSG_EXIT)){//下线
						model = (DefaultComboBoxModel)userList.getModel();
						model.removeElement(info.substring(6));
						ta_info.append(info.substring(6) + "  下线了。\n");
					}else if(info.startsWith(AWJ_ChatUtil.MSG_LOGIN)){//上线
						model = (DefaultComboBoxModel)userList.getModel();
						model.addElement(info.substring(7));
						userList.setModel(model);
						ta_info.append(info.substring(7) + "  上线了。\n");
					}else if(info.startsWith(AWJ_ChatUtil.MSG_USERLIST)){
						String users = info.substring(10);
						userList.setModel(new DefaultComboBoxModel(users.split(",")));
					}else{//接收到用户消息
						String[] infos = AWJ_ChatUtil.formatInfos(info);//接收到的信息
						ta_info.append(" " + infos[0] + "  " + infos[2] + "\n  " + infos[3] + "\n");//显示
						ta_info.setSelectionStart(ta_info.getText().length()-1);
						ta_info.setSelectionEnd(ta_info.getText().length());
						tf_send.requestFocus();//获取焦点
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
