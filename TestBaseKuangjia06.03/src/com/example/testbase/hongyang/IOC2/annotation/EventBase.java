/*
 * 文件名：EventBase.java
 * 版权：Copyright by www.xx.com
 * 描述：
 * 作者：wen
 * 修改时间：2015年3月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
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