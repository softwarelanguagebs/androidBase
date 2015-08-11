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
		 * 而如果想要移除ActionBar的话通常有两种方式， 一是将theme指定成Theme.Holo.NoActionBar，
		 * 表示使用一个不包含ActionBar的主题，二是在Activity中调用以下方法：
		 */

		/**
		 * getActionBar()这个方法是在3.0以上版本才有的,所以在manifest清单文件中需要标注下
		 * 
		 * < uses-sdk android:minSdkVersion = "11"
		 * 
		 * android:targetSdkVersion = "19" />
		 */
		// android.app.ActionBar actionBar = getActionBar();
		// actionBar.hide();

		setOverflowShowingAlways();
		// action 左边的箭头图标
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
		 * 把默认的注释掉
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
		// home 左边默认图标id
		if (id == R.id.home) {
			// finish(); //如果进入下一个activity就会返回
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
	 * 这是官方的默认效果，Google认为隐藏在overflow中的Action按钮都应该只显示文字。
	 * 当然，如果你认为这样不够美观，希望在overflow中的Action按钮也可以显示图标，我们仍然可以想办法来改变这一默认行为。
	 * 其实，overflow中的Action按钮应不应该显示图标
	 * ，是由MenuBuilder这个类的setOptionalIconsVisible方法来决定的，
	 * 如果我们在overflow被展开的时候给这个方法传入true
	 * ，那么里面的每一个Action按钮对应的图标就都会显示出来了。调用的方法当然仍然是用反射了， 代码如下所示：
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
