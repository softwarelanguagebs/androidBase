package com.example.testbase.fragment;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *2014-5-16  Nanjing,njupt,China
 */
public class MyFragment2 extends Fragment {
	
	FragmentCallBack fragmentCallBack = null;
	Button btn1;
	Button btn2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment2, container,
				false);
		Bundle data = getArguments();//获得从activity中传递过来的值
		Toast.makeText(getActivity(), data.getString("TEXT"), Toast.LENGTH_SHORT).show();
		btn1 = (Button)rootView.findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 直接调用Activity中的方法
				((Main_fragmentActivity)getActivity()).changeButtonColor();
			}
		});
		btn2 = (Button)rootView.findViewById(R.id.btn2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 回调的方式
				fragmentCallBack.callbackFun2(null);
			}
		});
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		fragmentCallBack = (Main_fragmentActivity)activity;
	}
}
