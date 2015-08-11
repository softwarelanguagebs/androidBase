package com.example.testbase.dialog1;

import com.example.testbase.kuangjia.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDlg extends Dialog {

	private View mDlgView;
	private ImageView mImgLoading;
	private TextView mTxtMsg;

	public MyDlg(Context context) {
		super(context, R.style.iphone_progress_dialog);

		mDlgView = LayoutInflater.from(context).inflate(
				R.layout.iphone_progress_dialog, null);
		mImgLoading = (ImageView) mDlgView.findViewById(R.id.img_iphone_load);
		mTxtMsg = (TextView) mDlgView.findViewById(R.id.txt_iphone_load_msg);

		Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.iphone_progress_dialog);
		mImgLoading.setAnimation(animation);
		setContentView(mDlgView);

	}

	public void setMessage(CharSequence msg) {
		this.mTxtMsg.setText(msg);
	}

	/**
	 * 设置消息
	 * 
	 * @param msgId
	 */
	public void setMessage(int msgId) {
		this.mTxtMsg.setText(msgId);
	}
}
