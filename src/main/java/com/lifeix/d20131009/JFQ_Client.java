package com.lifeix.d20131009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class JFQ_Client {
	
	private static final int BUFFER_SIZE = 1024;
	private static String name = "jiangfuqiang";
	private static String charset= "UTF-8";
	private void initClient() throws IOException {
		Socket socket = new Socket("192.168.50.127",9999);
		OutputStream os = socket.getOutputStream();
		os.write(("#name:" + name + "[]").getBytes(charset));
		os.flush();
		
		new Thread(new ReadClientThread(BUFFER_SIZE,socket, name)).start();
		new Thread(new WriteClientThread(BUFFER_SIZE,socket, name, os)).start();
	}
	
	
	
	/**@description:	
	 * @return:void
	 * @param args
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new JFQ_Client().initClient();
	}

}

class ReadClientThread implements Runnable {

	private int bufferSize;
	private Socket socket;
	private String name;
	private OutputStream os;
	private boolean flag = false;
	private static String charset= "UTF-8";
	public ReadClientThread(int bufferSize, Socket socket, String name) {
		this.bufferSize = bufferSize;
		this.socket = socket;
		this.name = name;
		try {
			os = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			System.out.println("exit命令下线");
			while(!flag) {
				InputStream is = socket.getInputStream();
				read(is);
			}
		} catch (IOException e) {
			
		} catch(Exception we) {
			System.out.println(name + "下线");
			
		}
	}
	private void read(InputStream is) throws IOException,SocketException {
		StringBuffer sb = new StringBuffer();
		byte[] buffer = new byte[bufferSize];
		if(is.read(buffer) > 0) {
			sb.append(new String(buffer,charset));
		}
		String data = sb.toString();
		if(null != data && !"".equals(data)) {
			data = data.substring(0, data.lastIndexOf("[]"));
		}
		System.out.println(data);
	}
}

class WriteClientThread implements Runnable {

	private Socket socket;
	private String name;
	private OutputStream os;
	private static String charset= "UTF-8";
	public WriteClientThread(int bufferSize, Socket socket, String name,OutputStream os) {
		this.socket = socket;
		this.name = name;
		this.os = os;
	}
	
	public void run() {
		boolean flag = false;
		while(!flag) {
			String command = null;
			try {
				command = getInputCommand();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("command:" + command);
			
			if(null != command && !"".equals(command.trim())) {
				if("list".equals(command)) {
					try {
						os.write("list[]".getBytes(charset));
						os.flush();
						continue;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(command.startsWith("setname")) {
					String temp = name;
					this.name = command.substring(command.indexOf("|") + 1);
					if(this.name == null || "".equals(this.name)) {
						System.out.println("给个名字吧....");
						continue;
					}
					try {
						os.write((temp + "#|setname|" + name + "[]").getBytes(charset));
						os.flush();
						continue;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if("exit".equals(command)) {
					try {
						flag = true;
						os.write((name + "#|exit|''[]").getBytes(charset));
						os.flush();
						os.close();
					} catch (IOException e) {
						System.out.println(name + "下线");
					}
				} else {
					if(command.indexOf("|") < 0) {
						System.out.println("invalidate 【" + command + "】");
						continue;
					}
					String cmd = command.substring(0, command.indexOf("|"));
					String content = command.substring(command.indexOf("|")+1);
					if("send".equals(cmd) && null != content && !"".equals(content) && !"".equals(name)) {
						try {
							os.write((name + "#|" + content + "[]").getBytes(charset));
							os.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else {
						System.out.println("命令错误");
					}
				}
			} else {
				System.out.println("invalidate" + command);
			}
		}
	}
	
	private String getInputCommand() throws IOException {
		System.out.print("发送消息：");
		InputStream is = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		if((line = br.readLine()) != null) {
			return line.trim();
		}
		System.out.println();
		return "";
	}
}