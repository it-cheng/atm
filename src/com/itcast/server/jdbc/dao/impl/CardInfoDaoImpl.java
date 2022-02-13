package com.itcast.server.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itcast.server.jdbc.dao.ICardInfoDao;
import com.itcast.server.jdbc.domain.CardInfo;
import com.itcast.server.jdbc.domain.UserInfo;
import com.itcast.util.jdbcutil.CRUDUtil;
import com.itcast.util.jdbcutil.JDBCUtil;

// ���п���Ϣ������
public class CardInfoDaoImpl implements ICardInfoDao {

	String info = null;

	@Override
	public List login(String cardId, String password) {
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List list = null;
		CardInfo cardInfo = null;
		UserInfo userInfo = null;
		info = "loginFailed";
		// �������ݿ�
		conn = JDBCUtil.getConn();
		String sql = "select * from card_info, user_Info where card_id = ? and pass = ? and card_info.customer_id = user_info.customer_id";
		try {
			ps = conn.prepareStatement(sql); // Ԥ����sql���
			ps.setString(1, cardId);
			ps.setString(2, password);
			rs = ps.executeQuery(); // ִ��sql���
			list = new ArrayList(); // ��������
			if (rs.next()) {
				info = "loginSuccessfully";

				cardInfo = new CardInfo();
				cardInfo.setCardId(rs.getString("card_id"));
				cardInfo.setPassword(rs.getString("pass"));
				cardInfo.setBalance(rs.getInt("balance"));
				cardInfo.setCustomerId(rs.getInt("customer_id"));

				userInfo = new UserInfo();
				userInfo.setCustomerId(rs.getInt("customer_id"));
				userInfo.setCustomerName(rs.getString("customer_name"));
				userInfo.setPid(rs.getString("pid"));
				userInfo.setTelephone(rs.getString("telephone"));
				userInfo.setAddress(rs.getString("address"));
			}
			// �򼯺������Ԫ��
			list.add(info);
			list.add(cardInfo);
			list.add(userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public String deposit(String cardId, int money) {
		int rs = 0;
		info = "despositFailed";
		// ����sql���
		String sql = "update card_info set balance = balance + ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, cardId); // ��ʵ�ε����޸ķ������������rs
		// ����޸ĳɹ��޸�info��ֵ
		if (rs > 0) {
			info = "depositSuccessfully";
		}
		return info;
	}

	@Override
	public String withdrawals(String cardId, int money) {
		int rs = 0;
		info = "withdrawalsFailed";
		// ����sql���
		String sql = "update card_info set balance = balance - ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, cardId); // ��ʵ�ε����޸ķ������������rs
		// ����޸ĳɹ��޸�info��ֵ
		if (rs > 0) {
			info = "withdrawalsSuccessfully";
		}
		return info;
	}

	@Override
	public String changePassword(String cardId, String newPassword) {
		int rs = 0;
		info = "changeFailed";
		// ����sql���
		String sql = "update card_info set pass = ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, newPassword, cardId); // ��ʵ�ε����޸ķ������������rs
		// ����޸ĳɹ��޸�info��ֵ
		if (rs > 0) {
			info = "changeSuccessfully";
		}
		return info;
	}

	@Override
	public String transferAccounts(String cardId, int money, String transferToCardId) {
		int rs = 0;
		info = "transferFailed";
		Connection conn = null;
		ResultSet res = null;
		PreparedStatement ps = null;
		// �������ݿ�
		conn = JDBCUtil.getConn();
		String sql = "select * from card_info where card_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, transferToCardId);
			res = ps.executeQuery();
			if (!res.next()) {
				info = "notfound";
				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, res);
		}
		// ����sql���
		sql = "update card_info set balance = balance - ? where card_id = ?";
		CRUDUtil.executeUpdate(sql, money, cardId); // ��ʵ�ε����޸ķ������������rs
		sql = "update card_info set balance = balance + ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, transferToCardId); // ��ʵ�ε����޸ķ������������rs
		// ����޸ĳɹ��޸�info��ֵ
		if (rs > 0) {
			info = "transferSuccessfully";
		}
		return info;
	}

	@Override
	public List query(String cardId) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List list = null;
		int money = 0;
		info = "queryFailed";
		// �������ݿ�
		conn = JDBCUtil.getConn();
		String sql = "select * from card_info where card_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cardId);
			rs = ps.executeQuery();
			list = new ArrayList();
			if (rs.next()) {
				info = "querySuccessfully";
				money = rs.getInt("balance");
			}
			list.add(info);
			list.add(money);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
		return list;
	}

}
