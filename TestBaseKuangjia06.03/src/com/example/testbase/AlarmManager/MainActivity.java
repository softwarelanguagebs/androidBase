package com.example.testbase.AlarmManager;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	public static final String TAG ="PollingService";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_alarm);
		//Start polling service
		System.out.println("Start polling service...");
		Log.i(TAG, "Start polling service...");
		PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//Stop polling service
		System.out.println("Stop polling service...");
		Log.i(TAG, "Stop polling service...");
		PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
	}

}
