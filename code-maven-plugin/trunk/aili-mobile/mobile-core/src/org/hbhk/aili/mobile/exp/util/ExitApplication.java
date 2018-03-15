package org.hbhk.aili.mobile.exp.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application {
	private List<Activity> activities = new LinkedList<Activity>();
	private static ExitApplication instance;

	private ExitApplication() {
	}

	public synchronized static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}

	// add Activity
	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void exit() {
		try {
			for (Activity activity : activities) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

}