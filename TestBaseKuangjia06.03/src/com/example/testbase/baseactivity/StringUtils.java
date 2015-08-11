/*
 * 文件名：StringUtils.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月24日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.baseactivity;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StringUtils {

	public static boolean isEmpty(CharSequence str) {
		if (str == null || str.length() == 0 || str.equals("")
				|| str.equals("null"))
			return true;
		else
			return false;
	}

	public static String clearEmpty(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str;
		}
	}

	public static void printMap(Map<String, String> paraMap) {
		// 第一种方法：
		Iterator iterator = paraMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			String value = (String) entry.getValue();
			String key = (String) entry.getKey();

			// System.out.println("key:" + key);
			// System.out.println("value:" + paraMap.get(key));

			System.out.println(key + "=" + paraMap.get(key));
		}

	}

	public static String clearEmptyMeter(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str + "米";
		}
	}

	public static String clearEmptyTon(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str + "吨";
		}
	}

	public static String clearEmptyCube(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str + "方";
		}
	}
}
