package com.itcast.client.view.mainframe;

import java.util.Iterator;
import java.util.List;

import com.itcast.client.ConnectToServer;

public class QueryBalance {

	public static void query() {
		String message = "query " + MainFrame.cardInfo.getCardId();
		String info = null;
		int money = 0;
		// 调用客户端与服务端连接类，并记录返回的结果
		List list = new ConnectToServer().listAccept(message);
		Iterator iter = list.iterator(); // 创建迭代器
		// 迭代遍历集合
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof String) {
				info = (String) obj;
			}
			if(obj instanceof Integer) {
				money = (int) obj;
			}
		}
		if(info.contains("querySuccessfully")) {
			MainFrame.cardInfo.setBalance(money); // 重新设置账号卡的余额
		}
	}
}
