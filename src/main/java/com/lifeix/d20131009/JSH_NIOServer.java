package com.lifeix.d20131009;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSH_NIOServer {
	
	private static final int PORT = 19101;
	
	private static int seq = 101010;
	
	private Set<String> users;
	
	/** 通道管理器  */
	private Selector selector;  
	/**
	 * 初始化服务器，绑定端口号
	 * @param port
	 * @throws IOException 
	 */
	public void initServer(int port) throws IOException {
		users = new HashSet<String>();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		this.selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	/**
	 * 监听selector上是否有需要处理的事件，有则处理
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("server start succeed!");
		while(true) {
			selector.select();
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			while(ite.hasNext()) {
				SelectionKey key = ite.next();
				ite.remove();
				if(key.isAcceptable()) {
					sendAccept(key);
				}else if(key.isReadable()) {  
                    read(key);
				}
			}
		}
	}
	/**
	 * 返回连接信息
	 * @param key
	 * @throws IOException
	 */
	public void sendAccept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        String host = server.socket().getInetAddress().getHostAddress();
        seq++;
        String userName = getUserName(host);
		SocketChannel channel = server.accept();
		channel.configureBlocking(false);
		String msg = "";
		if(users.contains(userName)) {
			msg = "username repeat";
		}else {
			msg = "login|" + host + "|" + userName;
			users.add(userName);
		}
		channel.write(ByteBuffer.wrap(msg.getBytes("UTF-8")));
		channel.register(this.selector, SelectionKey.OP_READ);
	}
	/**
	 * 读取客户端发送来的信息
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		buffer.flip();
        String msg = Charset.forName("UTF-8").decode(buffer).toString();
		System.out.println(msg);
		if("bye".equalsIgnoreCase(msg)) {
			String host = channel.socket().getInetAddress().getHostAddress();
			users.remove(getUserName(host));
		}else if("list".equalsIgnoreCase(msg)) {
			StringBuilder str = new StringBuilder();
			for(String user : users) {
				str.append(user).append("\\n");
			}
			channel.write(ByteBuffer.wrap(str.toString().getBytes("UTF-8")));
		}
	}
	private String getUserName(String host) {
		return host;
	}
	public static void main(String[] args) throws IOException {
		JSH_NIOServer server = new JSH_NIOServer();
		server.initServer(PORT);
		server.listen();
	}
}
