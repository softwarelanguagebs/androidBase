/*
 * 文件名：PollingUtils.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
 * @Create 2013-7-13 上午10:14:43
 */

//http://blog.csdn.net/ryantang03/article/details/9317499
public class PollingUtils {

	//开启轮询服务
	public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
		//获取AlarmManager系统服务
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		
		//包装需要执行Service的Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//触发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime();
		
		//使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	//停止轮询服务
	public static void stopPollingService(Context context, Class<?> cls,String action) {
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//取消正在执行的服务
		manager.cancel(pendingIntent);
	}
}