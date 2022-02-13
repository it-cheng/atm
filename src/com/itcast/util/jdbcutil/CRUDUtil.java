package com.itcast.util.jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// ��ɾ�Ĳ鹤����
public class CRUDUtil {

	// update����
	public static int executeUpdate(String sql, Object...params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// ��ȡ���ݿ����Ӷ���
			conn = JDBCUtil.getConn();
			// Ԥ����sql���
			ps = conn.prepareStatement(sql);
			// ��������
			for(int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// ִ��sql��䲢���ؽ��
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, null);
		}
		return 0;
	}
}
