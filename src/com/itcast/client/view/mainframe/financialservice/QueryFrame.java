package com.itcast.client.view.mainframe.financialservice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.itcast.client.view.mainframe.MainFrame;
import com.itcast.client.view.mainframe.QueryBalance;

public class QueryFrame extends JInternalFrame {

	private JLabel balanceLabel;
	
	// 查询界面
	public QueryFrame() {
		
		QueryBalance.query();
		
		// 设置界面参数
		setTitle("\u67E5\u8BE2\u754C\u9762");
		setFrameIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u67E5\u8BE2\u754C\u9762.png")));
		setBounds(175, 65, 450, 300);
		setClosable(true); // 可关闭的
		setIconifiable(true); // 可放大缩小
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5F53\u524D\u8D26\u6237\uFF1A");
		label.setIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u5361\u53F7.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setBounds(77, 50, 100, 25);
		getContentPane().add(label);
		
		JLabel cardInfoLabel = new JLabel("");
		cardInfoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		cardInfoLabel.setBounds(177, 50, 175, 25);
		cardInfoLabel.setText(MainFrame.cardInfo.getCardId());
		getContentPane().add(cardInfoLabel);
		
		JLabel label_2 = new JLabel("\u8D26\u6237\u4F59\u989D\uFF1A");
		label_2.setIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u91D1\u989D.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(77, 120, 100, 25);
		getContentPane().add(label_2);
		
		balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		balanceLabel.setBounds(177, 120, 200, 25);
		getContentPane().add(balanceLabel);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query(e);
			}
		});
		button.setIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u67E5\u8BE225.png")));
		button.setFont(new Font("微软雅黑", Font.BOLD, 20));
		button.setBounds(160, 185, 110, 30);
		getContentPane().add(button);
	}

	private void query(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "查询成功");
		balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
	}
}
