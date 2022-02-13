package com.itcast.client.view.loginframe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itcast.client.ConnectToServer;
import com.itcast.client.view.mainframe.MainFrame;
import com.itcast.server.jdbc.domain.CardInfo;
import com.itcast.server.jdbc.domain.UserInfo;
import com.itcast.util.StringUtil;

// ��¼����
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cardIdField; // ����
	private JPasswordField passwordField; // ����
	private JLabel displayPassword; // ��ʾ����
	private JLabel hiddenPassword; // ��������
	private CardInfo cardInfo = null; // �˺ſ���Ϣ
	private UserInfo userInfo = null; // �û���Ϣ
	
	// ��������
	public LoginFrame() {
		// ���ô�������
		setTitle("\u767B\u5F55\u7A97\u53E3");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/ATM\u673A-.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setFocusable(true);

		// ������岢����������
		contentPane = new JPanel();
		// �������Ӷ����¼�
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.requestFocus(); // ��ȡ����
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ������ǩ������������
		JLabel lblAtm = new JLabel("ATM\u673A\u767B\u5F55");
		lblAtm.setFont(new Font("΢���ź�", Font.BOLD, 25));
		lblAtm.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/ATM.png")));
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtm.setBounds(150, 15, 200, 60);
		contentPane.add(lblAtm);

		JLabel label = new JLabel("\u5361\u53F7\uFF1A");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u5361\u53F7.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(100, 100, 100, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u5BC6\u7801.png")));
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label_1.setBounds(100, 170, 100, 20);
		contentPane.add(label_1);

		displayPassword = new JLabel();
		displayPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ("����������".equals(passwordField.getText()))
					return;
				if (passwordField.getEchoChar() == '��') {
					passwordField.setEchoChar((char) 0);
					displayPassword.setVisible(false);
					hiddenPassword.setVisible(true);
				}
			}
		});
		displayPassword.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u663E\u793A\u5BC6\u7801.png")));
		displayPassword.setBounds(410, 170, 15, 20);
		contentPane.add(displayPassword);

		hiddenPassword = new JLabel();
		hiddenPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ("����������".equals(passwordField.getText()))
					return;
				if (passwordField.getEchoChar() != '��') {
					passwordField.setEchoChar('��');
					hiddenPassword.setVisible(false);
					displayPassword.setVisible(true);
				}
			}
		});
		hiddenPassword.setHorizontalAlignment(SwingConstants.CENTER);
		hiddenPassword.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u9690\u85CF\u5BC6\u7801.png")));
		hiddenPassword.setBounds(410, 170, 15, 20);
		hiddenPassword.setVisible(false);
		contentPane.add(hiddenPassword);

		// �����ı�������������
		cardIdField = new JTextField();
		cardIdField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		// Ϊ�ı�����Ӷ����¼�
		cardIdField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if ("�����뿨��".equals(cardIdField.getText())) {
					cardIdField.setText("");
				}
				cardIdField.setForeground(SystemColor.BLACK);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(cardIdField.getText())) {
					cardIdField.setText("�����뿨��");
					cardIdField.setForeground(SystemColor.activeCaptionBorder);
				}
			}
		});
		cardIdField.setText("\u8BF7\u8F93\u5165\u5361\u53F7");
		cardIdField.setForeground(SystemColor.activeCaptionBorder);
		cardIdField.setBounds(200, 95, 200, 30);
		contentPane.add(cardIdField);
		cardIdField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		// Ϊ�ı�����Ӷ����¼�
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if ("����������".equals(passwordField.getText())) {
					passwordField.setText("");
				}
				passwordField.setForeground(SystemColor.BLACK);
				passwordField.setEchoChar('��');
			}

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(passwordField.getText())) {
					passwordField.setText("����������");
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(SystemColor.activeCaptionBorder);
				}
			}
		});
		passwordField.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		passwordField.setForeground(SystemColor.activeCaptionBorder);
		passwordField.setBounds(200, 165, 200, 30);
		passwordField.setEchoChar((char) 0);
		contentPane.add(passwordField);

		// ������ť
		JButton loginButton = new JButton("\u767B\u5F55");
		// Ϊ��ť��Ӷ����¼�
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signIn(e);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u767B\u5F55.png")));
		loginButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		loginButton.setBounds(100, 250, 105, 30);
		contentPane.add(loginButton);

		JButton exitButton = new JButton("\u9000\u51FA");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u9000\u51FA25.png")));
		exitButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		exitButton.setBounds(295, 250, 105, 30);
		contentPane.add(exitButton);
	}

	// ��¼����
	private void signIn(ActionEvent e) {
		// ��ȡ�ı�������
		String cardId = cardIdField.getText();
		String password = passwordField.getText();
		// �ж��ı��������Ƿ�Ϊ��
		if (StringUtil.isEmpty(cardId) || "�����뿨��".equals(cardId)) {
			JOptionPane.showMessageDialog(this, "�����뿨�ţ�");
			return;
		}
		if (StringUtil.isEmpty(password) || "����������".equals(password)) {
			JOptionPane.showMessageDialog(this, "���������룡");
			return;
		}
		// ���ı�����������������Ϊ��Ϣ
		String message = "login " + cardId + " " + password;
		String info = null;
		// ���ÿͻ��������������࣬����¼���صĽ��
		List list = new ConnectToServer().listAccept(message);
		Iterator iter = list.iterator(); // ����������
		// ������������
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof String) {
				info = (String) obj;
			}
			if (obj instanceof CardInfo) {
				cardInfo = (CardInfo) obj;
			}
			if (obj instanceof UserInfo) {
				userInfo = (UserInfo) obj;
			}
		}
		// ����info������ִ�в�ͬ����
		if (info.contains("loginFailed")) { // ��½ʧ��
			JOptionPane.showMessageDialog(this, "���Ż������������"); // ����
			return; // ����������ִ����������
		}
		if (info.contains("loginSuccessfully")) {
			this.dispose(); // �رյ�¼����
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame frame = new MainFrame(cardInfo, userInfo); // ����������
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}
}
