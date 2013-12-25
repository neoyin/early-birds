package com.lifeix.d20131225httpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LJApp extends ServerSocket {

	public LJApp(int port) throws IOException {
		super(port);
		try{
			while(true){
				Socket socket = accept();
				new ServerThread(socket);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
	}
	class ServerThread extends Thread{
		private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String path = "/home/abc/httptest";

        public ServerThread(Socket s) throws IOException {
            this.socket = s;
            // 构造该会话中的输入输出流
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GB2312"));
            out = new PrintWriter(socket.getOutputStream(), true);
            start();
        }

        public void run() {
            try {
            	String line = in.readLine();
            	if(line !=null){
            		// 通过输入流接收客户端信息
            		if ("bye".equals(line)) {  // 是否终止会话
            			return;
            		}
            		System.out.println("Received message:" + line);
            		String messages[] = line.split(" ");
                	String message = messages[1].trim();
                	try{
                		File file = new File(path+message);
                		FileReader fr = new FileReader(file);
                		BufferedReader br = new BufferedReader(fr);
                		String read;
                		StringBuilder builder = new StringBuilder();
                		while((read = br.readLine())!= null){
                			builder.append(read);
                		}
                		String back = builder.toString();
                		if(back == null){
                			back = "请求的资源不存在！";
                		}
                		// 通过输出流向客户端发送信息
                		out.println(back); 
                		out.flush();
                	}catch (Exception e) {
                		out.println("请求的资源不存在"); 
                		out.flush();
					}
            	}
            	out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	public static void main(String[] args) throws IOException {
        new LJApp(8888);
    }
}
