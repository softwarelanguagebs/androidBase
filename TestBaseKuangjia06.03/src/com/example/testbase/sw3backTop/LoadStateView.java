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
 * loading.xml页面，加载进度提示。 下面有LinearLayout 部分，里面 提示 错误信息和 重新加载按钮
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
		btnListLoadErr.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		btnListLoadErr.getPaint().setAntiAlias(true);// 抗锯齿
		tipstext = (TextView) findViewById(R.id.tiptext);
	}

	public void startLoad() {
		downLoadErrMsgBox.setVisibility(View.GONE);
		progBar.setVisibility(View.VISIBLE);

		// 提示加载中
		tipstext.setVisibility(View.VISIBLE);
		tipstext.setText("");// 加载中……

	}

	public void stopLoad() {
		progBar.setVisibility(View.GONE);
		tipstext.setVisibility(View.GONE);
		tipstext.setText("");
	}

	// 出错的时候 ，进度条下面的LinearLayout 部分才显示
	public void showError() {
		downLoadErrMsgBox.setVisibility(View.VISIBLE);
		progBar.setVisibility(View.GONE);
	}

	// 没有数据返回
	public void showEmpty() {
		downLoadErrMsgBox.setVisibility(View.VISIBLE);
		progBar.setVisibility(View.GONE);
		tipstext.setVisibility(View.VISIBLE);

	}

	// 出错的时候，显示按钮重新加载
	public void setOnReloadClickListener(OnClickListener onReloadClickListener) {
		btnListLoadErr.setOnClickListener(onReloadClickListener);
	}
}
