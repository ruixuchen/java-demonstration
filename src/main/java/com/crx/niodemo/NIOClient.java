package com.crx.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
 

public class NIOClient {
 
	public static void main(String[] args) {
 

		NIOClient client = new NIOClient();

		client.init();
 
		client.listen();
 
	}
 
	private Selector selector = null;
 
	private void init() {
		try {
			selector = Selector.open();
			SocketChannel clientChannel = SocketChannel.open();
			clientChannel.configureBlocking(false);
			clientChannel.register(selector, SelectionKey.OP_CONNECT);
			clientChannel.connect(new InetSocketAddress("localhost", 9977));

		} catch (IOException e) {
		}
 
	}
 
	private void listen() {
 
		while (true) {
 
			if(!flag){
				
				try {
					int length = selector.select(2000);
 
					if (length > 0) {
 
						Set<SelectionKey> selectedKeys = selector.selectedKeys();
						Iterator<SelectionKey> iterator = selectedKeys.iterator();
						while (iterator.hasNext()) {
							SelectionKey key = iterator.next();
							iterator.remove();
							handleKey(key);
						}
					}
				} catch (IOException e) {
					System.exit(1);
				}
			}else{
				System.exit(1);
			}
		}
	}
 
	private void handleKey(SelectionKey key) {
 
		if (key.isValid()) {
 
			SocketChannel clientChannel = (SocketChannel) key.channel();
 
			if (key.isConnectable()) {
 
				boolean isSuccess = false;
				try {
					isSuccess = clientChannel.finishConnect();
 
					System.out.println("5 ------ client connect serve success !!!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
 
				if (isSuccess) {
 
					String[] requestStrArray = new String[] { "getNow", "getServerName", "abcde" };
					Random r = new Random();
					String request = requestStrArray[r.nextInt(requestStrArray.length)];
					System.out.println("6 ------ client server: " + request);
					try {
						clientChannel.register(selector, SelectionKey.OP_READ);
					} catch (ClosedChannelException e) {
						e.printStackTrace();
					}
 
					writeDataToServer(clientChannel, request);
 
				} else {
					System.exit(1);
				}
 
			}

			if (key.isReadable()) {
 

				ByteBuffer buffer = ByteBuffer.allocate(1024);
				try {
					int length = clientChannel.read(buffer);
 
					String response = new String(buffer.array(), 0, length);
 
					System.out.println("10 ------ client receive server's response : " + response);
 
				} catch (IOException e) {
					e.printStackTrace();
				}
 
				if (key != null) {
					key.cancel();
					if (key.channel() != null) {
						try {
							key.channel().close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					flag = true;
				}
			}
		}
	}
 
	private volatile boolean flag = false;
 
	private void writeDataToServer(SocketChannel clientChannel, String request) {
 

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(request.getBytes());
 
		buffer.flip();
 
		try {
			clientChannel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		System.out.println("client send request to server success !!!  === " + request);
	}
}

