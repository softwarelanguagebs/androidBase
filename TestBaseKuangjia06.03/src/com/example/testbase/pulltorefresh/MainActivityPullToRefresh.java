package com.example.testbase.pulltorefresh;

import com.example.testbase.kuangjia.R;
import com.example.testbase.slidemenu4.MainActivity_slideMenu4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivityPullToRefresh extends Activity  {

	Button btn1;

	Button btn2;

	Button btn3;

	Button btn4;

	Button btn5;

	Button btn6;

	Button btn7;

	Button btn8;

	Button btn9;

	Button btn10;

	Button btn11;
	Button btn12;
	Button btn13;
	Button btn14;
	Button btn15;
	//
	Button btn16;
	Button btn17;
	Button btn18;
	Button btn19;

	Button btn20;
	//
	Button btn21;
	Button btn22;
	Button btn23;
	Button btn24;
	Button btn25;

	Button btn26;
	Button btn27;
	Button btn28;
	Button btn29;

	Button btn30;
	Button btn31;
	Button btn32;
	Button btn33;
	Button btn34;
	Button btn35;
	Button btn36;
	Button btn37;
	Button btn38;
	Button btn39;
	
	Button btn40;
	Button btn41;
	Button btn42;
	Button btn43;
	Button btn44;
	Button btn45;
	Button btn46;
	Button btn47;
	Button btn48;
	Button btn49;
	
	Button btn50;
	Button btn51;
	Button btn52;
   Button btn53;
	Button btn54;
//	Button btn45;
//	Button btn46;
//	Button btn47;
//	Button btn48;
//	Button btn49;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_pull_to_refresh);
		

		initButton(btn1, R.id.btn1, PullToRefreshListActivity.class);
		initButton(btn2, R.id.btn2, PullToRefreshListActivityBoth.class);
		initButton(btn3, R.id.btn3, PullToRefreshListActivityBottom.class);
//		initButton(btn2, R.id.btn2, com.example.testbase.hongyang.CustomView2.MainActivity.class);
//		initButton(btn3, R.id.btn3, com.example.testbase.hongyang.CustomView3.MainActivity.class);
//		initButton(btn4, R.id.btn4, com.example.testbase.hongyang.CustomView4.MainActivity.class);
//		
//		initButton(btn5, R.id.btn5, com.example.testbase.hongyang.activitydialog.MainActivity.class);
//		
//		initButton(btn6, R.id.btn6, com.example.testbase.hongyang.SwipeRefreshLayout.MainActivity.class);
		
		
	}

	
	

	public void initButton(Button btn, int id, final Class c) {

		btn = (Button) this.findViewById(id);
	
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(MainActivityPullToRefresh.this, c);
				startActivity(intent);

			}
		});
	}




}
