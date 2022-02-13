package com.itcast.util.jdbcutil; // �ð���Ź�����

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// ���ݿ����ӹ�����
public class JDBCUtil {
	
	public static String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=UTC"; // ���ݿ��ַ
	public static String user = "root"; // ���ݿ��û���
	public static String password = "root"; // ���ݿ�����
	public static String driverName = "com.mysql.cj.jdbc.Driver"; // ������
	
	// ����ֻ�����һ�Σ����þ�̬�������������������ļ��ض�����
	static {
		try {
			// ��������
			Class.forName(JDBCUtil.driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// ��ȡ���ݿ����Ӷ���
	public static Connection getConn() {
		try {
			return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// �ͷ���Դ
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
