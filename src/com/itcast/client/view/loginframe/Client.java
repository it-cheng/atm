package com.itcast.client.view.loginframe;

import java.awt.EventQueue;

import javax.swing.UIManager;

// 启动客户端类
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
		// 定义窗口的显示风格
		try {
			// getSystemLookAndFeelClassName方法表示取得与系统相同的显示风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Client();
	}
}
