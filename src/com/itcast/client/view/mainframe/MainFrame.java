package com.itcast.client.view.mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itcast.client.view.mainframe.financialservice.DepositFrame;
import com.itcast.client.view.mainframe.financialservice.QueryFrame;
import com.itcast.client.view.mainframe.financialservice.TransferAccountsFrame;
import com.itcast.client.view.mainframe.financialservice.WithdrawalsFrame;
import com.itcast.client.view.mainframe.informationservice.DisplayInformation;
import com.itcast.client.view.mainframe.systemsetup.ChangePasswordFrame;
import com.itcast.server.jdbc.domain.CardInfo;
import com.itcast.server.jdbc.domain.UserInfo;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	public static CardInfo cardInfo;
	public static UserInfo userInfo;

	// ����������
	public MainFrame(CardInfo cardInfo, UserInfo userInfo) {
		
		this.cardInfo = cardInfo;
		this.userInfo = userInfo;
		
		// ���ô�������
		setTitle("ATM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/ATM\u673A-.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// �����˵����������������Լ�Ϊ������¼�
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u8BBE\u7F6E16.png")));
		menu.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u9000\u51FA16.png")));
		menuItem.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u4FEE\u6539\u5BC6\u780116.png")));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword(e); 
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u91D1\u878D\u670D\u52A1");
		menu_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u91D1\u878D\u670D\u52A1.png")));
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5B58\u6B3E");
		menuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u5B58\u6B3E16.png")));
		menuItem_2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit(e);
			}
		});
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u53D6\u6B3E");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdrawals(e);
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u53D6\u6B3E16.png")));
		menuItem_3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menu_1.add(menuItem_3);
		
		JMenuItem menuItem_6 = new JMenuItem("\u67E5\u8BE2");
		menuItem_6.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u67E5\u8BE216.png")));
		menuItem_6.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query(e);
			}
		});
		menu_1.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("\u8F6C\u8D26");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferAccounts(e);
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u8F6C\u8D2616.png")));
		menuItem_7.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menu_1.add(menuItem_7);
		
		JMenu menu_2 = new JMenu("\u4FE1\u606F\u670D\u52A1");
		menu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u4FE1\u606F\u670D\u52A1.png")));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("\u663E\u793A\u4FE1\u606F");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayInformation(e);
			}
		});
		menuItem_4.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u67E5\u8BE216.png")));
		menu_2.add(menuItem_4);
		
		JMenu menu_3 = new JMenu("\u5E2E\u52A9");
		menu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u5E2E\u52A916.png")));
		menu_3.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menuBar.add(menu_3);
		
		JMenuItem menuItem_5 = new JMenuItem("\u8054\u7CFB\u5BA2\u670D");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contactCustomerService(e);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u8054\u7CFB\u5BA2\u670D16.png")));
		menuItem_5.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		menu_3.add(menuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.CYAN);
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	// ��ϵ�ͷ�����
	private void contactCustomerService(ActionEvent e) {
		String info = "�ͷ��绰��0373-12345\n";
		info = info + "�Ƿ�������ϵ�ͷ���";
		String[] buttons = {"��","��"}; // ��ť����
		int ret = JOptionPane.showOptionDialog(this, info, "��ϵ�ͷ�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon(MainFrame.class.getResource("/images/\u8054\u7CFB\u5BA2\u670D48.png")), buttons, null);
		// ret���ص���buttons���±�				
		if(ret == 0) {
			JOptionPane.showMessageDialog(this, "��Ϊ����ϵ�ͷ������Ժ�");
			
		}
		
	}

	// ת�˷���
	private void transferAccounts(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferAccountsFrame frame = new TransferAccountsFrame();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	// �޸����뷽�� 
	private void changePassword(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordFrame frame = new ChangePasswordFrame();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	// ��ʾ��Ϣ����
	private void displayInformation(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayInformation frame = new DisplayInformation();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ȡ���
	private void withdrawals(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawalsFrame frame = new WithdrawalsFrame();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ��ѯ����
	private void query(ActionEvent e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryFrame frame = new QueryFrame();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ����
	private void deposit(ActionEvent r) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositFrame frame = new DepositFrame();
					frame.setVisible(true);
					desktopPane.add(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}