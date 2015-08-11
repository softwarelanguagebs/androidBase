/*
 * @Title:  CustomView.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015年5月22日 下午3:23:10
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
 * @data:  2015年5月22日 下午3:23:10
 * @version:  V1.0
 */
public class CustomView  extends View implements Runnable { 

	private Paint mPaint;  
	private Context mContext;// 上下文环境引用
	
	 private int radiu;// 圆环半径  
	
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
		
		 // 绘制圆环  
	    canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radiu, mPaint);  
		   
	}
	
	 /** 
     * 初始化画笔 
     */  
    private void initPaint() {  
        // 实例化画笔并打开抗锯齿  
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        /* 
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了 
         *  
         * 画笔样式分三种： 
         * 1.Paint.Style.STROKE：描边 
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充 
         * 3.Paint.Style.FILL：填充 
         */  
        mPaint.setStyle(Paint.Style.STROKE);  
      
        // 设置画笔颜色为浅灰色  
        mPaint.setColor(Color.LTGRAY);  
      
        /* 
         * 设置描边的粗细，单位：像素px 
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素 
         */  
        mPaint.setStrokeWidth(10);  
    }  
    
    public void  myDraw(){
    	
    	new Thread(){
    		public void run() {
    			
    			/* 
    	         * 确保线程不断执行不断刷新界面 
    	         */  
    	        while (true) {  
    	            try {  
    	                /* 
    	                 * 如果半径小于200则自加否则大于200后重置半径值以实现往复 
    	                 */  
    	                if (radiu <= 200) {  
    	                    radiu += 10;  
    	  
    	                    // 刷新View  
    	               //     invalidate();  // Only the original thread that created a view hierarchy can touch its views.
    	                    
    	                    // 刷新View  
    	                    postInvalidate();  
    	                } else {  
    	                    radiu = 0;  
    	                }  
    	  
    	                // 每执行一次暂停40毫秒  
    	                Thread.sleep(40);  
    	            } catch (InterruptedException e) {  
    	                e.printStackTrace();  
    	            }  
    	        }  	
    		};
    	}.start();
    	
    }

	/**
	 * 重载方法
	 */
	@Override
	public void run() {

		/*
		 * 确保线程不断执行不断刷新界面
		 */
		while (true) {
			try {
				/*
				 * 如果半径小于200则自加否则大于200后重置半径值以实现往复
				 */
				if (radiu <= 200) {
					radiu += 10;

					// 刷新View
					postInvalidate();
				} else {
					radiu = 0;
				}

				// 每执行一次暂停40毫秒
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		
	}
}
