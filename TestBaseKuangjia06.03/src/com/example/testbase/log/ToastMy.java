package com.example.testbase.log;

import com.example.testbase.kuangjia.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Tau.Chen sw
 * 
 * @email
 * 
 * @date 2013��9��12��
 * 
 * @version V_1.0.0
 * 
 * @description
 * 
 */
public class ToastMy {

	/**
	 * ������ʾToast��Ϣ
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShortToast(Context context, String message) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.custom_toast, null);

		TextView text = (TextView) view.findViewById(R.id.toast_message);
		text.setText(message);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 300); // �ƶ� Toast ��ʾ��λ��
		// toast.setGravity(Gravity.TOP, 0, 220);
		toast.setView(view);
		toast.show();
	}

}
