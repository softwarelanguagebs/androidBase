/*
 * @Title:  CustomView.java
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * @Description:  TODO<>
 * @author:  wen_er
 * @data:  2015年5月22日 下午3:23:10
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
 * @data:  2015年5月22日 下午3:23:10
 * @version:  V1.0
 */
public class CustomView  extends View { 

	private Paint mPaint;  
	private Context mContext;// 上下文环境引用
	
	
	
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
	    canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2, MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, 200, mPaint);  
		   
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
        mPaint.setStyle(Paint.Style.FILL);  
      
        // 设置画笔颜色为自定义颜色  
        mPaint.setColor(Color.argb(255, 255, 128, 103));  
      
        /* 
         * 设置描边的粗细，单位：像素px 
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素 
         */  
        
     // 生成色彩矩阵  
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
