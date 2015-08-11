package com.example.testbase.hongyang.weixinCaijian;



import java.io.ByteArrayOutputStream;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * http://blog.csdn.net/lmj623565791/article/details/39761281
 * @author zhy
 *
 */


public class MainActivity extends Activity implements OnClickListener
{
	private ClipImageLayout mClipImageLayout;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_weixincaijian);

		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
		btn = (Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(this);

	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			
Bitmap bitmap = mClipImageLayout.clip();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] datas = baos.toByteArray();
			
			Intent intent = new Intent(this, ShowImageActivity.class);
			intent.putExtra("bitmap", datas);
			startActivity(intent);

			break;

		default:
			break;
		}
		
	}
}
