/*
 * �ļ�����OnClick.java
 * ��Ȩ��Copyright by www.xx.com
 * ������
 * ���ߣ�wen
 * �޸�ʱ�䣺2015��3��18��
 * ���ٵ��ţ�
 * �޸ĵ��ţ�
 * �޸����ݣ�
 */

package com.example.testbase.hongyang.IOC2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerType = View.OnClickListener.class, listenerSetter = "setOnClickListener", methodName = "onClick")
public @interface OnClick
{
	int[] value();
}
