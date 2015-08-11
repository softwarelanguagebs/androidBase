package com.example.testbase.baseactivity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

 //在退出的地方 ActivityCollector.finishAll();
//baseFragment 在以前代码中

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015年5月19日 上午10:59:40
 * @version:  V1.0
 */
public abstract class BaseActivity extends Activity {

	private static String TAG;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = getClass().getSimpleName();
		context = this;
	//	ActivityCollector.getInstance().addActivity(this); //没使用
		ActivityCollector.addActivity(this);
		
	}

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
	
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
	
	/**
	 * 初始化控件
	 */
	protected abstract void initView();
	
	/**
	 * 初始化接收数据 包括接收传递的intent参数和实例化 相关类对象。。。
	 */
	protected abstract void initData();

	/**
	 * 设置事件。。
	 */
	protected abstract void setLinstener();

	/**
	 * 绑定数据
	 * 
	 */
	protected abstract void fillData();
	
	
	
	//可以添加dialog之类的方法
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
