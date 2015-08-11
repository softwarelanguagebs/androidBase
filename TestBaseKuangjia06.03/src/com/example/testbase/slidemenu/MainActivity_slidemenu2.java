package com.example.testbase.slidemenu;

import com.example.testbase.kuangjia.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity_slidemenu2 extends SlidingActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_slidemenu1);

		setBehindContentView(R.layout.leftmenu);
		// configure the SlidingMenu
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		// ���ô�����Ļ��ģʽ
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		// ���û����˵���ͼ�Ŀ��
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// ���ý��뽥��Ч����ֵ
		menu.setFadeDegree(0.35f);
		/**
		 * SLIDING_WINDOW will include the Title/ActionBar in the content
		 * section of the SlidingMenu, while SLIDING_CONTENT does not.
		 */
		// menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// menu.setMenu(R.layout.leftmenu);

	}

}
