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

public class WithdrawalsFrame extends JInternalFrame {
	
	private JLabel balanceLabel;
	private JTextField moneyField;

	// 取款界面
	public WithdrawalsFrame() {
		
		QueryBalance.query();
		
		setFrameIcon(new ImageIcon(WithdrawalsFrame.class.getResource("/images/\u53D6\u6B3E\u754C\u9762.png")));
		setTitle("\u53D6\u6B3E\u754C\u9762");
		setBounds(175, 65, 450, 300);
		getContentPane().setLayout(null);
		setClosable(true); // 可关闭的
		setIconifiable(true); // 可放大缩小
		
		JLabel label = new JLabel("\u53D6\u6B3E\u91D1\u989D\uFF1A");
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
		
		moneyField = new JTextField();
		moneyField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		moneyField.setBounds(177, 135, 175, 35);
		getContentPane().add(moneyField);
		
		JButton button = new JButton("\u53D6\u6B3E");
		button.setIcon(new ImageIcon(WithdrawalsFrame.class.getResource("/images/\u53D6\u6B3E25.png")));
		button.setFont(new Font("微软雅黑", Font.BOLD, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				withdrawals(ae);
			}
		});
		button.setBounds(160, 200, 110, 30);
		getContentPane().add(button);
		
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
	}
	
	private void withdrawals(ActionEvent ae) {
		String moneyText = moneyField.getText();
		if(StringUtil.isEmpty(moneyText)) {
			JOptionPane.showMessageDialog(this, "请输入取款金额！");
			return;
		}
		int money = Integer.valueOf(moneyField.getText());
		if (money <= 0) {
			JOptionPane.showMessageDialog(this, "输入的金额需大于0！");
			return;
		}
		if ((money % 50 != 0) && (money % 100 != 0)) {
			JOptionPane.showMessageDialog(this, "输入的金额需为50或100的整数倍！");
			return;
		}
		if(money > MainFrame.cardInfo.getBalance()) {
			JOptionPane.showMessageDialog(this, "您当前账户余额不足！");
			return;
		}
		String message = "withdrawals " + MainFrame.cardInfo.getCardId() + " " + moneyText;
		String info = new ConnectToServer().stringAccept(message);
		if(info.contains("withdrawalsFailed")) {
			JOptionPane.showMessageDialog(this, "取款失败！");
			return;
		}
		if(info.contains("withdrawalsSuccessfully")) {
			JOptionPane.showMessageDialog(this, "取款成功");
			MainFrame.cardInfo.setBalance(MainFrame.cardInfo.getBalance() - money);
			balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		}
	}
}
