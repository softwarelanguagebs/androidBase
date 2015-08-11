package com.example.testbase.dialog2;

import com.example.testbase.kuangjia.R;
import com.example.testbase.util.ScreemUtils;
import com.example.testbase.util.ScreenUtils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DialogFactory {

	public static Dialog creatRequestDialog(final Context context, String tip) {

		final Dialog dialog = new Dialog(context, R.style.dialog);
		dialog.setContentView(R.layout.dialog_layout);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		// int width = ScreemUtils.getScreenWidth(context); //原来的
		int width = ScreenUtils.getScreenWidth(context);
		lp.width = (int) (0.6 * width);

		TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
		if (tip == null || tip.length() == 0) {
			titleTxtv.setText("正在发送请求");
		} else {
			titleTxtv.setText(tip);
		}

		return dialog;
	}

}
