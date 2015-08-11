package com.example.testbase.CommonEditText;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class editTextActivity extends Activity implements OnClickListener {
	private String TAG = "CargoEvaluationActivity";
	ImageView imv_back;
	TextView tv_title;
	CommonEditText cedt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_autoclear_text);
		setupView();
		initValue();
		setLinstener();
		fillData();

	}

	private void setupView() {
		cedt = (CommonEditText) this.findViewById(R.id.input_new_phonenumber);
	}

	private void initValue() {

	}

	private void setLinstener() {

	}

	private void fillData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		// switch (v.getId())
		// {
		//
		// case R.id.imv_back:
		//
		// this.finish();
		// break;
		//
		// default:
		// break;
		// }

	}

}
