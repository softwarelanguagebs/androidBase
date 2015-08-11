package com.example.testbase.dialog3;



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
 * Description:�Զ���Dialog����������Dialog��ʽ�ļ���Dialog�����ļ��� ȡ����ȷ�ϰ�ť�� ȷ�ϰ�ťҪ���� ���ص�����������
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
	int layoutRes;// �����ļ�
	Context context;
	String tips;// ��ʾ�����ݡ�����

	/** ȷ����ť **/
	public Button confirmBtn;
	/** ȡ����ť **/
	private Button cancelBtn;
	// ����
	TextView txt_tips;

	public DialogConfirm(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * �Զ��岼�ֵĹ��췽��
	 * 
	 * @param context
	 * @param resLayout
	 */
	public DialogConfirm(Context context, int resLayout) {
		super(context);
		this.context = context;
		this.layoutRes = resLayout;

	}

	/**
	 * �Զ������⼰���ֵĹ��췽��
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public DialogConfirm(Context context, int theme, int resLayout, String tips) {
		super(context, theme);
		this.context = context;
		this.layoutRes = resLayout;
		this.tips = tips;

		/*
		 * �Ի��򡣡��� ֱ��ʵ���� ָ�� : �Զ����xml ���� Dialog dlg = new Dialog(this); View
		 * dlgView = View.inflate(this, R.layout.other_plat_dialog, null); View
		 * tvFacebook = dlgView.findViewById(R.id.tvFacebook);
		 * tvFacebook.setTag(dlg); tvFacebook.setOnClickListener(this); View
		 * tvTwitter = dlgView.findViewById(R.id.tvTwitter);
		 * tvTwitter.setTag(dlg); tvTwitter.setOnClickListener(this);
		 * 
		 * dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * dlg.setContentView(dlgView); dlg.show();
		 */

		// ����AlertDialog ////
		/*
		 * Builder builder = new Builder(this);
		 * builder.setTitle(R.string.if_register_needed);
		 * builder.setMessage(R.string.after_auth);
		 * builder.setPositiveButton(R.string.ok, null);
		 * builder.create().show();
		 */

		/*
		 * ��ȡ��ǰDialog�ľ�� ���󣻣��� public void onClick(View v) { switch(v.getId()) {
		 * case R.id.tvTwitter: { Dialog dlg = (Dialog) v.getTag();
		 * dlg.dismiss(); } }
		 */
	}

	// ʵ�����ӿ�
	IConfirmDialog onTouchingLetterChangedListener;

	// ָ���¼�������
	public void setOnTouchingLetterChangedListener(
			IConfirmDialog onTouchingLetterChangedListener) {

		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(layoutRes);

		// ����id�ڲ������ҵ��ؼ�����
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		cancelBtn = (Button) findViewById(R.id.cancel_btn);

		// ���ð�ť���ı���ɫ
	//	confirmBtn.setTextColor(R.color.systemcolor);
	//	cancelBtn.setTextColor(R.color.systemcolor);

		// ��ʾ��ʾ�ı�
		txt_tips = (TextView) findViewById(R.id.txt_tips);
		txt_tips.setText(tips);

		// Ϊ��ť�󶨵���¼�������
		confirmBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		
		 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.confirm_btn:// ȷ����������������ص�����
			// newBBSTabFragment.java��ʹ��

			onTouchingLetterChangedListener.doClick();
			// ��ת����
			/*
			 * ((Activity) context).overridePendingTransition(
			 * android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			 */

			this.dismiss();

			break;
		case R.id.cancel_btn:// �Ժ���˵
			this.dismiss();
			break;
		}
	}
}