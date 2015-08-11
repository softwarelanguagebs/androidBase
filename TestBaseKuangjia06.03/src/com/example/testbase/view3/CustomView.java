/*
 * @Title:  CustomView.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
package com.example.testbase.view3;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
public class CustomView extends View {
	private Paint mPaint;// ����
	private Context mContext;// �����Ļ�������
	private Bitmap bitmap;// λͼ

	private int x, y;// λͼ����ʱ���Ͻǵ��������
	private boolean isClick;// ������ʶ�ؼ��Ƿ񱻵����

	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

		// ��ʼ������
		initPaint();

		// ��ʼ����Դ
		initRes(context);

		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * �жϿؼ��Ƿ񱻵����
				 */
				if (isClick) {
					// ����Ѿ������������ʱ������ɫ����Ϊ�ջ�ԭ��ɫ
					mPaint.setColorFilter(null);
					isClick = false;
				} else {
					// ���δ���������ʱ������ɫ���˺�Ϊ��ɫ
					mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0X00FFFF00));
					isClick = true;
				}

				// �ǵ��ػ�
				invalidate();
			}
		});
	}

	/**
	 * ��ʼ������
	 */
	private void initPaint() {
		// ʵ��������
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initRes(Context context) {
		// ��ȡλͼ
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);

		/*
		 * ����λͼ����ʱ���Ͻǵ�����ʹ��λ����Ļ����
		 * ��Ļ����x������ƫ��λͼһ��Ŀ��
		 * ��Ļ����y������ƫ��λͼһ��ĸ߶�
		 */
		x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
		y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// ����λͼ
		canvas.drawBitmap(bitmap, x, y, mPaint);
	}
}