package com.example.testbase.actionbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.example.testbase.kuangjia.R;
import com.example.testbase.util.L;
import com.example.testbase.util.T;

import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.ShareActionProvider;

public class MyActionbar1 extends ActionBarActivity {

	ShareActionProvider mShareActionProvider;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbar1);
		/**
		 * �������Ҫ�Ƴ�ActionBar�Ļ�ͨ�������ַ�ʽ�� һ�ǽ�themeָ����Theme.Holo.NoActionBar��
		 * ��ʾʹ��һ��������ActionBar�����⣬������Activity�е������·�����
		 */

		/**
		 * getActionBar()�����������3.0���ϰ汾���е�,������manifest�嵥�ļ�����Ҫ��ע��
		 * 
		 * < uses-sdk android:minSdkVersion = "11"
		 * 
		 * android:targetSdkVersion = "19" />
		 */
		// android.app.ActionBar actionBar = getActionBar();
		// actionBar.hide();

		setOverflowShowingAlways();
		// action ��ߵļ�ͷͼ��
		android.app.ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.main1, menu);
		// MenuItem searchItem = menu.findItem(R.id.action_search);
		// searchItem.setOnActionExpandListener(new OnActionExpandListener() {
		// @SuppressLint("NewApi")
		// @Override
		// public boolean onMenuItemActionExpand(MenuItem item) {
		// L.i("TAG", "on expand");
		// return true;
		// }
		//
		// @Override
		// public boolean onMenuItemActionCollapse(MenuItem item) {
		// L.i("TAG", "on collapse");
		// return true;
		// }
		// });
		// return super.onCreateOptionsMenu(menu);

		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.main1, menu);
		// MenuItem shareItem = menu.findItem(R.id.action_share);
		// ShareActionProvider provider = (ShareActionProvider)
		// shareItem.getActionProvider();
		// provider.setShareIntent(getDefaultIntent());
		//
		// return super.onCreateOptionsMenu(menu);

		//
		// getMenuInflater().inflate(R.menu.main1, menu);
		// MenuItem item = menu.findItem(R.id.action_share1);
		// ShareActionProvider provider = (ShareActionProvider)
		// item.getActionProvider();
		// return true;

		// getMenuInflater().inflate(R.menu.main1, menu);
		//
		// // Locate MenuItem with ShareActionProvider
		// MenuItem item = menu.findItem(R.id.menu_item_share);
		//
		// // Fetch and store ShareActionProvider
		// mShareActionProvider = (ShareActionProvider)
		// item.getActionProvider();
		//
		// // Return true to display menu
		// Intent shareIntent = new Intent(Intent.ACTION_SEND);
		// shareIntent.setType("image/*");
		// mShareActionProvider.setShareIntent(shareIntent);
		// return true;

		/**
		 * ��Ĭ�ϵ�ע�͵�
		 */
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main1, menu);
		return true;
	}

	private void setShareIntent(Intent shareIntent) {
		if (mShareActionProvider != null) {
			mShareActionProvider.setShareIntent(shareIntent);
		}
	}

	// private Intent getDefaultIntent() {
	// Intent intent = new Intent(Intent.ACTION_SEND);
	// intent.setType("image/*");
	// return intent;}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// home ���Ĭ��ͼ��id
		if (id == R.id.home) {
			// finish(); //���������һ��activity�ͻ᷵��
			// return true;

			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.create(this)
						.addNextIntentWithParentStack(upIntent)
						.startActivities();
			} else {
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_compose) {

			T.showShort(getApplicationContext(), "icon_refresh_loading");
			return true;
		}
		if (id == R.id.action_delete) {
			T.showShort(getApplicationContext(), "Home");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ǹٷ���Ĭ��Ч����Google��Ϊ������overflow�е�Action��ť��Ӧ��ֻ��ʾ���֡�
	 * ��Ȼ���������Ϊ�����������ۣ�ϣ����overflow�е�Action��ťҲ������ʾͼ�꣬������Ȼ������취���ı���һĬ����Ϊ��
	 * ��ʵ��overflow�е�Action��ťӦ��Ӧ����ʾͼ��
	 * ������MenuBuilder������setOptionalIconsVisible�����������ģ�
	 * ���������overflow��չ����ʱ��������������true
	 * ����ô�����ÿһ��Action��ť��Ӧ��ͼ��Ͷ�����ʾ�����ˡ����õķ�����Ȼ��Ȼ���÷����ˣ� ����������ʾ��
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

}
