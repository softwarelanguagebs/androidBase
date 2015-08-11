package com.example.testbase.log;

import android.util.Log;

public class LogUtils {

	// //////////////////可追踪代码到函数具体某行的日志类 ////////////////可追踪代码到函数具体某行的日志类
	private final static int VERBOSE = 0;
	private final static int DEBUG = 1;
	private final static int INFO = 2;
	private final static int WARN = 3;
	private final static int ERROR = 4;
	private final static int DEFAULT_LEVEL = -1;

	private int level;

	private final String clazz;

	private static final String TAG = "LogUtils";
	//private static  String TAG;

	public static LogUtils getDebugLog(Class<?> clazz, int l) {
		LogUtils log = new LogUtils(clazz);
		log.level = l;
		return log;
	}

	public static LogUtils getLog(Class<?> clazz) {
		return new LogUtils(clazz);
	}

	public LogUtils(Class<?> clazz) {
		this.clazz = "[" + clazz.getSimpleName() + "] ";
		level = DEFAULT_LEVEL;
	}
	
	

	public void verbose(String message) {
		verbose(message, null);
	}

	public void debug(String message) {
		debug(message, null);
	}

	public void info(String message) {
		info(message, null);
	}

	public void warn(String message) {
		warn(message, null);
	}

	public void error(String message) {
		error(message, null);
	}

	public void verbose(String message, Throwable t) {
		if (VERBOSE < level)
			return;
		if (message != null)
			android.util.Log.v(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ message);
		if (t != null)
			android.util.Log.v(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ t.toString());
	}

	public void debug(String message, Throwable t) {
		if (DEBUG < level)
			return;
		if (message != null)
			android.util.Log.d(clazz, clazz + " Line: " + getLineNumber()
					+ " : " + message);
		if (t != null)
			android.util.Log.d(clazz, clazz + " Line: " + getLineNumber()
					+ " : " + t.toString());
	}

	public void info(String message, Throwable t) {
		if (INFO < level)
			return;
		if (message != null)
			android.util.Log.i(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ message);
		if (t != null)
			android.util.Log.i(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ t.toString());
	}

	public void warn(String message, Throwable t) {
		if (WARN < level)
			return;
		if (message != null)
			android.util.Log.w(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ message);
		if (t != null)
			android.util.Log.w(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ t.toString());
	}

	public void error(String message, Throwable t) {
		if (ERROR < level)
			return;
		if (message != null)
			android.util.Log.e(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ message);
		if (t != null)
			android.util.Log.e(TAG, clazz + " Line: " + getLineNumber() + " : "
					+ t.toString());
	}

	private static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[5].getLineNumber();
	}
}
