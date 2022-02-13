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

public class TransferAccountsFrame extends JInternalFrame {
	
	private JLabel balanceLabel;
	private JTextField transferToCardIdField;
	private JTextField moneyField;
	
	// 转账界面
	public TransferAccountsFrame() {
		
		QueryBalance.query();
		
		setFrameIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u8F6C\u8D26\u754C\u9762.png")));
		setTitle("\u8F6C\u8D26\u754C\u9762");
		setBounds(175, 65, 450, 300);
		getContentPane().setLayout(null);
		setClosable(true); // 可关闭的
		setIconifiable(true); // 可放大缩小
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u8D26\u6237\uFF1A");
		label_1.setIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u5361\u53F7.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setBounds(77, 10, 100, 25);
		getContentPane().add(label_1);

		JLabel cardIdLabel = new JLabel("");
		cardIdLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		cardIdLabel.setBounds(177, 10, 175, 25);
		cardIdLabel.setText(MainFrame.cardInfo.getCardId());
		getContentPane().add(cardIdLabel);
		
		JLabel label_2 = new JLabel("\u8D26\u6237\u4F59\u989D\uFF1A");
		label_2.setIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u91D1\u989D.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(77, 60, 100, 25);
		getContentPane().add(label_2);
		
		balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		balanceLabel.setBounds(177, 60, 200, 25);
		balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		getContentPane().add(balanceLabel);
		
		JLabel label = new JLabel("\u8F6C\u5165\u8D26\u6237\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label.setIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u5361\u53F7.png")));
		label.setBounds(77, 110, 100, 25);
		getContentPane().add(label);
		
		JLabel label_3 = new JLabel("\u8F6C\u5165\u91D1\u989D\uFF1A");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_3.setIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u91D1\u989D.png")));
		label_3.setBounds(77, 160, 100, 25);
		getContentPane().add(label_3);
		
		JButton button = new JButton("\u8F6C\u8D26");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferAccounts(e);
			}
		});
		button.setIcon(new ImageIcon(TransferAccountsFrame.class.getResource("/images/\u8F6C\u8D2625.png")));
		button.setFont(new Font("微软雅黑", Font.BOLD, 20));
		button.setBounds(160, 210, 110, 30);
		getContentPane().add(button);
		
		transferToCardIdField = new JTextField();
		transferToCardIdField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		transferToCardIdField.setBounds(177, 108, 175, 30);
		getContentPane().add(transferToCardIdField);
		transferToCardIdField.setColumns(10);
		
		moneyField = new JTextField();
		moneyField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		moneyField.setColumns(10);
		moneyField.setBounds(177, 158, 175, 30);
		getContentPane().add(moneyField);

	}

	private void transferAccounts(ActionEvent e) {
		String transferCardId = transferToCardIdField.getText();
		String moneyText = moneyField.getText();
		if (StringUtil.isEmpty(transferCardId)) {
			JOptionPane.showMessageDialog(this, "请输入转入账户！");
			return;
		}
		if (StringUtil.isEmpty(moneyText)) {
			JOptionPane.showMessageDialog(this, "请输入转入金额！");
			return;
		}
		if(transferCardId.equals(MainFrame.cardInfo.getCardId())) {
			JOptionPane.showMessageDialog(this, "您不能给自己转账！");
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
		
		String message = "transfer " + MainFrame.cardInfo.getCardId() + " " + moneyText + " " + transferCardId;
		String info = new ConnectToServer().stringAccept(message);
		if(info.contains("notfound")) {
			JOptionPane.showMessageDialog(this, "未找到转入账户！");
			return;
		}
		if (info.contains("transferFailed")) {
			JOptionPane.showMessageDialog(this, "转账失败！");
			return;
		}
		if (info.contains("transferSuccessfully")) {
			JOptionPane.showMessageDialog(this, "转账成功");
			MainFrame.cardInfo.setBalance(MainFrame.cardInfo.getBalance() - money);
			balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		}
	}

}
