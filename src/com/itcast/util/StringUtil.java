package com.itcast.util;

// 字符串工具类
public class StringUtil {
	// 判断字符串是否为空
	public static boolean isEmpty(String str) {
		if("".equals(str) || str == null) {
			return true; // 字符串为空或为空值时返回真
		}
		return false; // 字符串不为空时返回假
	}

}
