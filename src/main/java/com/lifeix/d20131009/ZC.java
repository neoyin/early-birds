package com.lifeix.d20131009;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ZC {
	// 通道管理器
	private Selector selector;

	public static SocketChannel channel;

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 *            连接的服务器的ip
	 * @param port
	 *            连接的服务器的端口号
	 * @throws IOException
	 */
	public void initClient(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞
		channel.configureBlocking(false);
		// 获得一个通道管理器
		this.selector = Selector.open();

		// 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
		// 用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip, port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			selector.select();
			// 获得selector中选中的项的迭代器
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已选的key,以防重复处理
				ite.remove();
				// 连接事件发生
				if (key.isConnectable()) {
					channel = (SocketChannel) key.channel();
					// 如果正在连接，则完成连接
					if (channel.isConnectionPending()) {
						channel.finishConnect();

					}
					// 设置成非阻塞
					channel.configureBlocking(false);

					// 在这里可以给服务端发送信息哦
					channel.write(ByteBuffer.wrap(new String(
							"login|192.168.1.1|ZC").getBytes()));
					// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
					Thread t = new Thread(new sendKuang());
					t.start();
					// 获得了可读的事件
				} else if (key.isReadable()) {
					read(key);
				}

			}

		}
	}

	/**
	 * 处理读取服务端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(100);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data, "UTF-8").trim();
		System.out.println(msg);
	}

	/**
	 * 启动客户端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ZC client = new ZC();
		client.initClient("192.168.50.123", 8100);
		client.listen();
	}

}

class sendKuang implements Runnable {
	public static JTextArea area = new JTextArea();

	@Override
	public void run() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		KeyLogger logger = new KeyLogger();
		logger.setArea(area);
		kit.addAWTEventListener(logger, AWTEvent.KEY_EVENT_MASK);
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Frame");
				frame.add(new JScrollPane(area));
				frame.setPreferredSize(new java.awt.Dimension(500, 300));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setTitle("输入框");
			}
		});
	}

	private static class KeyLogger implements AWTEventListener {
		private JTextArea area;

		@Override
		public void eventDispatched(AWTEvent e) {
			if (e instanceof KeyEvent) {
				KeyEvent event = (KeyEvent) e;
				if (event.getID() == KeyEvent.KEY_PRESSED) {
					if (event.getKeyCode() == 10) {
						String str = area.getText();
						System.out.println(str);
						try {
							area.setText("");
							ZC.channel.write(ByteBuffer.wrap(str
									.getBytes("ISO8859-1")));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}


		public void setArea(JTextArea area) {
			this.area = area;
		}
	}
}
