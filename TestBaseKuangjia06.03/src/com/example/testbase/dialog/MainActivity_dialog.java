package com.example.testbase.dialog;

import java.io.File;

import com.example.testbase.kuangjia.R;
import com.example.testbase.net.Request;
import com.example.testbase.net.Request.RequestMethod;
import com.example.testbase.net.StringCallback;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_dialog extends Activity implements OnClickListener {

	Button btn;
	TextView tv;
	CustormDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_net);

		btn = (Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(this);
		tv = (TextView) this.findViewById(R.id.tv);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn:

			Log.i("Tag", "jinlaile");

			dialog = new CustormDialog(MainActivity_dialog.this, "放标题", "放内容",
					R.style.CustomDialog_1, new DialogCallBack() {
						@Override
						public void OkDown() {
							dialog.dismiss();

						}

						@Override
						public void CancleDown() {
							dialog.dismiss();
						}

					}, 2);

			break;
		}

	}

}
