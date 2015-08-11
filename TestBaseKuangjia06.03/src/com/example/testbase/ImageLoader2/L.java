package com.example.testbase.ImageLoader2;

import android.util.Log;

/**
 * Log统一管理类
 * 
 * @author way
 * 
 */
public class L
{
    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    
    private static final String TAG = "way";
    
    public static final String SEPARATOR = ",";
    
    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }
    
    public static void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }
    
    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }
    
    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }
    
    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
    
    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
    
    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
    
    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
    
    /**
     * 输出日志所包含的信息
     */
    public static String getLogInfo(StackTraceElement stackTraceElement)
    {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取生日输出行数
        int lineNumber = stackTraceElement.getLineNumber();
        
        logInfoStringBuilder.append("[ ");
        
        logInfoStringBuilder.append("fileName=" + fileName).append(SEPARATOR);
        logInfoStringBuilder.append("className=" + className).append(SEPARATOR);
        logInfoStringBuilder.append("methodName=" + methodName)
                .append(SEPARATOR);
        logInfoStringBuilder.append("lineNumber=" + lineNumber);
        logInfoStringBuilder.append(" ] ");
        return logInfoStringBuilder.toString();
    }
}
