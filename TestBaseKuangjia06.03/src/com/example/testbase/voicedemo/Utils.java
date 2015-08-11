package com.example.testbase.voicedemo;

import android.content.Context;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class Utils {
 
    // 语音文件路径
    public static final String audioRootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test/audios";
    
    
	public static void show(String msg, Context context){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	
    /**
     * 获取view在屏幕上的绝对位置
     * 
     * */
    public static Rect getViewAbsoluteLocation(View view){
    	if(view == null){
    		return new Rect();
    	}
		// 获取View相对于屏幕的坐标
		int[] location = new int[2] ;
		view.getLocationOnScreen(location);
		// 获取View的宽高
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		// 获取View的Rect
		Rect rect = new Rect();
		rect.left = location[0];
		rect.top = location[1];
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
		return rect;
	}

    /**
     * 获取view在window上的位置
     * 
     * */
    public static Rect getViewRelativeLocation(View view){
    	if(view == null){
    		return new Rect();
    	}
		// 获取View相对于window的坐标
		int[] location = new int[2] ;
		view.getLocationInWindow(location);
		// 获取View的宽高
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		// 获取View的Rect
		Rect rect = new Rect();
		rect.left = location[0];
		rect.top = location[1];
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
		return rect;
	}
}
