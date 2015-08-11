package com.example.testbase.baseactivity2;

import com.example.testbase.kuangjia.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * <p>
 * Title: TipDialog
 * </p>
 * <p>
 * Description:自定义Dialog（参数传入Dialog样式文件，Dialog布局文件）
 * </p>
 * 确认 按钮
 * <p>
 * 确认按钮要触发 ，回调函数。。。 Copyright: Copyright (c) 2013
 * </p>
 * 
 * @author sw
 * @version 1.0
 */
public class DialogTips extends Dialog implements
		android.view.View.OnClickListener {
	
	Context context;
	String content;// 显示的内容
	/** 确定按钮 **/
	private Button confirmBtn;
	private TextView txt_tips;

	
	public DialogTips(Context context) {
		super(context);
		this.context = context;
		

		// 设置动画 ///
		/*
		 * context.startAnimation(AnimationUtils.loadAnimation(context,
		 * R.anim.grow_from_bottom));
		 */
	}

	/**
	 * 自定义主题及布局的构造方法
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public DialogTips(Context context, int theme,  String content) {
		super(context, theme);
		this.context = context;
		
		this.content = content;// 提示内容

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.custom_tipsdialog);

		// 根据id在布局中找到控件对象
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		// 设置提示内容
		txt_tips = (TextView) findViewById(R.id.txt_tips);
		txt_tips.setText(content);

		// 设置按钮的文本颜色
		// confirmBtn.setTextColor(R.color.white);// 0xff1E90FF

		// 为按钮绑定点击事件监听器
		confirmBtn.setOnClickListener(this);
	}

	// 实例化接口
	IConfirmDialog onTouchingLetterChangedListener;

	// 指定事件。。。
	public void setOnTouchingLetterChangedListener(
			IConfirmDialog onTouchingLetterChangedListener) {

		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.confirm_btn:
			// newBBSTabFragment.java有使用

			onTouchingLetterChangedListener.doClick();

			// 确认 ，直接关闭 对话框
			dismiss();

			break;
		}
	}
}
