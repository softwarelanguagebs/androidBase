/*
 * 文件名：MainActivity.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
//		//	getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit(); //继承Activity
//			getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();
//		}
	}

}