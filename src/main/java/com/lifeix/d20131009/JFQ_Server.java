package com.lifeix.d20131009;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class JFQ_Server {

	private static BlockingQueue<Map<String, SelectionKey>> queues = new ArrayBlockingQueue<Map<String, SelectionKey>>(10);
	private static final int BUFFER_SIZE = 1024;
	private static ExecutorService readService = Executors.newFixedThreadPool(50);
	
	private void initServer() throws IOException{
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(9999));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		readService.execute(new ReceiveServerThread(selector, queues, BUFFER_SIZE));
		
	}
	
	/**@description:	
	 * @return:void
	 * @param args
	 * @throws IOException 
	 */

	public static void main(String[] args) throws IOException {
		new JFQ_Server().initServer();
	}

}


class ReceiveServerThread implements Runnable {
	private Selector selector;
	private int bufferSize;
	private BlockingQueue<Map<String, SelectionKey>> queues;
	private boolean flag = false;
	public ReceiveServerThread(Selector selector,BlockingQueue<Map<String, SelectionKey>> queues,int BUFFER_SIZE) {
		this.selector = selector;
		this.queues = queues;
		this.bufferSize = BUFFER_SIZE;
	}
	
	public void run() {
		try {
			while(!flag) {
				System.out.println("waiting connected.......");
				selector.select();
				Iterator<SelectionKey> keys =  selector.selectedKeys().iterator();
				while(keys.hasNext()) {
					SelectionKey key = keys.next();
					keys.remove();
					if(key.isAcceptable()) {
						ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
						SocketChannel sc = ssc.accept();
						sc.configureBlocking(false);
						SelectionKey clientKey = sc.register(selector, SelectionKey.OP_READ);  
						System.out.println(sc.socket().getInetAddress().getHostAddress() + " 已连接");
					}
					if(key.isReadable()) {
						read(key);
					} 
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void read(SelectionKey key) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
		SocketChannel sc = (SocketChannel) key.channel();
		try {
			sc.read(buffer);
			buffer.flip();
		} catch(IOException e) {
			sc.close();
		}
		String data = new String(buffer.array(),"UTF-8");
		if(null == data || "".equals(data) || data.indexOf("[]") < 0) {
			return;
		}
		data = data.substring(0,data.lastIndexOf("[]"));
		if("list".equals(data)) {
			sc.write(ByteBuffer.wrap((queues.toString()+"[]").getBytes("UTF-8")));
			return;
		} 
		if(data.startsWith("#name")) {
			Map<String, SelectionKey> map = null;
			if(queues.isEmpty()) {
				map = new HashMap<String, SelectionKey>();
			} else {
				map = queues.element();
			}
			map.put(data.substring(data.indexOf(":")+1), key);
			queues.clear();
			queues.add(map);
			return;
		}
		System.out.println("from client: " + data);
		String name = data.substring(0,data.indexOf("#"));
		String targetName = data.substring(data.indexOf("|") + 1, data.lastIndexOf("|"));
		String msg = data.substring(data.lastIndexOf("|") + 1);
		Iterator<Map<String, SelectionKey>> its = queues.iterator();
		if("exit".equals(targetName)) {
			if(its.hasNext()) {
				Map<String, SelectionKey> map = its.next();
				map.remove(name);
				System.out.println(name + "已经下线....");
				queues.clear();
				queues.add(map);
				key.cancel();
			}
		}  else {
			
			if(its.hasNext()) {
				
				Map<String, SelectionKey> map = its.next();
				SelectionKey targetSocket = map.get(targetName);
				if("setname".equals(targetName)) {
					map.remove(name);
					map.put(msg, key);
					queues.clear();
					queues.add(map);
					return;
				}
				if(null != targetSocket) {
					SocketChannel tsc = (SocketChannel) targetSocket.channel();
					tsc.write(ByteBuffer.wrap(( name + " :" + msg + "[]").getBytes("UTF-8")));
				} else {
					sc.write(ByteBuffer.wrap(("没有【" + targetName + "】[]").getBytes("UTF-8")));
				}
			}
		}
	}
	
}

