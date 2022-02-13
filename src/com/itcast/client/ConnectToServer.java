package com.itcast.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

// 客户端与服务端连接类
public class ConnectToServer {
	
	private Socket s = null;
	private PrintWriter out = null;
	
	// 返回结果为集合
	public List listAccept (String message) {
		
		List list = null;
		
		try {
			// 创建客户端socket
			s = new Socket("127.0.0.1", 10001);
			// 获取socket输出流，用于将客户端的信息发送给服务端
			out = new PrintWriter(s.getOutputStream(), true);
			// 获取socket输入流，用于读取服务端发回来的数据
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			out.println(message); // 将信息发送给服务端	
			list = (List) ois.readObject();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "连接服务器失败！");
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
	
	// 返回结果为字符串
	public String stringAccept(String message) {
		
		BufferedReader bufIn = null;
		String info = null;
		
		try {
			// 创建客户端socket
			s = new Socket("127.0.0.1", 10001);
			// 获取socket输出流，用于将客户端的信息发送给服务端
			out = new PrintWriter(s.getOutputStream(), true);
			// 获取socket输入流，用于读取服务端发回来的数据
			bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));			
			out.println(message); // 将信息发送给服务端
			info = bufIn.readLine(); // 读取服务端返回的信息
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "连接服务器失败！");
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
