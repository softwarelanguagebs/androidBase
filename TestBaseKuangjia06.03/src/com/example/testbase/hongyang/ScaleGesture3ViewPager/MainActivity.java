package com.example.testbase.hongyang.ScaleGesture3ViewPager;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.testbase.kuangjia.R;


public class MainActivity extends Activity
{
	private ViewPager mViewPager;
	private int[] mImgs = new int[] { R.drawable.xiaodao1, R.drawable.xiaodao2,
			R.drawable.xiaodao4 };
	private ImageView[] mImageViews = new ImageView[mImgs.length];

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vp);
		
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mViewPager.setAdapter(new PagerAdapter()
		{

			@Override
			public Object instantiateItem(ViewGroup container, int position)
			{
				ZoomImageView imageView = new ZoomImageView(
						getApplicationContext());
				imageView.setImageResource(mImgs[position]);
				container.addView(imageView);
				mImageViews[position] = imageView;
				return imageView;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object)
			{
				container.removeView(mImageViews[position]);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1)
			{
				return arg0 == arg1;
			}

			@Override
			public int getCount()
			{
				return mImgs.length;
			}
		});

	}
}
