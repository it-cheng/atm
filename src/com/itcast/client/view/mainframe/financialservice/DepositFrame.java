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

	// ������
	public DepositFrame() {
		
		QueryBalance.query(); // ���ò�ѯ����
		
		// ���ý������
		setTitle("\u5B58\u6B3E\u754C\u9762");
		setFrameIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5B58\u6B3E\u754C\u9762.png")));
		setBounds(175, 65, 450, 300);
		getContentPane().setLayout(null);
		setClosable(true); // �ɹرյ�
		setIconifiable(true); // �ɷŴ���С
		
		// ������ǩ
		JLabel label = new JLabel("\u5B58\u6B3E\u91D1\u989D\uFF1A");
		label.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u91D1\u989D.png")));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(77, 140, 100, 25);
		getContentPane().add(label);

		JLabel label_2 = new JLabel("\u8D26\u6237\u4F59\u989D\uFF1A");
		label_2.setIcon(new ImageIcon(QueryFrame.class.getResource("/images/\u91D1\u989D.png")));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(77, 85, 100, 25);
		getContentPane().add(label_2);

		balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		balanceLabel.setBounds(177, 85, 200, 25);
		balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		getContentPane().add(balanceLabel);

		JLabel label_1 = new JLabel("\u5F53\u524D\u8D26\u6237\uFF1A");
		label_1.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5361\u53F7.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label_1.setBounds(77, 30, 100, 25);
		getContentPane().add(label_1);

		JLabel cardIdLabel = new JLabel("");
		cardIdLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		cardIdLabel.setBounds(177, 30, 175, 25);
		cardIdLabel.setText(MainFrame.cardInfo.getCardId());
		getContentPane().add(cardIdLabel);
		
		// �����ı���
		moneyField = new JTextField();
		moneyField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		moneyField.setBounds(177, 135, 175, 35);
		getContentPane().add(moneyField);

		// ������ť��Ϊ������¼�
		JButton button = new JButton("\u5B58\u6B3E");
		button.setIcon(new ImageIcon(DepositFrame.class.getResource("/images/\u5B58\u6B3E25.png")));
		button.setFont(new Font("΢���ź�", Font.BOLD, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deposit(ae);
			}
		});
		button.setBounds(160, 200, 110, 30);
		getContentPane().add(button);

	}

	// ����
	private void deposit(ActionEvent ae) {
		String moneyText = moneyField.getText(); // ��ȡ�����
		// �жϽ���Ƿ���������
		if (StringUtil.isEmpty(moneyText)) {
			JOptionPane.showMessageDialog(this, "���������");
			return;
		}
		int money = Integer.valueOf(moneyField.getText()); // �����ת��Ϊ����
		if (money <= 0) {
			JOptionPane.showMessageDialog(this, "����Ľ�������0��");
			return;
		}
		if ((money % 50 != 0) && (money % 100 != 0)) {
			JOptionPane.showMessageDialog(this, "����Ľ����Ϊ50��100����������");
			return;
		}
		// ������Ϣ
		String message = "deposit " + MainFrame.cardInfo.getCardId() + " " + moneyText;
		// ���ÿͻ��������������ಢ��¼���ؽ��
		String info = new ConnectToServer().stringAccept(message);
		// ���ݷ��ص���Ϣִ�в�ͬ����
		if (info.contains("depositFailed")) {
			JOptionPane.showMessageDialog(this, "���ʧ�ܣ�");
			return;
		}
		if (info.contains("depositSuccessfully")) {
			JOptionPane.showMessageDialog(this, "���ɹ�");
			MainFrame.cardInfo.setBalance(MainFrame.cardInfo.getBalance() + money);
			balanceLabel.setText(String.valueOf(MainFrame.cardInfo.getBalance()));
		}
	}
}
