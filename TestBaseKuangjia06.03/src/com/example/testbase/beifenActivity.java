package com.example.testbase;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class beifenActivity extends Activity implements OnClickListener {

	public static final String TAG = beifenActivity.class.getSimpleName();

	Context context = beifenActivity.this;
	TextView tv_title;
	ImageView imv_back;

	Button btn_submit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setLinstener();
		initData();
		fillData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		// case R.id.tv_reget_pwd:
		// openActivity(MainTab2.class,null);
		// break;
		default:
			break;
		}

	}

	
	protected void initData() {
		// TODO Auto-generated method stub
	//	tv_title.setText("зЂВс");

	}


	protected void initView() {
		
		// tv_register = (TextView) this.findViewById(R.id.tv_register);
		
	}

	
	protected void setLinstener() {
		
		// tv_reget_pwd.setOnClickListener(this);

	}


	protected void fillData() {
		// TODO Auto-generated method stub

	}

}
