/*
 * �ļ�����MainActivity.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��20��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.guolin.fragment1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

import com.example.testbase.kuangjia.R;



public class MainActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_guo_f);
//		Display display = getWindowManager().getDefaultDisplay();
//		if (display.getWidth() > display.getHeight()) {
//			Fragment1 fragment1 = new Fragment1();
//			getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();
//			
//		} else {
//			Fragment2 fragment2 = new Fragment2();
//		//	getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit(); //�̳�Activity
//			getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();
//		}
	}

}