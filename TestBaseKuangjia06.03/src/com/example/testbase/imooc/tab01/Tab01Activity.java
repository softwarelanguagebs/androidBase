package com.example.testbase.imooc.tab01;

import java.util.ArrayList;
import java.util.List;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Tab01Activity extends Activity implements OnClickListener
{

	private ViewPager mViewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews = new ArrayList<View>();
	// TAB

	private LinearLayout mTabWeixin;
	private LinearLayout mTabFrd;
	private LinearLayout mTabAddress;
	private LinearLayout mTabSetting;

	private ImageButton mWeixinImg;
	private ImageButton mFrdImg;
	private ImageButton mAddressImg;
	private ImageButton mSettingImg;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab01);

		initView();

		initEvents();

	}

	private void initEvents()
	{
		mTabWeixin.setOnClickListener(this);
		mTabFrd.setOnClickListener(this);
		mTabAddress.setOnClickListener(this);
		mTabSetting.setOnClickListener(this);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int arg0)
			{
				int currentItem = mViewPager.getCurrentItem();
				resetImg();
				switch (currentItem)
				{
				case 0:
					mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
					break;
				case 1:
					mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
					break;
				case 2:
					mAddressImg
							.setImageResource(R.drawable.tab_address_pressed);
					break;
				case 3:
					mSettingImg
							.setImageResource(R.drawable.tab_settings_pressed);
					break;

				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{

			}
		});
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		// tabs
		mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
		mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
		mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
		mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
		// ImageButton
		mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
		mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
		mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
		mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);

		LayoutInflater mInflater = LayoutInflater.from(this);
		View tab01 = mInflater.inflate(R.layout.tab001, null);
		View tab02 = mInflater.inflate(R.layout.tab002, null);
		View tab03 = mInflater.inflate(R.layout.tab003, null);
		View tab04 = mInflater.inflate(R.layout.tab004, null);
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);

		mAdapter = new PagerAdapter()
		{

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object)
			{
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position)
			{
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1)
			{
				return arg0 == arg1;
			}

			@Override
			public int getCount()
			{
				return mViews.size();
			}
		};

		mViewPager.setAdapter(mAdapter);

	}

	@Override
	public void onClick(View v)
	{
		resetImg();
		switch (v.getId())
		{
		case R.id.id_tab_weixin:
			mViewPager.setCurrentItem(0);
			mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
			break;
		case R.id.id_tab_frd:
			mViewPager.setCurrentItem(1);
			mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
			break;
		case R.id.id_tab_address:
			mViewPager.setCurrentItem(2);
			mAddressImg.setImageResource(R.drawable.tab_address_pressed);
			break;
		case R.id.id_tab_settings:
			mViewPager.setCurrentItem(3);
			mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
			break;

		default:
			break;
		}
	}

	/**
	 * 将所有的图片切换为暗色的
	 */
	private void resetImg()
	{
		mWeixinImg.setImageResource(R.drawable.tab_weixin_normal);
		mFrdImg.setImageResource(R.drawable.tab_find_frd_normal);
		mAddressImg.setImageResource(R.drawable.tab_address_normal);
		mSettingImg.setImageResource(R.drawable.tab_settings_normal);
	}

}
