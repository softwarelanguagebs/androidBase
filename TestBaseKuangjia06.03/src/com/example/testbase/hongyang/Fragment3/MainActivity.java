/*
 * 文件名：MainActivity.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.example.testbase.hongyang.Fragment3;

import com.example.testbase.hongyang.Fragment3.FragmentOne.FOneBtnClickListener;
import com.example.testbase.hongyang.Fragment3.FragmentTwo.FTwoBtnClickListener;
import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;



public class MainActivity extends Activity implements FOneBtnClickListener,
		FTwoBtnClickListener
{

	private FragmentOne mFOne;
	private FragmentTwo mFTwo;
	private FragmentThree mFThree;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_f3);
/*
 * 因为当屏幕发生旋转，Activity发生重新启动，默认的Activity中的Fragment
 * 也会跟着Activity重新创建；这样造成当旋转的时候，
 * 本身存在的Fragment会重新启动，然后当执行Activity的onCreate时，
 * 又会再次实例化一个新的Fragment，这就是出现的原因。
 */
		//简单改一下代码，只有在savedInstanceState==null时，才进行创建Fragment实例：
		 if(savedInstanceState == null)   //
	        {  
		mFOne = new FragmentOne();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		tx.add(R.id.id_content, mFOne, "ONE");
		tx.commit();
	        }
	}

	/**
	 * FragmentOne 按钮点击时的回调
	 */
	@Override
	public void onFOneBtnClick()
	{

		if (mFTwo == null)
		{
			mFTwo = new FragmentTwo();
			mFTwo.setfTwoBtnClickListener(this);
		}
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		tx.replace(R.id.id_content, mFTwo, "TWO");
		tx.addToBackStack(null);
		tx.commit();
	}

	/**
	 * FragmentTwo 按钮点击时的回调
	 */
	@Override
	public void onFTwoBtnClick()
	{
		if (mFThree == null)
		{
			mFThree = new FragmentThree();

		}
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		tx.hide(mFTwo);
		tx.add(R.id.id_content, mFThree, "THREE");
		// tx.replace(R.id.id_content, fThree, "THREE");
		tx.addToBackStack(null);
		tx.commit();
	}

}
