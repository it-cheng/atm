package com.itcast.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

// �ͻ���������������
public class ConnectToServer {
	
	private Socket s = null;
	private PrintWriter out = null;
	
	// ���ؽ��Ϊ����
	public List listAccept (String message) {
		
		List list = null;
		
		try {
			// �����ͻ���socket
			s = new Socket("127.0.0.1", 10001);
			// ��ȡsocket����������ڽ��ͻ��˵���Ϣ���͸������
			out = new PrintWriter(s.getOutputStream(), true);
			// ��ȡsocket�����������ڶ�ȡ����˷�����������
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			out.println(message); // ����Ϣ���͸������	
			list = (List) ois.readObject();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "���ӷ�����ʧ�ܣ�");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	// ���ؽ��Ϊ�ַ���
	public String stringAccept(String message) {
		
		BufferedReader bufIn = null;
		String info = null;
		
		try {
			// �����ͻ���socket
			s = new Socket("127.0.0.1", 10001);
			// ��ȡsocket����������ڽ��ͻ��˵���Ϣ���͸������
			out = new PrintWriter(s.getOutputStream(), true);
			// ��ȡsocket�����������ڶ�ȡ����˷�����������
			bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));			
			out.println(message); // ����Ϣ���͸������
			info = bufIn.readLine(); // ��ȡ����˷��ص���Ϣ
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "���ӷ�����ʧ�ܣ�");
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return info;
	}
	
}
