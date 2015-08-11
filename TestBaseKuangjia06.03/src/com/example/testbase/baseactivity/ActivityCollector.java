package com.example.testbase.baseactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {

	public static List<Activity> activityList;

	public static ActivityCollector activityCollector;

	private ActivityCollector() {
	}

	/*
	 * 单例，没使用
	 */
	public static ActivityCollector getInstance() {
		if (activityCollector == null) {
			activityCollector = new ActivityCollector();
		}
		return activityCollector;
	}

	public static void addActivity(Activity activity) {
		if (null != activityList) {
			activityList = new ArrayList<Activity>();
		}
		activityList.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activityList.remove(activity);
	}

	public static void finishAll() {
		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
