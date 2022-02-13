package com.itcast.client.view.mainframe.systemsetup;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.itcast.client.ConnectToServer;
import com.itcast.client.view.mainframe.MainFrame;
import com.itcast.util.StringUtil;

public class ChangePasswordFrame extends JInternalFrame {
	
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	
	// –ﬁ∏ƒ√‹¬ÎΩÁ√Ê
	public ChangePasswordFrame() {
		
		setFrameIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u4FEE\u6539\u5BC6\u780116.png")));
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(175, 65, 450, 300);
		getContentPane().setLayout(null);
		setClosable(true); // ø…πÿ±’µƒ
		setIconifiable(true); // ø…∑≈¥ÛÀı–°
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u8D26\u6237\uFF1A");
		label_1.setIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u5361\u53F7.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		label_1.setBounds(77, 20, 100, 25);
		getContentPane().add(label_1);

		JLabel cardIdLabel = new JLabel("");
		cardIdLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		cardIdLabel.setBounds(177, 20, 175, 25);
		cardIdLabel.setText(MainFrame.cardInfo.getCardId());
		getContentPane().add(cardIdLabel);
		
		JLabel label = new JLabel("\u539F  \u5BC6  \u7801\uFF1A");
		label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		label.setIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u5BC6\u7801.png")));
		label.setBounds(77, 70, 100, 25);
		getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u65B0  \u5BC6  \u7801\uFF1A");
		label_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		label_2.setIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		label_2.setBounds(77, 120, 100, 25);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u786E\u8BA4\u5BC6\u7801.png")));
		label_3.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		label_3.setBounds(77, 170, 100, 25);
		getContentPane().add(label_3);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword(e);
			}
		});
		button.setIcon(new ImageIcon(ChangePasswordFrame.class.getResource("/images/\u4FEE\u6539\u5BC6\u780125.png")));
		button.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 20));
		button.setBounds(160, 220, 110, 30);
		getContentPane().add(button);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(177, 68, 175, 30);
		getContentPane().add(oldPasswordField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(177, 118, 175, 30);
		getContentPane().add(newPasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(177, 168, 175, 30);
		getContentPane().add(confirmPasswordField);
		
		
	}

	private void changePassword(ActionEvent e) {
		String oldPassword = oldPasswordField.getText();
		String newPassword = newPasswordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		if(StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(this, "«ÎÃÓ–¥æ…√‹¬Î£°");
			return;
		}
		if(StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(this, "«ÎÃÓ–¥–¬√‹¬Î£°");
			return;
		}
		if(StringUtil.isEmpty(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "«Î»∑»œ–¬√‹¬Î£°");
			return;
		}
		if(!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "¡Ω¥Œ√‹¬Î ‰»Î≤ª“ª÷¬£°");
			return;
		}
		System.out.println(oldPassword + "   " + MainFrame.cardInfo.getPassword());
		if(!oldPassword.equals(MainFrame.cardInfo.getPassword())) {
			JOptionPane.showMessageDialog(this, "æ…√‹¬Î ‰»Î¥ÌŒÛ£°");
			return;
		}
		String message = "change " + MainFrame.cardInfo.getCardId() + " " + newPassword;
		String info = new ConnectToServer().stringAccept(message);
		if (info.contains("changeFailed")) {
			JOptionPane.showMessageDialog(this, "–ﬁ∏ƒ√‹¬Î ß∞‹£°");
			return;
		}
		if (info.contains("changeSuccessfully")) {
			JOptionPane.showMessageDialog(this, "–ﬁ∏ƒ√‹¬Î≥…π¶");
			MainFrame.cardInfo.setPassword(newPassword);
		}
		
	}
}
