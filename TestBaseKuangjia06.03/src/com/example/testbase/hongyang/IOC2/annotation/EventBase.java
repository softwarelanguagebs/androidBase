/*
 * �ļ�����EventBase.java
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
  
@Target(ElementType.ANNOTATION_TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface EventBase  
{  
    Class<?> listenerType();  
  
    String listenerSetter();  
  
    String methodName();  
}  