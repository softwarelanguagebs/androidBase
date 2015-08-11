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
 * //设置侧滑菜单的位置，可选值LEFT , RIGHT , LEFT_RIGHT （两边都有菜单时设置）
menu.setMode(SlidingMenu.LEFT_RIGHT);
// 设置触摸屏幕的模式，可选只MARGIN , CONTENT 
menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//根据dimension资源文件的ID来设置阴影的宽度
menu.setShadowWidthRes(R.dimen.shadow_width);
//根据资源文件ID来设置滑动菜单的阴影效果
menu.setShadowDrawable(R.drawable.shadow);
// 这两个都是设置滑动菜单视图的宽度，二选一
//设置SlidingMenu离屏幕的偏移量
menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//设置宽度
menu.setBehindWidth()
// 设置渐入渐出效果的值
menu.setFadeDegree(0.35f);
//设置SlidingMenu与下方视图的移动的速度比，当为1时同时移动，取值0-1
menu.setBehindScrollScale(1.0f);
//设置二级菜单的阴影效果
menu.setSecondaryShadowDrawable(R.drawable.shadow);
//设置右边（二级）侧滑菜单
menu.setSecondaryMenu(R.layout.right_menu_frame);
//为侧滑菜单设置布局
menu.setMenu(R.layout.leftmenu);
//把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
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
		// 初始化SlideMenu
		initRightMenu();
		// 初始化ViewPager
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
		 * 初始化Adapter
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
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		//设置右边（二级）侧滑菜单
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
