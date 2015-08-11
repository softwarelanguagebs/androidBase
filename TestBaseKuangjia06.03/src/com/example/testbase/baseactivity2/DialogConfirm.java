package com.example.testbase.baseactivity2;



import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * <p>
 * Title: CustomDialog
 * </p>
 * <p>
 * Description:自定义Dialog（参数传入Dialog样式文件，Dialog布局文件） 取消和确认按钮； 确认按钮要触发 ，回调函数。。。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * 
 * @author sw
 * @version 1.0
 */
public class DialogConfirm extends Dialog implements
		android.view.View.OnClickListener {
	
	Context context;
	String tips;// 提示的内容。。。

	/** 确定按钮 **/
	public Button confirmBtn;
	/** 取消按钮 **/
	private Button cancelBtn;
	// 内容
	TextView txt_tips;

	public DialogConfirm(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * 自定义布局的构造方法
	 * 
	 * @param context
	 * @param resLayout
	 */
	public DialogConfirm(Context context, int resLayout) {
		super(context);
		this.context = context;
		

	}

	/**
	 * 自定义主题及布局的构造方法
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public DialogConfirm(Context context, int theme, String tips) {
		super(context, theme);
		this.context = context;
		
		this.tips = tips;

		/*
		 * 对话框。。。 直接实例化 指定 : 自定义的xml 布局 Dialog dlg = new Dialog(this); View
		 * dlgView = View.inflate(this, R.layout.other_plat_dialog, null); View
		 * tvFacebook = dlgView.findViewById(R.id.tvFacebook);
		 * tvFacebook.setTag(dlg); tvFacebook.setOnClickListener(this); View
		 * tvTwitter = dlgView.findViewById(R.id.tvTwitter);
		 * tvTwitter.setTag(dlg); tvTwitter.setOnClickListener(this);
		 * 
		 * dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * dlg.setContentView(dlgView); dlg.show();
		 */

		// 或者AlertDialog ////
		/*
		 * Builder builder = new Builder(this);
		 * builder.setTitle(R.string.if_register_needed);
		 * builder.setMessage(R.string.after_auth);
		 * builder.setPositiveButton(R.string.ok, null);
		 * builder.create().show();
		 */

		/*
		 * 获取当前Dialog的句柄 对象；；； public void onClick(View v) { switch(v.getId()) {
		 * case R.id.tvTwitter: { Dialog dlg = (Dialog) v.getTag();
		 * dlg.dismiss(); } }
		 */
	}

	// 实例化接口
	IConfirmDialog onTouchingLetterChangedListener;

	// 指定事件。。。
	public void setOnTouchingLetterChangedListener(
			IConfirmDialog onTouchingLetterChangedListener) {

		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.custom_confirmdialog);

		// 根据id在布局中找到控件对象
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancelBtn = (Button) findViewById(R.id.cancel_btn);

		// 设置按钮的文本颜色
	//	confirmBtn.setTextColor(R.color.systemcolor);
	//	cancelBtn.setTextColor(R.color.systemcolor);

		// 显示提示文本
		txt_tips = (TextView) findViewById(R.id.txt_tips);
		txt_tips.setText(tips);

		// 为按钮绑定点击事件监听器
		confirmBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		
//	     Window dialogWindow = getWindow();
//	     WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//	       DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//	     lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
//	    dialogWindow.setAttributes(lp);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.confirm_btn:// 确定操作；点击触发回调函数
			// newBBSTabFragment.java有使用

			onTouchingLetterChangedListener.doClick();
			// 跳转动画
			/*
			 * ((Activity) context).overridePendingTransition(
			 * android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			 */

			this.dismiss();

			break;
		case R.id.cancel_btn:// 以后再说
			this.dismiss();
			break;
		}
	}
}