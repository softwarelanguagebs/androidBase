/*
 * �ļ�����ViewInjectUtils.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��18��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.hongyang.IOC;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.util.Log;

public class ViewInjectUtils
{
	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

	public static void inject(Activity activity)
	{
		Log.e("TAG", "inject");
		injectContentView(activity);
		injectViews(activity);
		// injectEvents(activity);
	}


	/**
	 * ע�����еĿؼ�
	 * 
	 * @param activity
	 */
	private static void injectViews(Activity activity)
	{
		Class<? extends Activity> clazz = activity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// �������г�Ա����
		for (Field field : fields)
		{
			Log.e("TAG", field.getName()+"");
			ViewInject viewInjectAnnotation = field
					.getAnnotation(ViewInject.class);
			if (viewInjectAnnotation != null)
			{
				int viewId = viewInjectAnnotation.value();
				if (viewId != -1)
				{
					Log.e("TAG", viewId+"");
					// ��ʼ��View
					try
					{
						Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
								int.class);
						Object resView = method.invoke(activity, viewId);
						field.setAccessible(true);
						field.set(activity, resView);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
			}

		}

	}

	/**
	 * ע���������ļ�
	 * 
	 * @param activity
	 */
	private static void injectContentView(Activity activity)
	{
		Class<? extends Activity> clazz = activity.getClass();
		// ��ѯ�����Ƿ����ContentViewע��
		ContentView contentView = clazz.getAnnotation(ContentView.class);
		if (contentView != null)// ����
		{
			int contentViewLayoutId = contentView.value();
			try
			{
				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}