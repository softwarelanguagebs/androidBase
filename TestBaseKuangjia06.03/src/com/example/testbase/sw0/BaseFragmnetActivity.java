package com.example.testbase.sw0;

import java.util.Timer;
import java.util.TimerTask;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
public abstract class BaseFragmnetActivity extends FragmentActivity {

	public static final String TAG = BaseFragmnetActivity.class.getSimpleName();

	// protected Handler mHandler = null;
	// 在点一次退出，程序
	private static Boolean isExit = false;
	private static Boolean hasTask = false;

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

	// 按2次返回键退出事件
	Timer tExit = new Timer();
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			isExit = false;
			hasTask = true;
		}
	};

	/**
	 * 返回健 再按一次退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			if (isExit == false) {
				isExit = true;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				/*
				 * if (!hasTask) { tExit.schedule(task, 1000); }
				 */
			} else {

				// 在按一次才执行
				// 关闭所有集合中的activity
				// ActivityStackControlUtil.finishProgram();

				// 新的 关闭所有的
				AppManager.getInstance().AppExit(getApplicationContext());

				// 每次都提示 在按一次退出。
				isExit = false;

			}

		}

		return false;
		// return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// 关闭集合中的activity
		// ActivityStackControlUtil.remove(this);
		// AppManager.getInstance().AppExit(getApplicationContext()); // 直接退出了
		// 清楚图片 ImageLoader.getInstance().clearMemoryCache();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// JPushInterface.onPause(this);
	//	StatService.onPause(this);// 百度统计
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
		// JPushInterface.onResume(this);
		//StatService.onResume(this);// 百度统计
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

	/**
	 * 初始化接收数据 包括接收传递的intent参数和实例化 相关类对象。。。
	 */
	protected abstract void initData();

	// 子类需要继承和实现的。。。方法。。
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

		// 退出，进入动画 ;android sdk支持至少是5 ////////////////动画 进入进出 ///////// 基类
		// overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
