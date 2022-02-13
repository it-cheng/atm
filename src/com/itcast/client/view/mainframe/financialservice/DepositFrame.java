package com.itcast.client.view.mainframe.financialservice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.itcast.client.ConnectToServer;
import com.itcast.client.view.mainframe.MainFrame;
import com.itcast.client.view.mainframe.QueryBalance;
import com.itcast.util.StringUtil;

public class DepositFrame extends JInternalFrame {
	
	private JLabel balanceLabel;
	private JTextField moneyField;

	// 存款界面
	public DepositFrame() {
		
		QueryBalance.query(); // 调用查询方法
		
		// 设置界面参数
		setTitle("\u5B58\u6B3E\u754C\u9762");
		setFrameIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5B58\u6B3E\u754C\u9762.png")));
		setBounds(175, 65, 450, 300);
		getContentPane().setLayout(null);
		setClosable(true); // 可关闭的
		setIconifiable(true); // 可放大缩小
		
		// 创建标签
		JLabel label = new JLabel("\u5B58\u6B3E\u91D1\u989D\uFF1A");
		label.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u91D1\u989D.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(77, 140, 100, 25);
		getContentPane().add(label);

		JLabel label_2 = new JLabel("\u8D26\u6237\u4F59\u989D\uFF1A");
		label_2.setIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u91D1\u989D.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(77, 85, 100, 25);
		getContentPane().add(label_2);

		balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		balanceLabel.setBounds(177, 85, 200, 25);
		balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		getContentPane().add(balanceLabel);

		JLabel label_1 = new JLabel("\u5F53\u524D\u8D26\u6237\uFF1A");
		label_1.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5361\u53F7.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setBounds(77, 30, 100, 25);
		getContentPane().add(label_1);

		JLabel cardIdLabel = new JLabel("");
		cardIdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		cardIdLabel.setBounds(177, 30, 175, 25);
		cardIdLabel.setText(MainFrame.cardInfo.getCardId());
		getContentPane().add(cardIdLabel);
		
		// 创建文本框
		moneyField = new JTextField();
		moneyField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		moneyField.setBounds(177, 135, 175, 35);
		getContentPane().add(moneyField);

		// 创建按钮并为其添加事件
		JButton button = new JButton("\u5B58\u6B3E");
		button.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5B58\u6B3E25.png")));
		button.setFont(new Font("微软雅黑", Font.BOLD, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deposit(ae);
			}
		});
		button.setBounds(160, 200, 110, 30);
		getContentPane().add(button);

	}

	// 存款方法
	private void deposit(ActionEvent ae) {
		String moneyText = moneyField.getText(); // 获取存款金额
		// 判断金额是否满足条件
		if (StringUtil.isEmpty(moneyText)) {
			JOptionPane.showMessageDialog(this, "请输入存款金额！");
			return;
		}
		int money = Integer.valueOf(moneyField.getText()); // 将金额转化为整型
		if (money <= 0) {
			JOptionPane.showMessageDialog(this, "输入的金额需大于0！");
			return;
		}
		if ((money % 50 != 0) && (money % 100 != 0)) {
			JOptionPane.showMessageDialog(this, "输入的金额需为50或100的整数倍！");
			return;
		}
		// 创建信息
		String message = "deposit " + MainFrame.cardInfo.getCardId() + " " + moneyText;
		// 调用客户端与服务端连接类并记录返回结果
		String info = new ConnectToServer().stringAccept(message);
		// 根据返回的信息执行不同代码
		if (info.contains("depositFailed")) {
			JOptionPane.showMessageDialog(this, "存款失败！");
			return;
		}
		if (info.contains("depositSuccessfully")) {
			JOptionPane.showMessageDialog(this, "存款成功");
			MainFrame.cardInfo.setBalance(MainFrame.cardInfo.getBalance() + money);
			balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		}
	}
}
