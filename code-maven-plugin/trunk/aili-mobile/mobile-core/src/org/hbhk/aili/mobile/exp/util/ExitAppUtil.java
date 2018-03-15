package org.hbhk.aili.mobile.exp.util;

import java.util.LinkedList;

import java.util.List;

import android.app.Activity;

import android.app.Application;

public class ExitAppUtil extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();
	private List<Activity> activitys = new LinkedList<Activity>();

	private static ExitAppUtil instance;

	private ExitAppUtil() {

	}

	public static ExitAppUtil getInstance() {
		if (null == instance) {
			instance = new ExitAppUtil();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void addActivitys(Activity activity) {
		activitys.add(activity);
	}

	public List<Activity> getAtivitys() {
		return activitys;
	}

	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

}
