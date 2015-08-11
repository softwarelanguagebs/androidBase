/*
 * �ļ�����PollingUtils.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��23��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.AlarmManager;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
/**
 * Polling Tools
 * @Author Ryan
 * @Create 2013-7-13 ����10:14:43
 */

//http://blog.csdn.net/ryantang03/article/details/9317499
public class PollingUtils {

	//������ѯ����
	public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
		//��ȡAlarmManagerϵͳ����
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		
		//��װ��Ҫִ��Service��Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//�����������ʼʱ��
		long triggerAtTime = SystemClock.elapsedRealtime();
		
		//ʹ��AlarmManger��setRepeating�������ö���ִ�е�ʱ������seconds�룩����Ҫִ�е�Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	//ֹͣ��ѯ����
	public static void stopPollingService(Context context, Class<?> cls,String action) {
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//ȡ������ִ�еķ���
		manager.cancel(pendingIntent);
	}
}