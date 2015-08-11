package com.example.testbase.baseactivity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

 //���˳��ĵط� ActivityCollector.finishAll();
//baseFragment ����ǰ������

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015��5��19�� ����10:59:40
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
	//	ActivityCollector.getInstance().addActivity(this); //ûʹ��
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
	 * ͨ����������Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * ͨ����������Activity�����Һ���Bundle����
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
	 * ͨ��Action����Activity
	 * 
	 * @param pAction
	 */
	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	/**
	 * ͨ��Action����Activity�����Һ���Bundle����
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
	 * ��ʼ���ؼ�
	 */
	protected abstract void initView();
	
	/**
	 * ��ʼ���������� �������մ��ݵ�intent������ʵ���� �������󡣡���
	 */
	protected abstract void initData();

	/**
	 * �����¼�����
	 */
	protected abstract void setLinstener();

	/**
	 * ������
	 * 
	 */
	protected abstract void fillData();
	
	
	
	//�������dialog֮��ķ���
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
