package com.example.testbase.actionbar;

import com.example.testbase.kuangjia.R;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyActionbar extends ActionBarActivity {

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbar);
		/**
		 * 而如果想要移除ActionBar的话通常有两种方式，
		 * 一是将theme指定成Theme.Holo.NoActionBar，
		 * 表示使用一个不包含ActionBar的主题，二是在Activity中调用以下方法：
		 */
		
		/**
		 * getActionBar()这个方法是在3.0以上版本才有的,所以在manifest清单文件中需要标注下

         < uses-sdk
         android:minSdkVersion = "11"

         android:targetSdkVersion = "19"   />
		 */
//		android.app.ActionBar actionBar = getActionBar();
//		actionBar.hide();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
