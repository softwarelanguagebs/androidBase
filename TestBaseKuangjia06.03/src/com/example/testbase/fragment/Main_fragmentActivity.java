package com.example.testbase.fragment;

import com.example.testbase.kuangjia.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main_fragmentActivity  extends ActionBarActivity implements FragmentCallBack{
	
	private Button btn;
	
	private MyFragment1 fragment1;
	private MyFragment2 fragment2;
	private FragmentManager fragmentManager;
	private Fragment currentFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment_activity);
		fragmentManager = getSupportFragmentManager();
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
		fragment1 = new MyFragment1();
		Bundle data = new Bundle();
		data.putString("TEXT", "����Activiyͨ��Bundle���ݹ�����ֵ");
		fragment1.setArguments(data);//ͨ��Bundle��Activity�д���ֵ
		fragmentTransaction.add(R.id.rl_container,fragment1);//��fragment1���õ�������
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commitAllowingStateLoss();
		currentFragment = fragment1;
		//��ʼ��button�ؼ�
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(currentFragment instanceof MyFragment1){
					switchFragment();
				}else{//��ǰ��fragment2����ˣ�ֻ��Ҫ��fragment2��ջ���ɱ��fragment1
					fragmentManager.popBackStack();
					currentFragment = fragment1;
				}
			}
		});
	}
	/**
	 * �л�Fragment
	 */
	private void switchFragment(){
		if(null == fragment2){//���Ա����л���ʱ���ظ�����
			fragment2 = new MyFragment2();
		}
		Bundle data = new Bundle();
		data.putString("TEXT", "���ݸ�fragment2");
		fragment2.setArguments(data);
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
		fragmentTransaction.add(R.id.rl_container,fragment2);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commitAllowingStateLoss();
		currentFragment = fragment2;
	}
	
	public void changeButtonColor(){
		btn.setBackgroundColor(Color.RED);
	}

	@Override
	public void callbackFun1(Bundle arg) {
		// TODO Auto-generated method stub
		switchFragment();//ͨ���ص���ʽ�л�
	}

	@Override
	public void callbackFun2(Bundle arg) {
		// TODO Auto-generated method stub
		changeButtonColor();//ͨ���ص���ʽ����Activity�еķ���
	}

}
