package com.itcast.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.itcast.server.jdbc.dao.ICardInfoDao;
import com.itcast.server.jdbc.dao.impl.CardInfoDaoImpl;

// ���ͻ��˷�װΪ�߳�
public class ServerSendAndAcceptThread implements Runnable {

	private Socket s = null;
	private String ip = null;
	private String message = null;
	private BufferedReader bufIn = null;
	private ObjectOutputStream oos = null;
	private PrintWriter out = null;
	private String[] str = null;
	
	public ServerSendAndAcceptThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		ip = s.getInetAddress().getHostAddress(); // ��ȡ�ͻ���ip
		System.out.println(ip + ".....connected");
		try {
			bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			oos = new ObjectOutputStream(s.getOutputStream());
			message = bufIn.readLine(); // ��ȡ�ͻ��˷�������Ϣ
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ����Ϣ�Կո�Ϊ�����Ϊ���ɸ��ַ���
		str = message.split(" ");
		// �����ַ���������׸��ַ����ж���Ҫִ�еĴ���
		if(str[0].contains("login") || str[0].contains("query")) {
			List list = null;
			if(str[0].contains("login")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				list = cardInfoDao.login(str[1], str[2]);
			}
			if(str[0].contains("query")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				list = cardInfoDao.query(str[1]);
			}
			try {
				oos.writeObject(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			String info = null;
			if(str[0].contains("deposit")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				int money = Integer.valueOf(str[2]);
				info = cardInfoDao.deposit(str[1], money);
			}
			if(str[0].contains("withdrawals")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				int money = Integer.valueOf(str[2]);
				info = cardInfoDao.withdrawals(str[1], money);
			}
			if(str[0].contains("change")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				info = cardInfoDao.changePassword(str[1], str[2]);
			}
			if(str[0].contains("transfer")) {
				ICardInfoDao cardInfoDao = new CardInfoDaoImpl();
				int money = Integer.valueOf(str[2]);
				info = cardInfoDao.transferAccounts(str[1], money, str[3]);
			}
			out.println(info); // ����Ϣд��ͻ���
		}
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
