package com.itcast.server.jdbc.dao;

import java.util.List;

// �y�п���Ϣ�ӿ�
public interface ICardInfoDao {

	List login(String cardId, String password);
	String deposit(String cardId, int money);
	String withdrawals(String cardId, int money);
	String changePassword(String cardId, String newPassword);
	String transferAccounts(String cardId, int money, String transferToCardId);
	List query(String cardId);
	
}
