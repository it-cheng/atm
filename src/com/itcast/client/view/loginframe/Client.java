package com.itcast.client.view.loginframe;

import java.awt.EventQueue;

import javax.swing.UIManager;

// �����ͻ�����
public class Client {
	
	public Client() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		// ���崰�ڵ���ʾ���
		try {
			// getSystemLookAndFeelClassName������ʾȡ����ϵͳ��ͬ����ʾ���
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Client();
	}
}
