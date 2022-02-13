package com.itcast.util.jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 增删改查工具类
public class CRUDUtil {

	// update方法
	public static int executeUpdate(String sql, Object...params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 获取数据库连接对象
			conn = JDBCUtil.getConn();
			// 预编译sql语句
			ps = conn.prepareStatement(sql);
			// 遍历参数
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行sql语句并返回结果
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, null);
		}
		return 0;
	}
}
