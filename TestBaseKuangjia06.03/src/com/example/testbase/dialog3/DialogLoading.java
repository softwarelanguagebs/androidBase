package com.example.testbase.dialog3;


import com.example.testbase.kuangjia.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

/**
 * �Զ�����ʾ���� ���������С�����
 * 
 * @author sw
 */
public class DialogLoading extends AlertDialog {

	private TextView tips_loading_msg;

	private String message = null;

	public DialogLoading(Context context) {
		super(context);
	//	message = getContext().getResources().getString(R.string.msg_load_ing);
		message = "������....";
	}

	public DialogLoading(Context context, String message) {
		super(context);
		this.message = message;
	//	this.setCancelable(true); //����Ϊfalse�������ؼ������˳���Ĭ��Ϊtrue��
	}

	public DialogLoading(Context context, int theme, String message) {
		super(context, theme);
		this.message = message;
		this.setCancelable(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.customer_tips_loading);
		tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
		tips_loading_msg.setText(this.message);
	}

	public void setText(String message) {
		this.message = message;
		tips_loading_msg.setText(this.message);
	}

	public void setText(int resId) {
		setText(getContext().getResources().getString(resId));
	}

}
