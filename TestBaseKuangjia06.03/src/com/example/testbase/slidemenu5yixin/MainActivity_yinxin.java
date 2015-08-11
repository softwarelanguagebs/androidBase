package com.example.testbase.slidemenu5yixin;

import com.example.testbase.kuangjia.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity_yinxin extends SlidingFragmentActivity implements
		OnClickListener {
	protected SlidingMenu leftRightSlidingMenu;
	private ImageButton ivTitleBtnLeft;
	private ImageButton ivTitleBtnRight;
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLeftRightSlidingMenu();
		setContentView(R.layout.activity_main_yixin);
		initView();
	}

	private void initView() {
		ivTitleBtnLeft = (ImageButton) this.findViewById(R.id.ivTitleBtnLeft);
		ivTitleBtnLeft.setOnClickListener(this);

		ivTitleBtnRight = (ImageButton) this.findViewById(R.id.ivTitleBtnRight);
		ivTitleBtnRight.setOnClickListener(this);
	}

	private void initLeftRightSlidingMenu() {
		mContent = new FragmentDefaultMain();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();
		setBehindContentView(R.layout.main_left_layout);
		FragmentTransaction leftFragementTransaction = getSupportFragmentManager()
				.beginTransaction();
		Fragment leftFrag = new LeftSlidingMenuFragment();
		leftFragementTransaction.replace(R.id.main_left_fragment, leftFrag);
		leftFragementTransaction.commit();
		// customize the SlidingMenu
		leftRightSlidingMenu = getSlidingMenu();
		leftRightSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// �������󻬻����һ����������Ҷ����Ի���������ֻ������

		// leftRightSlidingMenu.setMode(SlidingMenu.LEFT);//
		// �������󻬻����һ����������Ҷ����Ի���������ֻ������

		leftRightSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// ���ò˵����
		leftRightSlidingMenu.setFadeDegree(0.35f);// ���õ��뵭���ı���
		leftRightSlidingMenu
				.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ��������ģʽ
		leftRightSlidingMenu.setShadowDrawable(R.drawable.shadow);// ������˵���ӰͼƬ
		leftRightSlidingMenu.setFadeEnabled(true);// ���û���ʱ�˵����Ƿ��뵭��
		leftRightSlidingMenu.setBehindScrollScale(0.333f);// ���û���ʱ��קЧ��

		leftRightSlidingMenu.setSecondaryMenu(R.layout.main_right_layout);
		FragmentTransaction rightFragementTransaction = getSupportFragmentManager()
				.beginTransaction();
		Fragment rightFrag = new RightSlidingMenuFragment();
		leftFragementTransaction.replace(R.id.main_right_fragment, rightFrag);
		rightFragementTransaction.commit();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivTitleBtnLeft:
			leftRightSlidingMenu.showMenu();
			break;

		case R.id.ivTitleBtnRight:
			leftRightSlidingMenu.showSecondaryMenu(true);
			break;

		default:
			break;
		}

	}

	/**
	 * ���˵�����л���ҳ������
	 */

	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}

}
