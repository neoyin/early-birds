package com.lifeix.d20131009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class JSH_NIOClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = 19101;

	/** 通道管理器  */
	private Selector selector;
	/**
	 * 初始化客户端操作
	 * @param host
	 * @param port
	 * @throws IOException
	 */
	public void initClient(String host, int port) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		this.selector = Selector.open();
		channel.connect(new InetSocketAddress(host,port));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	/**
	 * 监听selector上是否有需要处理的事件，有则处理
	 * @throws IOException
	 */
	public void listen() throws IOException {
		boolean loopValue = true;
		while(loopValue) {
			selector.select();
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			while(ite.hasNext()) {
				SelectionKey key = ite.next();
				ite.remove();
				if(key.isConnectable()) {
					acceptMsg(key);
				}else if(key.isReadable()) {
					loopValue = read(key);
				}else if(key.isWritable()) {
					loopValue = sendMsg(key);
				}
			}
		}
	}
	/**
	 * 获得连接信息
	 * @param key
	 * @throws IOException
	 */
	public void acceptMsg(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel)key.channel();
		if(channel.isConnectionPending()) {
			channel.finishConnect();
		}
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_READ);
	}
	/**
	 * 发送信息
	 * @param key
	 * @throws IOException
	 */
	public boolean sendMsg(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel)key.channel();
		if(channel.isConnectionPending()) {
			channel.finishConnect();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String str = br.readLine();
		channel.configureBlocking(false);
		if("bye".equalsIgnoreCase(str)) {
			channel.write(ByteBuffer.wrap(str.getBytes("UTF-8")));
			channel.close();
			return false;
		}
		channel.write(ByteBuffer.wrap(str.getBytes("UTF-8")));
		channel.register(selector, SelectionKey.OP_READ);
		return true;
	}
	/**
	 * 读取服务器端发送来的信息
	 * @param key
	 * @throws IOException
	 */
	public boolean read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		buffer.flip();
        String msg = Charset.forName("UTF-8").decode(buffer).toString();
        System.out.println(msg);
        if("username repeat".equals(msg)) {
        	channel.close();
        	return false;
        }
        channel.register(selector, SelectionKey.OP_WRITE);
        return true;
	}
	public static void main(String[] args) throws IOException {
		JSH_NIOClient client = new JSH_NIOClient();  
        client.initClient(HOST,PORT);  
        client.listen(); 
	}
}