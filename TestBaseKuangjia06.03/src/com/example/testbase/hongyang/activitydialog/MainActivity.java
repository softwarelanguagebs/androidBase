package com.example.testbase.hongyang.activitydialog;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_dialog);
	}

	public void functionKey(View view)
	{
		Intent intent = new Intent(this, MainWeixinTitleRightActivity.class);  
        startActivity(intent);  
	}

}
