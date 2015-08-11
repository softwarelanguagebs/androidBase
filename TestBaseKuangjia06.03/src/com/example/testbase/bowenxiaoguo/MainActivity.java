package com.example.testbase.bowenxiaoguo;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity  implements OnClickListener{
	
	private Button btn1,btn2,btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_bowen);
		initView();
		initData();
		setLinstener();
		fillData();
	}



	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	
	protected void initView() {
		btn1 = (Button) this.findViewById(R.id.btn1);
		btn2 = (Button) this.findViewById(R.id.btn2);
		btn3 = (Button) this.findViewById(R.id.btn3);
		
	}

	
	protected void setLinstener() {
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		
	}


	protected void fillData() {
		// TODO Auto-generated method stub
		
	}
	

	
	public void onClick(View v) {
		switch (v.getId()) {
	
		
		 case R.id.btn1:
			  startActivity(new Intent(MainActivity.this, SingleChildActivity.class));
		 break;
		 case R.id.btn2:
			   startActivity(new Intent(MainActivity.this, MultiChildActivity.class));
			 break;
		 case R.id.btn3:
			  startActivity(new Intent(MainActivity.this, FragmentSampleActivity.class));
			 break;
		default:
			break;
		}
		
	}

	
}
