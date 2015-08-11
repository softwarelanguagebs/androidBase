/*
 * �ļ�����PollingService.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��23��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.AlarmManager;

import com.example.testbase.kuangjia.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PollingService extends Service {

	public static final String ACTION = "com.ryantang.service.PollingService";
	public static final String TAG ="PollingService";
	
	private Notification mNotification;
	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		initNotifiManager();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		new PollingThread().start();
	}

	//��ʼ��֪ͨ������
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int icon = R.drawable.ic_launcher;
		mNotification = new Notification();
		mNotification.icon = icon;
		mNotification.tickerText = "New Message";
		mNotification.defaults |= Notification.DEFAULT_SOUND;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
	}

	//����Notification
	private void showNotification() {
		mNotification.when = System.currentTimeMillis();
		//Navigator to the new activity when click the notification title
		Intent i = new Intent(this, MessageActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		mNotification.setLatestEventInfo(this,
				getResources().getString(R.string.app_name), "You have new message!", pendingIntent);
		mManager.notify(0, mNotification);
	}

	/**
	 * Polling thread
	 * ģ����Server��ѯ���첽�߳�
	 * @Author Ryan
	 * @Create 2013-7-13 ����10:18:34
	 */
	int count = 0;
	class PollingThread extends Thread {
		@Override
		public void run() {
			System.out.println("Polling...");
			Log.i(TAG, "Polling...");
			count ++;
			//�������ܱ�5����ʱ����֪ͨ
			if (count % 5 == 0) {
				showNotification();
				System.out.println("New message!");
				Log.i(TAG, "New message!");
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
		Log.i(TAG, "Service:onDestroy!");
	}

}