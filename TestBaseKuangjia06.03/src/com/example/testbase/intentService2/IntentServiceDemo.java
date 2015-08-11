/*
 * �ļ�����IntentServiceDemo.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��23��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.intentService2;

import android.app.IntentService;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class IntentServiceDemo extends IntentService {

	private static String TAG = "IntentServiceDemo";

	public IntentServiceDemo() {
		// ����ʵ�ָ���Ĺ��췽��
		super("IntentServiceDemo");
		Log.i(TAG, "����" + "IntentServiceDemo");
	}

	@Override
	public IBinder onBind(Intent intent) {

		Log.i(TAG, "onBind");
		return super.onBind(intent);
	}

	@Override
	public void onCreate() {

		Log.i(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("onStart");

		Log.i(TAG, "onStart");

		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void setIntentRedelivery(boolean enabled) {
		super.setIntentRedelivery(enabled);
		System.out.println("setIntentRedelivery");
		Log.i(TAG, "setIntentRedelivery");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// Intent�Ǵ�Activity�������ģ�Я��ʶ����������ݲ�����ִͬ�в�ͬ������
		String action = intent.getExtras().getString("param");
		if (action.equals("oper1")) {
			System.out.println("Operation1");
			Log.i(TAG, "Operation1");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper1");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper2")) {
			Log.i(TAG, "Operation2");
			System.out.println("Operation2");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper2");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper3")) {
			Log.i(TAG, "Operation3");
			System.out.println("Operation3");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper3");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper4")) {
			Log.i(TAG, "Operation4");
			System.out.println("Operation4");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper4");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper5")) {
			Log.i(TAG, "Operation5");
			System.out.println("Operation5");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper5");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper6")) {
			Log.i(TAG, "Operation6");
			System.out.println("Operation6");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper6");
			sendBroadcast(broadcastIntent);

		} else if (action.equals("oper7")) {
			Log.i(TAG, "Operation7");
			System.out.println("Operation7");
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("MyIS");
			broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
			broadcastIntent.putExtra("result", "oper7");
			sendBroadcast(broadcastIntent);

		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

}
