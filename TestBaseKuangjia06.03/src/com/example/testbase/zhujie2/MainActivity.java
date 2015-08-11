/*
 * �ļ�����MainActivity.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��18��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.zhujie2;

import java.lang.reflect.Field;

import com.example.testbase.kuangjia.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	  public static final String TAG= "MainActivity";
	  //��עTextView��id
	  @InjectView(id=R.id.tv_img)
	  private TextView mText;
	   
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_zhujie_new);
	    try {
	      autoInjectAllField(this);
	    } catch (IllegalAccessException e) {
	    } catch (IllegalArgumentException e) {
	    }
	     
	    if(mText!=null)
	      mText.setText("Hello Gavin");
	  }
	   
	  public void autoInjectAllField(Activity activity) throws IllegalAccessException, IllegalArgumentException
	  {
	    //�õ�Activity��Ӧ��Class
	    Class clazz=this.getClass();
	    //�õ���Activity�������ֶ�
	    Field []fields=clazz.getDeclaredFields();
	    Log.v(TAG, "fields size-->"+fields.length);
	    for(Field field :fields)
	    {
	      //�ж��ֶ��Ƿ��עInjectView
	      if(field.isAnnotationPresent(InjectView.class))
	      {
	        Log.v(TAG," is injectView");
	        //�����ע�ˣ��ͻ������id
	        InjectView inject=field.getAnnotation(InjectView.class);
	        int id=inject.id();
	        Log.v(TAG, "id--->"+id);
	        if(id>0)
	        {
	          //�������˽�г�Ա������������
	          field.setAccessible(true);
	          //Ȼ���������Ը���
	          field.set(activity, activity.findViewById(id));
	        }
	      }
	    }
	  }
	 
	 
	 
	}
