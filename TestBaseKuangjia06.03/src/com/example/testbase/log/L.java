package com.example.testbase.log;

import java.sql.Date;
import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.util.Log;

 /**
 * Log统一管理类
 * @author wen
 * @version 2015年1月16日
 * @see L
 * @since
 */
@SuppressLint("SimpleDateFormat")
public class L
{
    
    
    public final static int VERBOSE = 5; //不过滤输出所有调试信息 包括 VERBOSE、DEBUG、INFO、WARN、ERROR 

	public final static int DEBUG = 4; //debug过滤器，输出DEBUG、INFO、WARN、ERROR调试信息 
  
	public final static int INFO = 3; //info过滤器，输出INFO、WARN、ERROR调试信息 

	public final static int WARN = 2; //waring过滤器，输出WARN和ERROR调试信息 
	
	public final static int ERROR = 1;  //error过滤器，只输出ERROR调试信息 
	
	

	public static int level = 6;
	
    
    private static final String TAG = "wen";
    //private static final String TAG = _CLASS_FUNC();
    public static final String SEPARATOR = ",";
    
    // 下面是默认tag的函数
    public static void v(String msg)
    {
        if (VERBOSE>=level)
            Log.v(TAG, msg);
    }
    
    public static void d(String msg)
    {
    	if (DEBUG>=level)
            Log.d(TAG, msg);
    }
    
    public static void i(String msg)
    {
    	if (INFO>=level)
            Log.i(TAG, msg);
    }
    
    public static void w(String msg)
    {
    	if (WARN>=level)
            Log.w(TAG, msg);
    }
    
    public static void e(String msg)
    {
    	if (ERROR>=level)
            Log.e(TAG, msg);
    }
    
    // 下面是传入自定义tag的函数
    public static void v(String tag, String msg)
    {
    	  if (VERBOSE>=level)
            Log.v(tag, msg);
    }
    
    public static void d(String tag, String msg)
    {
    	if (DEBUG>=level)
            Log.d(tag, msg);
    }
    
    public static void i(String tag, String msg)
    {
    	if (INFO>=level)
            Log.i(tag, msg);
    }
    
    public static void w(String tag, String msg)
    {
    	if (WARN>=level)
            Log.w(tag, msg);
    }
    
    public static void e(String tag, String msg)
    {
    	if (ERROR>=level)
            Log.e(tag, msg);
    }
    
    
    
    // 当前文件名
    public static String _FILE_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getFileName();
    }
    
    // 当前类名
    public static String _CLASS_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getClassName();
    }
 
    // 当前方法名
    public static String _FUNC_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getMethodName();
    }
 
    // 当前行号
    public static int _LINE_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getLineNumber();
    }
 
    // 当前时间
    public static String _TIME_() {
    Date now = new Date(0);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return sdf.format(now);
 
    }
    
    public   static String _CLASS_FUNC(){
    	StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getClassName()+","+traceElement.getMethodName();
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
