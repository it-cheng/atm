package com.itcast.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// �����������
public class Server {

	public static void main(String[] args) {
		// ���������socket
		ServerSocket ss;
		try {
			ss = new ServerSocket(10001);
			while (true) {
				Socket s = ss.accept(); // ��ȡ�ͻ��˶���
				// ���ͻ��˶����װ���߳�
				new Thread(new ServerSendAndAcceptThread(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
