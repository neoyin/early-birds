package com.lifeix.d20131225httpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class JSH_HttpServer {

	private static final int PORT = 8080;
	private static final String File_PATH = "/var/index.html"; 
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("server listening on port " + PORT);
			while(true) {
				Socket socket = serverSocket.accept();
				doGet(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * get request
	 * @param out
	 */
	public static void doGet(Socket socket) {
		File file = new File(File_PATH);
		if(file.exists()) {
			OutputStream out = null;
			BufferedReader br = null;
			try {
				StringBuffer sb = new StringBuffer();
				out = socket.getOutputStream();
				br = new BufferedReader(new FileReader(file));
				out.write("HTTP/1.1 200 OK\n\n".getBytes());
				String read;
				while ((read = br.readLine())!= null) {
					sb.append(read);
				}
				out.write(sb.toString().getBytes());
				out.flush();
				out.close();
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
	}
}
