/*
 * �ļ�����MainActivity.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��19��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.hongyang.Fragment1;

import com.example.testbase.kuangjia.R;

import android.app.Activity;  
import android.os.Bundle;  
import android.view.Window;  
  
public class MainActivity extends Activity  
{  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.activity_main_f1);  
    }  
  
}  