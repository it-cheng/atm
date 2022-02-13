package com.itcast.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 服务端启动类
public class Server {

	public static void main(String[] args) {
		// 创建服务端socket
		ServerSocket ss;
		try {
			ss = new ServerSocket(10001);
			while (true) {
				Socket s = ss.accept(); // 获取客户端对象
				// 将客户端对象封装成线程
				new Thread(new ServerSendAndAcceptThread(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
