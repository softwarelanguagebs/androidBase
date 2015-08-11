package com.example.testbase.slidemenu;

import java.util.ArrayList;
import java.util.List;

import com.example.testbase.kuangjia.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

/* http://blog.csdn.net/lmj623565791/article/details/36677279
 * //���ò໬�˵���λ�ã���ѡֵLEFT , RIGHT , LEFT_RIGHT �����߶��в˵�ʱ���ã�
menu.setMode(SlidingMenu.LEFT_RIGHT);
// ���ô�����Ļ��ģʽ����ѡֻMARGIN , CONTENT 
menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//����dimension��Դ�ļ���ID��������Ӱ�Ŀ��
menu.setShadowWidthRes(R.dimen.shadow_width);
//������Դ�ļ�ID�����û����˵�����ӰЧ��
menu.setShadowDrawable(R.drawable.shadow);
// �������������û����˵���ͼ�Ŀ�ȣ���ѡһ
//����SlidingMenu����Ļ��ƫ����
menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//���ÿ��
menu.setBehindWidth()
// ���ý��뽥��Ч����ֵ
menu.setFadeDegree(0.35f);
//����SlidingMenu���·���ͼ���ƶ����ٶȱȣ���Ϊ1ʱͬʱ�ƶ���ȡֵ0-1
menu.setBehindScrollScale(1.0f);
//���ö����˵�����ӰЧ��
menu.setSecondaryShadowDrawable(R.drawable.shadow);
//�����ұߣ��������໬�˵�
menu.setSecondaryMenu(R.layout.right_menu_frame);
//Ϊ�໬�˵����ò���
menu.setMenu(R.layout.leftmenu);
//�ѻ����˵���ӽ����е�Activity�У���ѡֵSLIDING_CONTENT �� SLIDING_WINDOW
menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
 
 *
 */

public class MainActivity_slidemenu3 extends SlidingFragmentActivity
{

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_slidemenu3);
		// ��ʼ��SlideMenu
		initRightMenu();
		// ��ʼ��ViewPager
		initViewPager();

	}

	private void initViewPager()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		/**
		 * ��ʼ��Adapter
		 */
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
	}

	private void initRightMenu()
	{
		
		Fragment leftMenuFragment = new MenuLeftFragment();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		// ���ô�����Ļ��ģʽ
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// ���û����˵���ͼ�Ŀ��
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
		// ���ý��뽥��Ч����ֵ
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		//�����ұߣ��������໬�˵�
		menu.setSecondaryMenu(R.layout.right_menu_frame);
		Fragment rightMenuFragment = new MenuRightFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
	}

	public void showLeftMenu(View view)
	{
		getSlidingMenu().showMenu();
	}

	public void showRightMenu(View view)
	{
		getSlidingMenu().showSecondaryMenu();
	}
	
	
}
