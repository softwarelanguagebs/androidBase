package com.example.testbase.hongyang.activitydialog;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainWeixinTitleRightActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_weixin_top_right);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		finish();
		return true;
	}

	public void tip(View view)
	{
		Toast.makeText(this, "����������ⲿ�رմ���~", Toast.LENGTH_SHORT).show();
	}
}
