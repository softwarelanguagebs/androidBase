package org.lmw.demo.slidingtab;


import com.example.testbase.kuangjia.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;


public class PagerSlidingTabStripActivity extends FragmentActivity {
	PagerSlidingTabStrip tabs;
	ViewPager pager;
	DisplayMetrics dm;
	
	AFrag afrag;
	BFrag bfrag;
	CFrag cfrag;
//	String[] titles = { "AA", "BB", "CC" };
	
	   String[] titles = { "AA", "BB" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView(){
		setContentView(R.layout.activity_main_pagerslide);
		dm = getResources().getDisplayMetrics();
		pager = (ViewPager)findViewById(R.id.pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager.setAdapter(new MyAdapter(getSupportFragmentManager(),titles));
		tabs.setViewPager(pager);
	}
	

	public class MyAdapter extends FragmentPagerAdapter{
		String[] _titles;
		public MyAdapter(FragmentManager fm,String[] titles) {
			super(fm);
			_titles=titles;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return _titles[position];
		}
		
		@Override
		public int getCount() {
			return _titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (afrag == null) {
					afrag = new AFrag();
				}
				return afrag;
			case 1:
				if (bfrag == null) {
					bfrag = new BFrag();
				}
				return bfrag;
//			case 2:
//				if (cfrag == null) {
//					cfrag = new CFrag();
//				}
//				return cfrag;
			default:
				return null;
			}
		}
	}
}
