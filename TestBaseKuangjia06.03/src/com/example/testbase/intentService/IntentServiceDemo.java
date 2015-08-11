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

package com.example.testbase.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class IntentServiceDemo extends IntentService {

	public IntentServiceDemo() {
		//����ʵ�ָ���Ĺ��췽��
		super("IntentServiceDemo");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		return super.onBind(intent);
	}


	@Override
	public void onCreate() {
		System.out.println("onCreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("onStart");
		super.onStart(intent, startId);
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void setIntentRedelivery(boolean enabled) {
		super.setIntentRedelivery(enabled);
		System.out.println("setIntentRedelivery");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//Intent�Ǵ�Activity�������ģ�Я��ʶ����������ݲ�����ִͬ�в�ͬ������
		String action = intent.getExtras().getString("param");
		if (action.equals("oper1")) {
			System.out.println("Operation1");
		}else if (action.equals("oper2")) {
			System.out.println("Operation2");
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
		super.onDestroy();
	}

}
