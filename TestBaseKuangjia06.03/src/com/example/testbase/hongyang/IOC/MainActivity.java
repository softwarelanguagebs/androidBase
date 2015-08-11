/*
 * �ļ�����MainActivity.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��18��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.hongyang.IOC;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@ContentView(value = R.layout.activity_main_hongyang_ioc)
public class MainActivity extends Activity implements OnClickListener
{
	@ViewInject(R.id.id_btn)
	private Button mBtn1;
	@ViewInject(R.id.id_btn02)
	private Button mBtn2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ViewInjectUtils.inject(this);

		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.id_btn:
			Toast.makeText(MainActivity.this, "Why do you click me ?",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.id_btn02:
			Toast.makeText(MainActivity.this, "I am sleeping !!!",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

}

