/*
 * �ļ�����MainActivity.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��19��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
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
 * ��Ϊ����Ļ������ת��Activity��������������Ĭ�ϵ�Activity�е�Fragment
 * Ҳ�����Activity���´�����������ɵ���ת��ʱ��
 * ������ڵ�Fragment������������Ȼ��ִ��Activity��onCreateʱ��
 * �ֻ��ٴ�ʵ����һ���µ�Fragment������ǳ��ֵ�ԭ��
 */
		//�򵥸�һ�´��룬ֻ����savedInstanceState==nullʱ���Ž��д���Fragmentʵ����
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
	 * FragmentOne ��ť���ʱ�Ļص�
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
	 * FragmentTwo ��ť���ʱ�Ļص�
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
