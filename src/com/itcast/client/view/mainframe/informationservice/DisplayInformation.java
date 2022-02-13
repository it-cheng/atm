package com.itcast.client.view.mainframe.informationservice;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.itcast.client.view.mainframe.MainFrame;

public class DisplayInformation extends JInternalFrame {
	
	private JLabel customerNameLabel;
	private JLabel addressLabel;
	private JLabel telephoneLabel;
	private JLabel pidLabel;
	
	// ÏÔÊ¾ÐÅÏ¢½çÃæ
	public DisplayInformation() {
		
		setFrameIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u67E5\u8BE2\u4FE1\u606F\u754C\u9762.png")));
		setTitle("\u67E5\u8BE2\u4FE1\u606F");
		setBounds(175, 65, 450, 300);
		setClosable(true); // ¿É¹Ø±ÕµÄ
		setIconifiable(true); // ¿É·Å´óËõÐ¡
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u59D3\u540D.png")));
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label.setBounds(50, 30, 100, 20);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_1.setIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u8EAB\u4EFD\u8BC1\u53F7.png")));
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label_1.setBounds(50, 150, 100, 20);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u624B\u673A\u53F7\uFF1A");
		label_2.setIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u624B\u673A\u53F7.png")));
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label_2.setBounds(50, 110, 100, 20);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u4F4F\u5740\uFF1A");
		label_3.setIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u4F4F\u5740.png")));
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label_3.setBounds(50, 70, 100, 20);
		getContentPane().add(label_3);
		
		customerNameLabel = new JLabel("");
		customerNameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		customerNameLabel.setBounds(150, 30, 150, 20);
		getContentPane().add(customerNameLabel);
		
		addressLabel = new JLabel("");
		addressLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		addressLabel.setBounds(150, 70, 250, 20);
		getContentPane().add(addressLabel);
		
		telephoneLabel = new JLabel("");
		telephoneLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		telephoneLabel.setBounds(150, 110, 150, 20);
		getContentPane().add(telephoneLabel);
		
		pidLabel = new JLabel("");
		pidLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		pidLabel.setBounds(150, 150, 200, 20);
		getContentPane().add(pidLabel);
		
		JButton button = new JButton("\u663E\u793A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayInformation(e);
			}
		});
		button.setIcon(new ImageIcon(DisplayInformation.class.getResource("/images/\u67E5\u8BE225.png")));
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		button.setBounds(160, 200, 110, 30);
		getContentPane().add(button);

	}

	private void displayInformation(ActionEvent e) {
		customerNameLabel.setText(MainFrame.userInfo.getCustomerName());
		addressLabel.setText(MainFrame.userInfo.getAddress());
		telephoneLabel.setText(MainFrame.userInfo.getTelephone());
		pidLabel.setText(MainFrame.userInfo.getPid());
	}
}
