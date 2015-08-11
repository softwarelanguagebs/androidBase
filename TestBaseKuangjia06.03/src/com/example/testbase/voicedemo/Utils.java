package com.example.testbase.voicedemo;

import android.content.Context;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class Utils {
 
    // �����ļ�·��
    public static final String audioRootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test/audios";
    
    
	public static void show(String msg, Context context){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	
    /**
     * ��ȡview����Ļ�ϵľ���λ��
     * 
     * */
    public static Rect getViewAbsoluteLocation(View view){
    	if(view == null){
    		return new Rect();
    	}
		// ��ȡView�������Ļ������
		int[] location = new int[2] ;
		view.getLocationOnScreen(location);
		// ��ȡView�Ŀ��
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		// ��ȡView��Rect
		Rect rect = new Rect();
		rect.left = location[0];
		rect.top = location[1];
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
		return rect;
	}

    /**
     * ��ȡview��window�ϵ�λ��
     * 
     * */
    public static Rect getViewRelativeLocation(View view){
    	if(view == null){
    		return new Rect();
    	}
		// ��ȡView�����window������
		int[] location = new int[2] ;
		view.getLocationInWindow(location);
		// ��ȡView�Ŀ��
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		// ��ȡView��Rect
		Rect rect = new Rect();
		rect.left = location[0];
		rect.top = location[1];
		rect.right = rect.left + width;
		rect.bottom = rect.top + height;
		return rect;
	}
}
