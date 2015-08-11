package com.example.testbase.sw3backTop;


import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * loading.xmlҳ�棬���ؽ�����ʾ�� ������LinearLayout ���֣����� ��ʾ ������Ϣ�� ���¼��ذ�ť
 * 
 * @author sw
 *
 */
public class LoadStateView extends RelativeLayout {

	ProgressBar progBar;

	LinearLayout downLoadErrMsgBox;

	TextView downLoadErrText;

	TextView tipstext;

	TextView btnListLoadErr;

	public LoadStateView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		progBar = (ProgressBar) findViewById(R.id.progBar);
		downLoadErrMsgBox = (LinearLayout) findViewById(R.id.downLoadErrMsgBox);

		downLoadErrText = (TextView) findViewById(R.id.downLoadErrText);

		btnListLoadErr = (TextView) findViewById(R.id.btnListLoadErr);
		btnListLoadErr.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// �»���
		btnListLoadErr.getPaint().setAntiAlias(true);// �����
		tipstext = (TextView) findViewById(R.id.tiptext);
	}

	public void startLoad() {
		downLoadErrMsgBox.setVisibility(View.GONE);
		progBar.setVisibility(View.VISIBLE);

		// ��ʾ������
		tipstext.setVisibility(View.VISIBLE);
		tipstext.setText("");// �����С���

	}

	public void stopLoad() {
		progBar.setVisibility(View.GONE);
		tipstext.setVisibility(View.GONE);
		tipstext.setText("");
	}

	// �����ʱ�� �������������LinearLayout ���ֲ���ʾ
	public void showError() {
		downLoadErrMsgBox.setVisibility(View.VISIBLE);
		progBar.setVisibility(View.GONE);
	}

	// û�����ݷ���
	public void showEmpty() {
		downLoadErrMsgBox.setVisibility(View.VISIBLE);
		progBar.setVisibility(View.GONE);
		tipstext.setVisibility(View.VISIBLE);

	}

	// �����ʱ����ʾ��ť���¼���
	public void setOnReloadClickListener(OnClickListener onReloadClickListener) {
		btnListLoadErr.setOnClickListener(onReloadClickListener);
	}
}
