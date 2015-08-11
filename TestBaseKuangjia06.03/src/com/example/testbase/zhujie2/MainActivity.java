/*
 * 文件名：MainActivity.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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
	  //标注TextView的id
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
	    //得到Activity对应的Class
	    Class clazz=this.getClass();
	    //得到该Activity的所有字段
	    Field []fields=clazz.getDeclaredFields();
	    Log.v(TAG, "fields size-->"+fields.length);
	    for(Field field :fields)
	    {
	      //判断字段是否标注InjectView
	      if(field.isAnnotationPresent(InjectView.class))
	      {
	        Log.v(TAG," is injectView");
	        //如果标注了，就获得它的id
	        InjectView inject=field.getAnnotation(InjectView.class);
	        int id=inject.id();
	        Log.v(TAG, "id--->"+id);
	        if(id>0)
	        {
	          //反射访问私有成员，必须加上这句
	          field.setAccessible(true);
	          //然后对这个属性复制
	          field.set(activity, activity.findViewById(id));
	        }
	      }
	    }
	  }
	 
	 
	 
	}
