package com.example.testbase.dialog2;

import com.example.testbase.kuangjia.R;
import com.example.testbase.dialog1.MyDlgActivity;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogActivity extends Activity implements OnClickListener {

	Button btn1;

	Button btn2;

	private Dialog mDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_dialog);
		setView();
		setListner();
	}

	private void setView() {
		btn1 = (Button) this.findViewById(R.id.btn1_dia);
		btn2 = (Button) this.findViewById(R.id.btn2_dia);

	}

	private void setListner() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1_dia:

			showRequestDialog();

			break;

		case R.id.btn2_dia:

			mDialog.dismiss();

			break;

		default:
			break;
		}

	}

	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "正在验证账号...");
		mDialog.show();
	}

}
