package com.example.testbase.log;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ToastLogActivity extends Activity implements OnClickListener {
	private String TAG = "ToastLogActivity";
	ImageView imv_back;
	TextView tv_title;
	Button btn1, btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_log);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
	}

	private void initValue() {

	}

	private void setLinstener() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

	}

	private void fillData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn1:

			T.showLong(getApplicationContext(), "hello");
			break;

		case R.id.btn2:
			ToastMy.showShortToast(getApplicationContext(), "hello");
			LogUtils.getLog(getClass()).info("hhahhhhh"); //Ð§¹û£º[ToastLogActivity]  Line: 64 : hhahhhhh
			ML.getLogDatail(getClass(), Thread.currentThread()).info("hello,Log");
			L.i(TAG,  Thread.currentThread().getStackTrace()[1].getMethodName());
			ML.getLog(getClass()).info("hahaha");
		
			
			break;

		default:
			break;
		}

	}

}
