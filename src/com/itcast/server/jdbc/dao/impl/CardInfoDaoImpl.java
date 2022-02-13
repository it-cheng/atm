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

// 银行卡信息处理类
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
		// 连接数据库
		conn = JDBCUtil.getConn();
		String sql = "select * from card_info, user_Info where card_id = ? and pass = ? and card_info.customer_id = user_info.customer_id";
		try {
			ps = conn.prepareStatement(sql); // 预编译sql语句
			ps.setString(1, cardId);
			ps.setString(2, password);
			rs = ps.executeQuery(); // 执行sql语句
			list = new ArrayList(); // 创建集合
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
			// 向集合中添加元素
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
		// 创建sql语句
		String sql = "update card_info set balance = balance + ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, cardId); // 用实参调用修改方法并将结果给rs
		// 如果修改成功修改info的值
		if (rs > 0) {
			info = "depositSuccessfully";
		}
		return info;
	}

	@Override
	public String withdrawals(String cardId, int money) {
		int rs = 0;
		info = "withdrawalsFailed";
		// 创建sql语句
		String sql = "update card_info set balance = balance - ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, cardId); // 用实参调用修改方法并将结果给rs
		// 如果修改成功修改info的值
		if (rs > 0) {
			info = "withdrawalsSuccessfully";
		}
		return info;
	}

	@Override
	public String changePassword(String cardId, String newPassword) {
		int rs = 0;
		info = "changeFailed";
		// 创建sql语句
		String sql = "update card_info set pass = ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, newPassword, cardId); // 用实参调用修改方法并将结果给rs
		// 如果修改成功修改info的值
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
		// 连接数据库
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
		// 创建sql语句
		sql = "update card_info set balance = balance - ? where card_id = ?";
		CRUDUtil.executeUpdate(sql, money, cardId); // 用实参调用修改方法并将结果给rs
		sql = "update card_info set balance = balance + ? where card_id = ?";
		rs = CRUDUtil.executeUpdate(sql, money, transferToCardId); // 用实参调用修改方法并将结果给rs
		// 如果修改成功修改info的值
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
		// 连接数据库
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
