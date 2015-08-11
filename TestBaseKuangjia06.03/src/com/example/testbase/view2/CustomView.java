/*
 * @Title:  CustomView.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
package com.example.testbase.view2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
public class CustomView  extends View { 

	private Paint mPaint;  
	private Context mContext;// �����Ļ�������
	
	
	
	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}  

	public CustomView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    	mContext = context;
        initPaint();
    }  
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		 // ����Բ��  
	    canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, 200, mPaint);  
		   
	}
	
	 /** 
     * ��ʼ������ 
     */  
    private void initPaint() {  
        // ʵ�������ʲ��򿪿����  
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        /* 
         * ���û�����ʽΪ��ߣ�Բ�������Ȼ������䲻Ȼ��ô��˼�� 
         *  
         * ������ʽ�����֣� 
         * 1.Paint.Style.STROKE����� 
         * 2.Paint.Style.FILL_AND_STROKE����߲���� 
         * 3.Paint.Style.FILL����� 
         */  
        mPaint.setStyle(Paint.Style.FILL);  
      
        // ���û�����ɫΪ�Զ�����ɫ  
        mPaint.setColor(Color.argb(255, 255, 128, 103));  
      
        /* 
         * ������ߵĴ�ϸ����λ������px 
         * ע�⣺��setStrokeWidth(0)��ʱ����߿�Ȳ���Ϊ0����ֻռһ������ 
         */  
        
     // ����ɫ�ʾ���  
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{  
                0.5F, 0, 0, 0, 0,  
                0, 0.5F, 0, 0, 0,  
                0, 0, 0.5F, 0, 0,  
                0, 0, 0, 1, 0,  
        });  
        
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix)); 
        mPaint.setStrokeWidth(10);  
    }  
    
    
}
