/*
 * �ļ�����StringUtils.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��24��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
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
		// ��һ�ַ�����
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
			return (String) str + "��";
		}
	}

	public static String clearEmptyTon(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str + "��";
		}
	}

	public static String clearEmptyCube(CharSequence str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return (String) str + "��";
		}
	}
}
