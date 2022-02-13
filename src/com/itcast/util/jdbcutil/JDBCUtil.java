package com.itcast.util.jdbcutil; // 该包存放工具类

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 数据库连接工具类
public class JDBCUtil {
	
	public static String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=UTC"; // 数据库地址
	public static String user = "root"; // 数据库用户名
	public static String password = "root"; // 数据库密码
	public static String driverName = "com.mysql.cj.jdbc.Driver"; // 驱动名
	
	// 驱动只需加载一次，可用静态代码块来让驱动随着类的加载而加载
	static {
		try {
			// 加载驱动
			Class.forName(JDBCUtil.driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 获取数据库连接对象
	public static Connection getConn() {
		try {
			return DriverManager.getConnection(JDBCUtil.url, JDBCUtil.user, JDBCUtil.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 释放资源
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
