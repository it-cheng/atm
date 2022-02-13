package com.itcast.client.view.mainframe;

import java.util.Iterator;
import java.util.List;

import com.itcast.client.ConnectToServer;

public class QueryBalance {

	public static void query() {
		String message = "query " + MainFrame.cardInfo.getCardId();
		String info = null;
		int money = 0;
		// ���ÿͻ��������������࣬����¼���صĽ��
		List list = new ConnectToServer().listAccept(message);
		Iterator iter = list.iterator(); // ����������
		// ������������
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
			MainFrame.cardInfo.setBalance(money); // ���������˺ſ������
		}
	}
}
