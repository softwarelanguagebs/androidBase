package com.example.testbase.hongyang.IOC2;



import com.example.testbase.hongyang.IOC2.annotation.ContentView;
import com.example.testbase.hongyang.IOC2.annotation.OnClick;
import com.example.testbase.hongyang.IOC2.annotation.ViewInject;
import com.example.testbase.kuangjia.R;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;



@ContentView(value = R.layout.activity_main_hongyang_ioc)
public class MainActivity extends BaseActivity
{
	@ViewInject(R.id.id_btn)
	private Button mBtn1;
	@ViewInject(R.id.id_btn02)
	private Button mBtn2;

	@OnClick({ R.id.id_btn, R.id.id_btn02 })
	public void clickBtnInvoked(View view)
	{
		switch (view.getId())
		{
		case R.id.id_btn:
			Toast.makeText(this, "Inject Btn01 !", Toast.LENGTH_SHORT).show();
			break;
		case R.id.id_btn02:
			Toast.makeText(this, "Inject Btn02 !", Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
