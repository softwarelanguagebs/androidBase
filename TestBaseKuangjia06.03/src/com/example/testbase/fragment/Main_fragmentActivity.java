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
		data.putString("TEXT", "这是Activiy通过Bundle传递过来的值");
		fragment1.setArguments(data);//通过Bundle向Activity中传递值
		fragmentTransaction.add(R.id.rl_container,fragment1);//将fragment1设置到布局上
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commitAllowingStateLoss();
		currentFragment = fragment1;
		//初始化button控件
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(currentFragment instanceof MyFragment1){
					switchFragment();
				}else{//当前是fragment2，因此，只需要将fragment2出栈即可变成fragment1
					fragmentManager.popBackStack();
					currentFragment = fragment1;
				}
			}
		});
	}
	/**
	 * 切换Fragment
	 */
	private void switchFragment(){
		if(null == fragment2){//可以避免切换的时候重复创建
			fragment2 = new MyFragment2();
		}
		Bundle data = new Bundle();
		data.putString("TEXT", "传递给fragment2");
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
		switchFragment();//通过回调方式切换
	}

	@Override
	public void callbackFun2(Bundle arg) {
		// TODO Auto-generated method stub
		changeButtonColor();//通过回调方式调用Activity中的方法
	}

}
