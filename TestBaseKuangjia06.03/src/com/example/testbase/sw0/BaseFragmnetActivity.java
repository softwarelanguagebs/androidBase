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
 * @date 2013��9��12��
 * 
 * @version V_1.0.0
 * 
 * @description
 * 
 */
public abstract class BaseFragmnetActivity extends FragmentActivity {

	public static final String TAG = BaseFragmnetActivity.class.getSimpleName();

	// protected Handler mHandler = null;
	// �ڵ�һ���˳�������
	private static Boolean isExit = false;
	private static Boolean hasTask = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// ��ʼ�� ͼƬ ���� ������ÿ��ҳ�涼��Ҫ���� ; application�����Ѿ���ʼ���ˣ�����ֻ���ж� һ��
		/*
		 * if (!ImageLoader.getInstance().isInited()) {
		 * 
		 * ImageLoaderConfig.initImageLoader(this, Const.BASE_IMAGE_CACHE); }
		 */

		// �ɵ�ǰ��activity���� ��ע�� �˳���ʱ�� ��� //////// ���� ���Զ����� ������
		AppManager.getInstance().addActivity(this);
	}

	// ��2�η��ؼ��˳��¼�
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
	 * ���ؽ� �ٰ�һ���˳�����
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			if (isExit == false) {
				isExit = true;
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				/*
				 * if (!hasTask) { tExit.schedule(task, 1000); }
				 */
			} else {

				// �ڰ�һ�β�ִ��
				// �ر����м����е�activity
				// ActivityStackControlUtil.finishProgram();

				// �µ� �ر����е�
				AppManager.getInstance().AppExit(getApplicationContext());

				// ÿ�ζ���ʾ �ڰ�һ���˳���
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

		// �رռ����е�activity
		// ActivityStackControlUtil.remove(this);
		// AppManager.getInstance().AppExit(getApplicationContext()); // ֱ���˳���
		// ���ͼƬ ImageLoader.getInstance().clearMemoryCache();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// JPushInterface.onPause(this);
	//	StatService.onPause(this);// �ٶ�ͳ��
		// umeng
		// MobclickAgent.onPause(this); //umengͳ��
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
		//StatService.onResume(this);// �ٶ�ͳ��
		// uemng ���·���
		// MobclickAgent.onResume(this); //umengͳ��
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
	 * ��ʼ���������� �������մ��ݵ�intent������ʵ���� �������󡣡���
	 */
	protected abstract void initData();

	// ������Ҫ�̳к�ʵ�ֵġ�������������
	/**
	 * ��ʼ���ؼ�
	 */
	protected abstract void initView();

	/**
	 * �����¼�����
	 */
	protected abstract void setLinstener();

	/**
	 * ������
	 * 
	 */
	protected abstract void fillData();

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

		// �˳������붯�� ;android sdk֧��������5 ////////////////���� ������� ///////// ����
		// overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		// overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		overridePendingTransition(android.R.anim.slide_out_right,
				android.R.anim.slide_in_left);
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
	 * showAlertDialog("�˳�����", "ȷ���˳��߷¾����̳ǣ�", "ȷ��", new OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { //
	 * TODO Auto-generated method stub
	 * AppManager.getInstance().AppExit(getApplicationContext());
	 * ImageLoader.getInstance().clearMemoryCache(); } }, "ȡ��", new
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
	 *//** ���б��⡢���ݡ�������ť�ĶԻ��� **/
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
