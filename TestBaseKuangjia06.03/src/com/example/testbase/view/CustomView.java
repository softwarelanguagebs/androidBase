/*
 * @Title:  CustomView.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
package com.example.testbase.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO<>
 * @author  wen_er
 * @data:  2015��5��22�� ����3:23:10
 * @version:  V1.0
 */
public class CustomView  extends View implements Runnable { 

	private Paint mPaint;  
	private Context mContext;// �����Ļ�������
	
	 private int radiu;// Բ���뾶  
	
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
	    canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radiu, mPaint);  
		   
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
        mPaint.setStyle(Paint.Style.STROKE);  
      
        // ���û�����ɫΪǳ��ɫ  
        mPaint.setColor(Color.LTGRAY);  
      
        /* 
         * ������ߵĴ�ϸ����λ������px 
         * ע�⣺��setStrokeWidth(0)��ʱ����߿�Ȳ���Ϊ0����ֻռһ������ 
         */  
        mPaint.setStrokeWidth(10);  
    }  
    
    public void  myDraw(){
    	
    	new Thread(){
    		public void run() {
    			
    			/* 
    	         * ȷ���̲߳���ִ�в���ˢ�½��� 
    	         */  
    	        while (true) {  
    	            try {  
    	                /* 
    	                 * ����뾶С��200���Լӷ������200�����ð뾶ֵ��ʵ������ 
    	                 */  
    	                if (radiu <= 200) {  
    	                    radiu += 10;  
    	  
    	                    // ˢ��View  
    	               //     invalidate();  // Only the original thread that created a view hierarchy can touch its views.
    	                    
    	                    // ˢ��View  
    	                    postInvalidate();  
    	                } else {  
    	                    radiu = 0;  
    	                }  
    	  
    	                // ÿִ��һ����ͣ40����  
    	                Thread.sleep(40);  
    	            } catch (InterruptedException e) {  
    	                e.printStackTrace();  
    	            }  
    	        }  	
    		};
    	}.start();
    	
    }

	/**
	 * ���ط���
	 */
	@Override
	public void run() {

		/*
		 * ȷ���̲߳���ִ�в���ˢ�½���
		 */
		while (true) {
			try {
				/*
				 * ����뾶С��200���Լӷ������200�����ð뾶ֵ��ʵ������
				 */
				if (radiu <= 200) {
					radiu += 10;

					// ˢ��View
					postInvalidate();
				} else {
					radiu = 0;
				}

				// ÿִ��һ����ͣ40����
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		
	}
}
