package com.example.testbase.view2;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;



/**
 * ������
 * 
 * @author Aige
 * @since 2014/11/17
 */
public class MainActivity extends Activity {
	private CustomView mCustomView;// ���ǵ��Զ���View
	private LinearLayout llRoot;// ����ĸ�����  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view22);
		
		   //OK
		    llRoot = (LinearLayout) findViewById(R.id.main_root_ll);  
	        llRoot.addView(new CustomView(this));  
	        //OK

	
	}
}
