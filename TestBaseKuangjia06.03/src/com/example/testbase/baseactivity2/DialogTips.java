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
 * Description:�Զ���Dialog����������Dialog��ʽ�ļ���Dialog�����ļ���
 * </p>
 * ȷ�� ��ť
 * <p>
 * ȷ�ϰ�ťҪ���� ���ص����������� Copyright: Copyright (c) 2013
 * </p>
 * 
 * @author sw
 * @version 1.0
 */
public class DialogTips extends Dialog implements
		android.view.View.OnClickListener {
	
	Context context;
	String content;// ��ʾ������
	/** ȷ����ť **/
	private Button confirmBtn;
	private TextView txt_tips;

	
	public DialogTips(Context context) {
		super(context);
		this.context = context;
		

		// ���ö��� ///
		/*
		 * context.startAnimation(AnimationUtils.loadAnimation(context,
		 * R.anim.grow_from_bottom));
		 */
	}

	/**
	 * �Զ������⼰���ֵĹ��췽��
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public DialogTips(Context context, int theme,  String content) {
		super(context, theme);
		this.context = context;
		
		this.content = content;// ��ʾ����

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.custom_tipsdialog);

		// ����id�ڲ������ҵ��ؼ�����
		confirmBtn = (Button) findViewById(R.id.confirm_btn);
		// ������ʾ����
		txt_tips = (TextView) findViewById(R.id.txt_tips);
		txt_tips.setText(content);

		// ���ð�ť���ı���ɫ
		// confirmBtn.setTextColor(R.color.white);// 0xff1E90FF

		// Ϊ��ť�󶨵���¼�������
		confirmBtn.setOnClickListener(this);
	}

	// ʵ�����ӿ�
	IConfirmDialog onTouchingLetterChangedListener;

	// ָ���¼�������
	public void setOnTouchingLetterChangedListener(
			IConfirmDialog onTouchingLetterChangedListener) {

		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.confirm_btn:
			// newBBSTabFragment.java��ʹ��

			onTouchingLetterChangedListener.doClick();

			// ȷ�� ��ֱ�ӹر� �Ի���
			dismiss();

			break;
		}
	}
}
