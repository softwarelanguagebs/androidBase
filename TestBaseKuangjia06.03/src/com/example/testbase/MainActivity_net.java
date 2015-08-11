package com.example.testbase;

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

public class MainActivity_net extends Activity implements OnClickListener {

	Button btn;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_net);

		btn = (Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(this);
		tv = (TextView) this.findViewById(R.id.tv);

	}

	private void requestString() {
		// …Ë÷√±£¥Ê¬∑æ∂
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator + "mrfu_http.txt";
		Request request = new Request("http://www.baidu.com", RequestMethod.GET);
		request.setCallback(new StringCallback() {
			@Override
			public void onSuccess(Object result) {
				tv.setText((String) result);
				// Log.i("Tag", (String)result);
			}

			@Override
			public void onFilure(Exception result) {
				result.printStackTrace();
			}
		}.setPath(path));
		request.execute();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn:

			requestString();
			break;
		}

	}

}
