package com.itcast.util;

// �ַ���������
public class StringUtil {
	// �ж��ַ����Ƿ�Ϊ��
	public static boolean isEmpty(String str) {
		if("".equals(str) || str == null) {
			return true; // �ַ���Ϊ�ջ�Ϊ��ֵʱ������
		}
		return false; // �ַ�����Ϊ��ʱ���ؼ�
	}

}
