package com.example.testbase.sw2;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * @author sw
 * 
 * @email
 * 
 * @date 2013年9月12日
 * 
 * @version V_1.0.0
 * 
 * @description
 * 
 */
public abstract class BaseActivity extends Activity implements OnClickListener {

	public static final String TAG = BaseActivity.class.getSimpleName();

	// protected Handler mHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 初始化 图片 设置 ，避免每个页面都需要设置 ; application里面已经初始化了，这里只是判断 一下
		/*
		 * if (!ImageLoader.getInstance().isInited()) {
		 * 
		 * ImageLoaderConfig.initImageLoader(this, Const.BASE_IMAGE_CACHE); }
		 */

		// 吧当前的activity加入 ，注意 退出的时候 清空 //////// 子类 会自动加入 管理集合
		AppManager.getInstance().addActivity(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			this.finish();

			// 结束动画
			overridePendingTransition(R.anim.open_main, R.anim.close_next);

		}

		return false;
		// return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// 关闭集合中的activity
		ActivityStackControlUtil.remove(this);
		// AppManager.getInstance().AppExit(getApplicationContext()); // 直接退出了
		// 清楚图片 ImageLoader.getInstance().clearMemoryCache();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// StatService.onPause(this);// 百度统计
		// umeng
		// MobclickAgent.onPause(this); //umeng统计
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// StatService.onResume(this);// 百度统计
		// uemng 更新服务
		// MobclickAgent.onResume(this); //umeng统计
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	// 子类需要继承和实现的。。。方法。。

	/**
	 * 初始化接收数据 包括接收传递的intent参数和实例化 相关类对象。。。
	 */
	protected abstract void initData();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

	/**
	 * 设置事件。。
	 */
	protected abstract void setLinstener();

	/**
	 * 绑定数据
	 * 
	 */
	protected abstract void fillData();

	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);

		overridePendingTransition(android.R.anim.slide_out_right,
				android.R.anim.slide_in_left);

	}

	/**
	 * 通过Action启动Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);

		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // TODO
	 * Auto-generated method stub
	 * getMenuInflater().inflate(R.menu.activity_menu, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // TODO
	 * Auto-generated method stub switch (item.getItemId()) { case
	 * R.id.menu_about:
	 * 
	 * break;
	 * 
	 * case R.id.menu_setting:
	 * 
	 * break;
	 * 
	 * case R.id.menu_history:
	 * 
	 * break;
	 * 
	 * case R.id.menu_feedback:
	 * 
	 * break;
	 * 
	 * case R.id.menu_exit:
	 * 
	 * showAlertDialog("退出程序", "确定退出高仿京东商城？", "确定", new OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub
	 * AppManager.getInstance().AppExit(getApplicationContext());
	 * ImageLoader.getInstance().clearMemoryCache(); } }, "取消", new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub dialog.dismiss(); } });
	 * 
	 * break;
	 * 
	 * case R.id.menu_help:
	 * 
	 * break;
	 * 
	 * default: break; } return true; }
	 *//** 含有标题、内容、两个按钮的对话框 **/
	/*
	 * protected void showAlertDialog(String title, String message, String
	 * positiveText, DialogInterface.OnClickListener onPositiveClickListener,
	 * String negativeText, DialogInterface.OnClickListener
	 * onNegativeClickListener) { new
	 * AlertDialog.Builder(this).setTitle(title).setMessage(message)
	 * .setPositiveButton(positiveText, onPositiveClickListener)
	 * .setNegativeButton(negativeText, onNegativeClickListener) .show(); }
	 */

}
