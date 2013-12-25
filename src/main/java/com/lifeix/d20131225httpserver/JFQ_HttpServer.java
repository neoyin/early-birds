package com.lifeix.d20131225httpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JFQ_HttpServer {

	private static void listen(int port) throws IOException {
		ServerSocket ss = new ServerSocket(port);
		String rootPath = JFQ_HttpServer.class.getResource("").getFile()+"/response.html";
		File file = new File(rootPath);
		if(!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head><title>test</title></head><body>this is body</body></html>");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		while(true) {
			Socket socket = ss.accept();
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = br.readLine();
			line = line.split(" ")[0];
			BufferedReader brr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String out = brr.readLine();
			OutputStream os = socket.getOutputStream();
			while(out != null) {
				os.write(out.getBytes());
				out = brr.readLine();
			}
			os.flush();
			os.close();
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		listen(8888);
	}

}
